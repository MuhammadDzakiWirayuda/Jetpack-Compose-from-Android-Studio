package com.example.jetpackcompose.ui.theme

import java.text.NumberFormat
import java.util.*

object PriceUtils {
    fun formatPrice(price: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return formatter.format(price)
    }
}