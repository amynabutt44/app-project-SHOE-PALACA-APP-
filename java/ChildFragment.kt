package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ChildFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kids, container, false)

        val buttonKids: Button = view.findViewById(R.id.shop_kids_shoes_button)

        buttonKids.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsActivity::class.java)
                it.startActivity(intent)
            }
        }

        val arrowImageLifestyle: ImageView = view.findViewById(R.id.arrow_image_lifestyle)
        arrowImageLifestyle.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsLifestyle::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageRunning: ImageView = view.findViewById(R.id.arrow_image_running)
        arrowImageRunning.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsRunningActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageBoot: ImageView = view.findViewById(R.id.arrow_image_boots)
        arrowImageBoot.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsBootActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageBasketball: ImageView = view.findViewById(R.id.arrow_image_basketball)
        arrowImageBasketball.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsRunningActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageSkate: ImageView = view.findViewById(R.id.arrow_image_skate)
        arrowImageSkate.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsRunningActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageSandals: ImageView = view.findViewById(R.id.arrow_image_sandals)
        arrowImageSandals.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsBootActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageGrade: ImageView = view.findViewById(R.id.arrow_image_grade)
        arrowImageGrade.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsGradeSchoolActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImagePre: ImageView = view.findViewById(R.id.arrow_image_pre)
        arrowImagePre.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsPreSchoolActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageInfant: ImageView = view.findViewById(R.id.arrow_image_infant)
        arrowImageInfant.setOnClickListener {
            activity?.let {
                val intent = Intent(it, KidsInfantActivity::class.java)
                it.startActivity(intent)
            }
        }

        return view
    }
}
