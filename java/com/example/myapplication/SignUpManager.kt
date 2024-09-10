import android.util.Log
import com.example.yourapp.api.ApiService
import com.example.yourapp.api.SignupRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpManager(private val apiService: ApiService) {

    private val TAG = "SignUpManager"

    fun signUp(email: String, password: String, callback: (Boolean, String) -> Unit) {
        val request = SignupRequest(email, password)
        Log.d(TAG, "Sending sign-up request with email: $email")
        apiService.signup(request).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d(TAG, "Received response with code: ${response.code()}")
                if (response.isSuccessful) {
                    Log.d(TAG, "Sign-up successful: ${response.body()?.string()}")
                    callback(true, "Sign-Up Successful")
                } else {
                    Log.e(TAG, "Sign-up failed with code: ${response.code()}, message: ${response.message()}")
                    callback(false, "Sign-Up Failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, "Network error: ${t.message}", t)
                callback(false, "Network Error: ${t.message}")
            }
        })
    }
}
