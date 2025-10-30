package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.ui.theme.HomeScreen
import com.example.jetpackcompose.ui.theme.Product
import com.example.jetpackcompose.ui.theme.ProductCatalogTheme
import com.example.jetpackcompose.ui.theme.ProductDetailScreen
import com.example.jetpackcompose.ui.theme.ProductsScreen
import com.example.jetpackcompose.ui.theme.ProfileScreen

/**
 * MainActivity - Entry point aplikasi
 * Mengatur navigation dan bottom bar
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductCatalogTheme {
                ProductCatalogApp()
            }
        }
    }
}

/**
 * Main App Composable dengan Bottom Navigation
 */
@Composable
fun ProductCatalogApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Daftar bottom navigation items
    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = "home"
        ),
        BottomNavItem(
            title = "Products",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            route = "products"
        ),
        BottomNavItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = "profile"
        )
    )

    // Cek apakah sedang di halaman detail (untuk menyembunyikan bottom bar)
    val showBottomBar = currentDestination?.route != "product_detail"

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val isSelected = currentDestination?.route == item.route

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(item.route) {
                                    // Konfigurasi navigasi
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(item.title) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            // Home Screen
            composable("home") {
                HomeScreen(
                    onProductsClick = {
                        navController.navigate("products")
                    }
                )
            }

            // Products Screen
            composable("products") {
                ProductsScreen(
                    onProductClick = { product ->
                        navController.navigate("product_detail")
                    }
                )
            }

            // Profile Screen
            composable("profile") {
                ProfileScreen()
            }

            // Product Detail Screen
            composable("product_detail") {
                // Untuk demo, kita gunakan sample product pertama
                val sampleProduct = Product(
                    id = 1,
                    name = "Laptop Gaming",
                    description = "Laptop gaming dengan spesifikasi tinggi",
                    price = 15000000.0,
                    category = "Elektronik"
                )

                ProductDetailScreen(
                    product = sampleProduct,
                    onBackClick = { navController.navigateUp() }
                )
            }
        }
    }
}

/**
 * Data class untuk bottom navigation item
 */
data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)