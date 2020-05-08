package co.com.monkeymobile.letscook.features.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response
import javax.inject.Inject

class RecipesViewModel @Inject constructor(private val recipesUseCase: GetRecipesUseCase) : ViewModel() {

    private val disposable = CompositeDisposable()
    var recipes = MutableLiveData<Response<List<Recipe>>>()
    var failure = MutableLiveData<Boolean>()

    fun fetchItems() {
        disposable.add(
            recipesUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ recipes.value = it },
                    { failure.value = true })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}