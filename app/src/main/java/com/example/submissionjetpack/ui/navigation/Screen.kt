package com.example.submissionjetpack.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailShoes : Screen("home/{ShoesId}") {
        fun createRoute(shoesId : Long) = "home/$shoesId"
    }
}
