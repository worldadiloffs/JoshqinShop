package com.example.joshqinshop.model

import java.io.Serializable

data class User(
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val token: String,
    val username: String
):Serializable