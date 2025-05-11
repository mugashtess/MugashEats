package com.mugash.mugasheats.repository

import androidx.lifecycle.LiveData
import com.mugash.mugasheats.data.RecipeDao
import com.mugash.mugasheats.data.RecipeDatabase
import com.mugash.mugasheats.model.Recipe

class RecipeRepository(private val dao: RecipeDao) {

    val allRecipes: LiveData<List<Recipe>> = dao.getAllRecipes()

    suspend fun insert(recipe: Recipe) {
        dao.insert(recipe)
    }

    suspend fun update(recipe: Recipe) {
        dao.update(recipe)
    }

    suspend fun delete(recipe: Recipe) {
        dao.delete(recipe)
    }

    suspend fun getRecipeById(id: Int): Recipe? {
        return dao.getRecipeById(id)
    }

}
