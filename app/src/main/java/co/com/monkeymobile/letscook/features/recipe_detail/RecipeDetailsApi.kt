package co.com.monkeymobile.letscook.features.recipe_detail

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeDetailsApi {

    @GET("/recipes/{recipeId}")
    fun fetchRecipeDetails(@Path("recipeId") id: String): Call<RecipeDetails>
}