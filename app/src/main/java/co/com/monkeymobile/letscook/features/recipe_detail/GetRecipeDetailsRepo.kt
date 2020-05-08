package co.com.monkeymobile.letscook.features.recipe_detail

import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response

interface GetRecipeDetailsRepo {

    fun getRecipeDetails(id: String): Flowable<Response<RecipeDetails>>
}