package co.com.monkeymobile.letscook.features.recipe_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(private val recipeDetailsUseCase: GetRecipeDetailsUseCase) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    var recipeDetails = MutableLiveData<Response<RecipeDetails>>()
    var failure = MutableLiveData<Boolean>()

    fun fetchRecipeDetails(recipeId: String) {
        disposable.add(
            recipeDetailsUseCase(recipeId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ recipeDetails.value = it },
                    { failure.value = true })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}