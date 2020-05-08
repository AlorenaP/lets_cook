package co.com.monkeymobile.letscook.features.recipes

import retrofit2.Call
import retrofit2.http.GET

interface RecipesApi {

    @GET("/recipes")
    fun fetchRecipes(): Call<List<Recipe>>
}