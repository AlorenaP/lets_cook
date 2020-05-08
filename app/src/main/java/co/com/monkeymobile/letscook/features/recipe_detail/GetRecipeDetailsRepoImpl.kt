package co.com.monkeymobile.letscook.features.recipe_detail

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class GetRecipeDetailsRepoImpl @Inject constructor(private val recipeDetailsService: GetRecipeDetailsService) : GetRecipeDetailsRepo {

    override fun getRecipeDetails(id: String): Flowable<Response<RecipeDetails>> =
        Flowable.fromCallable { recipeDetailsService.fetchRecipeDetails(id) }
            .subscribeOn(Schedulers.io())
}