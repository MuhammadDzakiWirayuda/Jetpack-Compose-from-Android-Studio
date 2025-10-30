package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.productcatalog.model.Product
import com.example.productcatalog.navigation.*
import com.example.productcatalog.ui.screens.*
import com.example.productcatalog.ui.theme.ProductCatalogTheme

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
            route = HomeRoute::class
        ),
        BottomNavItem(
            title = "Products",
            selectedIcon = Icons.Filled.ShoppingBag,
            unselectedIcon = Icons.Outlined.ShoppingBag,
            route = ProductsRoute::class
        ),
        BottomNavItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = ProfileRoute::class
        )
    )

    // Cek apakah sedang di halaman detail (untuk menyembunyikan bottom bar)
    val showBottomBar = currentDestination?.hierarchy?.any {
        it.hasRoute(ProductDetailRoute::class)
    } != true

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.hasRoute(item.route)
                        } == true

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(
                                    when (item.route) {
                                        HomeRoute::class -> HomeRoute
                                        ProductsRoute::class -> ProductsRoute
                                        ProfileRoute::class -> ProfileRoute
                                        else -> HomeRoute
                                    }
                                ) {
                                    // Pop up to start destination
                                    popUpTo(HomeRoute) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies
                                    launchSingleTop = true
                                    // Restore state
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
            startDestination = HomeRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            // Home Screen
            composable<HomeRoute> {
                HomeScreen()
            }

            // Products Screen
            composable<ProductsRoute> {
                ProductsScreen(
                    onProductClick = { product ->
                        // Passing data menggunakan @Serializable
                        navController.navigate(
                            ProductDetailRoute(
                                id = product.id,
                                name = product.name,
                                description = product.description,
                                price = product.price,
                                category = product.category
                            )
                        )
                    }
                )
            }

            // Profile Screen
            composable<ProfileRoute> {
                ProfileScreen()
            }

            // Product Detail Screen
            composable<ProductDetailRoute> { backStackEntry ->
                val productDetail = backStackEntry.toRoute<ProductDetailRoute>()

                // Konversi dari route ke Product object
                val product = Product(
                    id = productDetail.id,
                    name = productDetail.name,
                    description = productDetail.description,
                    price = productDetail.price,
                    category = productDetail.category
                )

                ProductDetailScreen(
                    product = product,
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
    val route: kotlin.reflect.KClass<*>
)