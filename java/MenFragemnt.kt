package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class MenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_men, container, false)

        val buttonMen: Button = view.findViewById(R.id.shop_mens_shoes)

        buttonMen.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MenActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageLifestyle: ImageView = view.findViewById(R.id.arrow_image_lifestyle)
        arrowImageLifestyle.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MenLifestyleActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageRunning: ImageView = view.findViewById(R.id.arrow_image_running)
        arrowImageRunning.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MenRunningActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageBoot: ImageView = view.findViewById(R.id.arrow_image_boots)
        arrowImageBoot.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MenBootActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageBasketball: ImageView = view.findViewById(R.id.arrow_image_basketball)
        arrowImageBasketball.setOnClickListener {
            activity?.let {
                val intent = Intent(it, NikeActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageSkate: ImageView = view.findViewById(R.id.arrow_image_skate)
        arrowImageSkate.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MenRunningActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageSandals: ImageView = view.findViewById(R.id.arrow_image_sandals)
        arrowImageSandals.setOnClickListener {
            activity?.let {
                val intent = Intent(it, NikeActivity::class.java)
                it.startActivity(intent)
            }
        }


        return view
    }
}
