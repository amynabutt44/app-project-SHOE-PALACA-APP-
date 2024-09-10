package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.Toast
class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail) // Ensure you have a product_detail.xml layout file

        val product: Product = intent.getParcelableExtra("PRODUCT")!!

        val productImage: ImageView = findViewById(R.id.detailProductImage)
        val productName: TextView = findViewById(R.id.detailProductName)
        val productDescription: TextView = findViewById(R.id.detailProductDescription)
        val productPrice: TextView = findViewById(R.id.detailProductPrice)
        val sizeOptions: RadioGroup = findViewById(R.id.sizeOptions)
        val addToCartButton: Button = findViewById(R.id.addToCartButton)

        // Set product details
        productName.text = product.name
        productDescription.text = product.description // Make sure Product has a description field
        productPrice.text = product.price.toString()

        // Load product image
        Glide.with(this)
            .load(product.image.replace("localhost", "10.0.2.2"))
            .placeholder(R.drawable.iconmain)
            .into(productImage)

        addToCartButton.setOnClickListener {
            // Handle add to cart
            Cart.addItem(this, product)
            Toast.makeText(this, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
        }
    }
}
