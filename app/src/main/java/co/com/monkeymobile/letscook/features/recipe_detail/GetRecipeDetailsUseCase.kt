package co.com.monkeymobile.letscook.features.recipe_detail

import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(private val repository: GetRecipeDetailsRepo) {

    operator fun invoke(recipeId: String) = repository.getRecipeDetails(recipeId)
}