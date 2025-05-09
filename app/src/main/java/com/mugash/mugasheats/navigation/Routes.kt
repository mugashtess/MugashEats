package com.mugash.mugasheats.navigation

const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_CONTACT = "contact"
const val ROUT_DASHBOARD= "dashboard"
const val ROUT_ITEM = "item"
const val ROUT_START = "start"
const val ROUT_SPLASH= "splash"
const val ROUT_MORE= "more"
const val ROUT_RESTAURANTMENU= "restaurantmenu"
const val ROUT_RECIPE= "recipe"

//Recipe

const val ROUT_ADD_RECIPE = "add_recipe"
const val ROUT_RECIPE_LIST= "recipelist"
const val ROUT_EDIT_RECIPE = "edit_recipe/{recipeId}"

// ✅ Helper function for navigation
fun editRecipeRoute(recipeId: Int) = "edit_recipe/$recipeId"




// Authetication
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"
