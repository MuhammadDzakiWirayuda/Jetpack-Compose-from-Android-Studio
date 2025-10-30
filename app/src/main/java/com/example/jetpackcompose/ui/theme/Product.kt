package com.example.jetpackcompose.ui.theme

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageUrl: String = ""
)

object ProductData {
    val sampleProducts = listOf(
        Product(
            id = 1,
            name = "Laptop Gaming",
            description = "Laptop gaming dengan spesifikasi tinggi, processor Intel Core i7, RAM 16GB, VGA RTX 3060",
            price = 15000000.0,
            category = "Elektronik"
        ),
        Product(
            id = 2,
            name = "Smartphone Android",
            description = "Smartphone dengan kamera 108MP, layar AMOLED 6.7 inch, battery 5000mAh",
            price = 4500000.0,
            category = "Elektronik"
        ),
        Product(
            id = 3,
            name = "Headphone Bluetooth",
            description = "Headphone wireless dengan noise cancellation, battery life 30 jam",
            price = 1200000.0,
            category = "Audio"
        ),
        Product(
            id = 4,
            name = "Smart Watch",
            description = "Smart watch dengan heart rate monitor, GPS, dan waterproof IP68",
            price = 2500000.0,
            category = "Wearable"
        ),
        Product(
            id = 5,
            name = "Mechanical Keyboard",
            description = "Keyboard mechanical RGB dengan switch Cherry MX Blue",
            price = 850000.0,
            category = "Aksesoris"
        )
    )
}
