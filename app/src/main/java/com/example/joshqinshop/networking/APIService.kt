package com.example.joshqinshop.networking

import com.example.joshqinshop.model.CartsData
import com.example.joshqinshop.model.Comment
import com.example.joshqinshop.model.CommentData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.joshqinshop.model.Login
import com.example.joshqinshop.model.Product
import com.example.joshqinshop.model.ProductData
import com.example.joshqinshop.model.User

interface APIService {

    @GET("/products")
    fun getAllProducts(): Call<ProductData>

    @GET("/products/categories")
    fun getAllCategories(): Call<List<String>>

    @GET("/products/category/{categoryName}")
    fun getProductByCategory(@Path("categoryName") categoryName: String): Call<ProductData>

    @GET("/products/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

    @GET("products/search")
    fun searchByName(@Query("q") name: String): Call<ProductData>

    @POST("/auth/login")
    fun login(@Body login: Login): Call<User>

    @GET("/comments")
    fun getAllComments(): Call<CommentData>

    @GET("/carts")
    fun getAllCarts(): Call<CartsData>
}