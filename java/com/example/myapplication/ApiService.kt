package com.example.yourapp.api

import com.example.myapplication.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class SignupRequest(val email: String, val password: String)
data class SignupResponse(val success: Boolean, val message: String)

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val success: Boolean,
    val message: String
)


interface ApiService {
    @GET("Men/Index")
    suspend fun getMenProducts(): List<Product>

    @GET("Women/Index")
    suspend fun getWomenProducts(): List<Product>

    @GET("Child/Index")
    suspend fun getKidsProducts(): List<Product>
    @POST("/api/Auth/signup")
    fun signup(@Body request: SignupRequest): Call<ResponseBody>

    @POST("/api/Auth/login")
    fun login(@Body request: LoginRequest): Call<ResponseBody>

    @GET("Home/Index")
    suspend fun shopProducts(): List<Product>

    @GET("Home/Shoes")
    suspend fun shopShoes(): List<Product>

    @GET("Home/MTV")
    suspend fun getMTVProducts(): List<Product>

    @GET("Home/Adidas")
    suspend fun getAdidasProducts(): List<Product>

    @GET("Home/Nike")
    suspend fun getNikeProducts(): List<Product>
    @GET("Men/Lifestyle")
    suspend fun getMenLifestyle(): List<Product>
    @GET("Men/Running")
    suspend fun getMenRunning(): List<Product>
    @GET("Men/Sandals")
    suspend fun getMenSandals(): List<Product>
    @GET("Men/Basketball")
    suspend fun getMenBasketball(): List<Product>
    @GET("Men/Skate")
    suspend fun getMenSkate(): List<Product>
    @GET("Men/Boots")
    suspend fun getMenBoots(): List<Product>

    @GET("Women/Lifestyle")
    suspend fun getWomenLifestyle(): List<Product>
    @GET("Women/Running")
    suspend fun getWomenRunning(): List<Product>
    @GET("Women/Sandals")
    suspend fun getWomenSandals(): List<Product>
    @GET("Women/Basketball")
    suspend fun getWomenBasketball(): List<Product>
    @GET("Women/Skate")
    suspend fun getWomenSkate(): List<Product>
    @GET("Women/Boots")
    suspend fun getWomenBoots(): List<Product>
    @GET("Child/GradeSchool")
    suspend fun getChildGrade(): List<Product>
    @GET("Child/PreSchool")
    suspend fun getChildPre(): List<Product>
    @GET("Child/Running")
    suspend fun getChildRunning(): List<Product>

    @GET("Women/LifeStyle")
    suspend fun getChildLifestyle(): List<Product>
    @GET("Child/Basketball")
    suspend fun getChildBasketball(): List<Product>
    @GET("Child/Skate")
    suspend fun getChildSkate(): List<Product>
    @GET("Child/Boots")
    suspend fun getChildBoots(): List<Product>
    @GET("Child/Infant")
    suspend fun getInfant(): List<Product>

}
