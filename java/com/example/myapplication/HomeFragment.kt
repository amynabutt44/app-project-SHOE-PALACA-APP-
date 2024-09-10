import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.AdidasActivity
import com.example.myapplication.MTVActivity
import com.example.myapplication.NikeActivity
import com.example.myapplication.R
import com.example.myapplication.ShopActivity
import com.example.myapplication.ShopShoesActivity
import com.example.myapplication.WomenActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Find the button by its ID
        val shopNowButton: Button = view.findViewById(R.id.shopNowButton)
        val shopNowButton1: Button = view.findViewById(R.id.shopNowButton1)


        // Set a click listener on the button
        shopNowButton.setOnClickListener {
            activity?.let {
                val intent = Intent(it, ShopActivity::class.java)
                it.startActivity(intent)
            }
        }
        shopNowButton1.setOnClickListener {
            activity?.let {
                val intent = Intent(it, ShopShoesActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageMTV: ImageView = view.findViewById(R.id.arrow_image_mtv)
        arrowImageMTV.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MTVActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageAdidas: ImageView = view.findViewById(R.id.arrow_image_adidas)
        arrowImageAdidas.setOnClickListener {
            activity?.let {
                val intent = Intent(it, AdidasActivity::class.java)
                it.startActivity(intent)
            }
        }
        val arrowImageNike: ImageView = view.findViewById(R.id.arrow_image_nike)
        arrowImageNike.setOnClickListener {
            activity?.let {
                val intent = Intent(it, NikeActivity::class.java)
                it.startActivity(intent)
            }
        }

        return view
    }
}
