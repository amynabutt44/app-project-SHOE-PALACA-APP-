// WomenActivity.kt
package com.example.myapplication

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Product
import com.example.myapplication.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenLifestyleActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_women)

        // Initialize the GridView
        gridView = findViewById(R.id.gridViewMen)

        // Initialize the adapter with an empty list
        productListAdapter = ProductListAdapter(this, emptyList())
        gridView.adapter = productListAdapter

        // Fetch products for Women
        fetchProducts()
    }

    private fun fetchProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val products = RetrofitInstance.api.getMenProducts()
                withContext(Dispatchers.Main) {
                    productListAdapter.updateProducts(products)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Handle the error, possibly show a Toast or log it
                }
            }
        }
    }
}
