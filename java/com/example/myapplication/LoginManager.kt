import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import com.example.yourapp.api.ApiService
import com.example.yourapp.api.LoginRequest

class LoginManager(private val apiService: ApiService) {

    private val TAG = "LoginManager"

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        val request = LoginRequest(email, password)
        apiService.login(request).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    // Login successful
                    Log.d(TAG, "Login successful: ${response.body()?.string()}")
                    callback(true)
                } else {
                    // Handle failure, like incorrect credentials
                    Log.e(TAG, "Login failed: ${response.errorBody()?.string()}")
                    callback(false)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Handle network or other errors
                Log.e(TAG, "Network error: ${t.message}", t)
                callback(false)
            }
        })
    }
}
