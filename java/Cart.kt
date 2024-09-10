package com.example.myapplication

import android.content.Context

object Cart {
    private lateinit var dbHelper: DatabaseHelper

    fun initialize(context: Context) {
        dbHelper = DatabaseHelper(context)
    }

    fun addItem(context: Context, product: Product) {
        val existingItems = getItems(context)
        val existingItem = existingItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
            dbHelper.updateCartItem(existingItem)
        } else {
            dbHelper.addCartItem(CartItem(product, 1))
        }
    }

    fun removeItem(context: Context, product: Product) {
        dbHelper.deleteCartItem(product.id)
    }

    fun updateItem(context: Context, cartItem: CartItem) {
        dbHelper.updateCartItem(cartItem)
    }

    fun getItems(context: Context): List<CartItem> {
        return dbHelper.getCartItems()
    }

    fun clearCart(context: Context) {
        val items = getItems(context)
        items.forEach { dbHelper.deleteCartItem(it.product.id) }
    }
}
