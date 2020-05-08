package co.com.monkeymobile.letscook.features.recipes

import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: GetRecipesRepo) {

    operator fun invoke() = repository.getRecipes()
}