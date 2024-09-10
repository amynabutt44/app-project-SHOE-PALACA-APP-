package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.util.concurrent.TimeUnit

class CartFragment : Fragment() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var orderPlaceButton: Button
    private val TAG = "CartFragment"
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        val cartListView: ListView = view.findViewById(R.id.cartListView)
        orderPlaceButton = view.findViewById(R.id.orderPlaceButton)

        databaseHelper = DatabaseHelper(requireContext()) // Initialize DatabaseHelper

        val cartItems = databaseHelper.getCartItems()
        Log.d(TAG, "Loaded ${cartItems.size} items from cart")

        cartAdapter = CartAdapter(requireContext(), cartItems)
        cartListView.adapter = cartAdapter

        // Set initial button state based on cart content
        updateOrderButtonState(cartItems)

        // Handle Order Place Button click
        orderPlaceButton.setOnClickListener {
            showOrderDetailsDialog(cartItems)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // Refresh cart items when fragment is resumed
        val cartItems = databaseHelper.getCartItems()
        Log.d(TAG, "Refreshing cart items, ${cartItems.size} items found")
        cartAdapter.updateCartItems(cartItems)

        // Update the button state again when the fragment is resumed
        updateOrderButtonState(cartItems)
    }

    private fun updateOrderButtonState(cartItems: List<CartItem>) {
        if (cartItems.isEmpty()) {
            orderPlaceButton.isEnabled = false
            Toast.makeText(requireContext(), "Cart is empty, cannot place order.", Toast.LENGTH_SHORT).show()
        } else {
            orderPlaceButton.isEnabled = true
        }
    }

    private fun showOrderDetailsDialog(cartItems: List<CartItem>) {
        // Calculate total amount
        val totalAmount = cartItems.sumOf { it.product.price * it.quantity }

        // Inflate the dialog layout
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_order_details, null)
        val emailEditText: EditText = dialogView.findViewById(R.id.emailEditText)
        val nameEditText: EditText = dialogView.findViewById(R.id.nameEditText)
        val phoneEditText: EditText = dialogView.findViewById(R.id.phoneEditText)
        val totalAmountTextView: TextView = dialogView.findViewById(R.id.totalAmountTextView)
        totalAmountTextView.text = "Total Amount: $${totalAmount.format(2)}"

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Order Details")
            .setView(dialogView)
            .setPositiveButton("Confirm") { _, _ ->
                // Collect user input
                val email = emailEditText.text.toString()
                val name = nameEditText.text.toString()
                val phone = phoneEditText.text.toString()

                // Handle order placement
                placeOrder(cartItems, name, email, phone, totalAmount)
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }
    private fun placeOrder(cartItems: List<CartItem>, name: String, email: String, phone: String, totalAmount: Double) {
        // Convert cart items to JSON
        val jsonCartItems = cartItemsToJson(cartItems)
        val jsonString = jsonCartItems.toString()

        // Show the JSON data in a Toast on the main thread
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(requireContext(), jsonString, Toast.LENGTH_LONG).show()
        }

        // Create HTTP client with increased timeout settings
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(30, TimeUnit.SECONDS)    // Read timeout
            .writeTimeout(30, TimeUnit.SECONDS)   // Write timeout
            .build()

        // Define the request
        val request = Request.Builder()
            .url("http://10.0.2.2:5189/api/CartAPI/place_order") // Replace with your server URL
            .post(RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), jsonString))
            .build()

        // Make the HTTP call asynchronously
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Failed to place order: ${e.message}", e)
                // Handle failure on the main thread
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Order placed successfully")
                    // Add the order to the database
                    databaseHelper.addOrder(name, email, phone, totalAmount)

                    // Clear the cart
                    databaseHelper.clearCart()

                    // Handle success on the main thread
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(requireContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show()
                        // Update the cart items and button state
                        val cartItems = databaseHelper.getCartItems()
                        cartAdapter.updateCartItems(cartItems)
                        updateOrderButtonState(cartItems)
                    }
                } else {
                    Log.e(TAG, "Failed to place order, response code: ${response.code}, message: ${response.message}")
                    // Handle failure on the main thread
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(requireContext(), "Error placing order: ${response.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }


    private fun cartItemsToJson(cartItems: List<CartItem>): JSONArray {
        val jsonArray = JSONArray()
        for (item in cartItems) {
            val jsonObject = JSONObject().apply {
                put("name", item.product.name)
                put("price", item.product.price)
                put("quantity", item.quantity)
            }
            jsonArray.put(jsonObject)
        }
        return jsonArray
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}
