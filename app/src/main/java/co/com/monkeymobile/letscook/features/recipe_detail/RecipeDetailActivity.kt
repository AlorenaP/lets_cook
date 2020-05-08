package co.com.monkeymobile.letscook.features.recipe_detail

import android.os.Bundle
import co.com.monkeymobile.letscook.R
import co.com.monkeymobile.letscook.core.platform.BaseActivity
import javax.inject.Inject

class RecipeDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: RecipeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        appComponent.inject(this)
    }
}
