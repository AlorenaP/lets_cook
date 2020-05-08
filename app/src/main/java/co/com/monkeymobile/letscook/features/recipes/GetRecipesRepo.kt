package co.com.monkeymobile.letscook.features.recipes

import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response

interface GetRecipesRepo {

    fun getRecipes(): Flowable<Response<List<Recipe>>>
}