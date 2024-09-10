package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button


class NewFragemnt : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragemnt_new, container, false)

        val buttonMen: Button = view.findViewById(R.id.shop_men)
        val buttonWomen: Button = view.findViewById(R.id.shop_women)
        val buttonKids: Button = view.findViewById(R.id.shop_kids)

        buttonMen.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MenActivity::class.java)
                it.startActivity(intent)
            }
        }

        buttonWomen.setOnClickListener {
            activity?.let {
                val intent = Intent(it, WomenActivity::class.java)
                it.startActivity(intent)
            }
        }

        buttonKids.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsActivity::class.java)
                it.startActivity(intent)
            }
        }

        return view
    }
}
