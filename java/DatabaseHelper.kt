package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "cart.db"
        private const val DATABASE_VERSION = 2 // Increment the version number

        private const val TABLE_CART = "cart"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PRODUCT_NAME = "product_name"
        private const val COLUMN_PRODUCT_PRICE = "product_price"
        private const val COLUMN_QUANTITY = "quantity"

        private const val TABLE_ORDERS = "orders"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_ADDRESS = "address"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_TOTAL_AMOUNT = "total_amount"

        private const val TABLE_CREATE_CART = """
            CREATE TABLE $TABLE_CART (
                $COLUMN_ID TEXT PRIMARY KEY,  
                $COLUMN_PRODUCT_NAME TEXT,
                $COLUMN_PRODUCT_PRICE REAL,
                $COLUMN_QUANTITY INTEGER
            )
        """

        private const val TABLE_CREATE_ORDERS = """
            CREATE TABLE $TABLE_ORDERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_ADDRESS TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_TOTAL_AMOUNT REAL
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE_CART)
        db.execSQL(TABLE_CREATE_ORDERS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CART")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        onCreate(db)
    }

    fun addCartItem(cartItem: CartItem) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, cartItem.product.id)  // String
            put(COLUMN_PRODUCT_NAME, cartItem.product.name)
            put(COLUMN_PRODUCT_PRICE, cartItem.product.price)
            put(COLUMN_QUANTITY, cartItem.quantity)
        }
        db.insert(TABLE_CART, null, values)
        db.close()
    }

    fun getCartItems(): List<CartItem> {
        val cartItems = mutableListOf<CartItem>()
        val db = readableDatabase
        val cursor = db.query(TABLE_CART, null, null, null, null, null, null)

        if (cursor != null) {
            val idIndex = cursor.getColumnIndex(COLUMN_ID)
            val nameIndex = cursor.getColumnIndex(COLUMN_PRODUCT_NAME)
            val priceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)
            val quantityIndex = cursor.getColumnIndex(COLUMN_QUANTITY)

            if (idIndex == -1 || nameIndex == -1 || priceIndex == -1 || quantityIndex == -1) {
                Log.e("DatabaseHelper", "Column index error: idIndex=$idIndex, nameIndex=$nameIndex, priceIndex=$priceIndex, quantityIndex=$quantityIndex")
                cursor.close()
                db.close()
                return cartItems
            }

            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getString(idIndex)  // String
                    val name = cursor.getString(nameIndex)
                    val price = cursor.getDouble(priceIndex)
                    val quantity = cursor.getInt(quantityIndex)

                    cartItems.add(CartItem(Product(id, name, "", price, "", "", "", ""), quantity))
                } while (cursor.moveToNext())
            }
        } else {
            Log.e("DatabaseHelper", "Cursor is null")
        }

        cursor?.close()
        db.close()
        return cartItems
    }

    fun updateCartItem(cartItem: CartItem) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PRODUCT_NAME, cartItem.product.name)
            put(COLUMN_PRODUCT_PRICE, cartItem.product.price)
            put(COLUMN_QUANTITY, cartItem.quantity)
        }
        db.update(TABLE_CART, values, "$COLUMN_ID = ?", arrayOf(cartItem.product.id))
        db.close()
    }

    fun deleteCartItem(productId: String) {
        val db = writableDatabase
        db.delete(TABLE_CART, "$COLUMN_ID = ?", arrayOf(productId))
        db.close()
    }
    fun clearCart() {
        val db = writableDatabase
        db.delete(TABLE_CART, null, null) // Deletes all rows in the cart table
        db.close()
    }
    fun addOrder(name: String, address: String, phone: String, totalAmount: Double) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_ADDRESS, address)
            put(COLUMN_PHONE, phone)
            put(COLUMN_TOTAL_AMOUNT, totalAmount)
        }
        db.insert(TABLE_ORDERS, null, values)
        db.close()
    }
}
