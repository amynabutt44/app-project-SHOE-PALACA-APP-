// MainActivity.kt
package com.example.myapplication

import HomeFragment
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Cart.initialize(this)

        // Set the initial fragment to HomeFragment
        replaceFragment(HomeFragment())

        findViewById<View>(R.id.nav_home).setOnClickListener {
            replaceFragment(HomeFragment())
        }
        findViewById<View>(R.id.nav_new).setOnClickListener {
            replaceFragment(NewFragemnt())
        }

        findViewById<View>(R.id.nav_men).setOnClickListener {
            replaceFragment(MenFragment())
        }

        findViewById<View>(R.id.nav_women).setOnClickListener {
            replaceFragment(WomenFragment())
        }

        findViewById<View>(R.id.nav_kids).setOnClickListener {
            replaceFragment(ChildFragment())
        }

        findViewById<View>(R.id.nav_brands).setOnClickListener {
            replaceFragment(BrandFragment())
        }
// MainActivity.kt
        // Handle the cart icon click
        findViewById<ImageButton>(R.id.cart).setOnClickListener {
            replaceFragment(CartFragment())
        }

        // Handle the cart icon click
        // Handle login and sign-up button clicks
        findViewById<ImageButton>(R.id.login_user1).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }



    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
