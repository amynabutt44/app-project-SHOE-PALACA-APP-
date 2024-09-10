package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class CartAdapter(private val context: Context, private var cartItems: List<CartItem>) : BaseAdapter() {

    override fun getCount(): Int = cartItems.size

    override fun getItem(position: Int): Any = cartItems[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)

        val productName: TextView = view.findViewById(R.id.cartProductName)
        val productPrice: TextView = view.findViewById(R.id.cartProductPrice)
        val productQuantity: TextView = view.findViewById(R.id.cartProductQuantity)
        val buttonDecrease: Button = view.findViewById(R.id.buttonDecrease)
        val buttonIncrease: Button = view.findViewById(R.id.buttonIncrease)
        val buttonRemove: Button = view.findViewById(R.id.buttonRemove)

        val cartItem = cartItems[position]

        productName.text = cartItem.product.name
        productPrice.text = "Price: ${cartItem.product.price * cartItem.quantity}"
        productQuantity.text = "Quantity: ${cartItem.quantity}"

        buttonRemove.setOnClickListener {
            Cart.removeItem(context, cartItem.product)
            updateCartItems(Cart.getItems(context))
        }

        buttonDecrease.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                Cart.updateItem(context, cartItem)
                updateCartItems(Cart.getItems(context))
            }
        }

        buttonIncrease.setOnClickListener {
            cartItem.quantity++
            Cart.updateItem(context, cartItem)
            updateCartItems(Cart.getItems(context))
        }

        return view
    }

    fun updateCartItems(newCartItems: List<CartItem>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }
}
