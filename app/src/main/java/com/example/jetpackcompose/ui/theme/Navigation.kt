package com.example.jetpackcompose.ui.theme

import kotlinx.serialization.Serializable

// Bottom Navigation Routes
@Serializable
object HomeRoute

@Serializable
object ProductsRoute

@Serializable
object ProfileRoute

// Detail Route dengan parameter
@Serializable
data class ProductDetailRoute(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String
)