/*import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myapplication.Cart
import com.example.myapplication.Product
import com.example.myapplication.R

class ProductListAdapter(private val context: Context, private var products: List<Product>) : BaseAdapter() {

    companion object {
        private const val TAG = "ProductListAdapter"
    }

    override fun getCount(): Int = products.size

    override fun getItem(position: Int): Any = products[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)

        val productImage: ImageView = view.findViewById(R.id.productImage)
        val productName: TextView = view.findViewById(R.id.productName)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
        val productButton: Button = view.findViewById(R.id.productButton)

        val product = products[position]

        // Load image using Glide
      
        Glide.with(context)
            .load(product.image)
            .placeholder(R.drawable.p1)
            .into(productImage)

        productName.text = product.name
        productPrice.text = product.price.toString()

        productButton.setOnClickListener {
            Log.d(TAG, "Button clicked for product: ${product.name}")

            try {
                // Add the product to the cart
                Cart.addItem(context, product)
                Log.d(TAG, "Product ${product.name} added to cart successfully")

                // Notify the user that the product has been added to the cart
                Toast.makeText(context, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e(TAG, "Error adding product to cart: ${e.message}", e)
            }
        }

        return view
    }

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
*/
package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.util.Log
import android.widget.Toast
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.Product

class ProductListAdapter(private val context: Context, private var products: List<Product>) : BaseAdapter() {

    companion object {
        private const val TAG = "ProductListAdapter"
    }
    override fun getCount(): Int = products.size

    override fun getItem(position: Int): Product = products[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val product = getItem(position)

        holder.productName.text = product.name
        holder.productPrice.text = "${product.price}"


        // Replace localhost with 10.0.2.2 for emulator access
        val imageUrl = product.image.replace("localhost", "10.0.2.2")

        // Load the product image from the URL using Glide
        Glide.with(context)
            .load(imageUrl) // URL should now be like http://10.0.2.2:5294/Images/Uploads/ladies4_4bad7493-6e9d-498f-9af0-041b755b235a.png
            .placeholder(R.drawable.iconmain)  // Placeholder image while loading
           // Error image if loading fails
            .into(holder.productImage)

        // Set up the button click listener (e.g., for adding to cart)
        holder.buyButton.setOnClickListener {

            Log.d(TAG, "Button clicked for product: ${product.name}")

            try {
                // Add the product to the cart
                Cart.addItem(context, product)
                Log.d(TAG, "Product ${product.name} added to cart successfully")

                // Notify the user that the product has been added to the cart
                Toast.makeText(context, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e(TAG, "Error adding product to cart: ${e.message}", e)
            }
        }

        view.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("PRODUCT", product) // Pass the product object to the new Activity
            }
            context.startActivity(intent)
        }
        return view
    }

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    private class ViewHolder(view: View) {
        val productImage: ImageView = view.findViewById(R.id.productImage)
        val productName: TextView = view.findViewById(R.id.productName)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
        val buyButton: Button = view.findViewById(R.id.productButton)
    }
}
