package co.com.monkeymobile.letscook.features.recipes

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class GetRecipesRepoImpl @Inject constructor(private val recipesService: GetRecipesService) : GetRecipesRepo {

    override fun getRecipes(): Flowable<Response<List<Recipe>>> =
        Flowable.fromCallable { recipesService.fetchRecipes() }
            .subscribeOn(Schedulers.io())
}