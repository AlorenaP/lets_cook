package co.com.monkeymobile.letscook.features.recipes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.monkeymobile.letscook.R
import co.com.monkeymobile.letscook.core.platform.BaseActivity
import co.com.monkeymobile.letscook.core.utils.KEY_ID
import co.com.monkeymobile.letscook.features.recipe_detail.RecipeDetailActivity
import kotlinx.android.synthetic.main.recipes_list_activity.*
import retrofit2.Response
import javax.inject.Inject

class RecipesListActivity : BaseActivity(), RecipesAdapter.RecipeListener {

    @Inject
    lateinit var viewModel: RecipesViewModel
    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_list_activity)
        appComponent.inject(this)

        val adapterLayoutManager = LinearLayoutManager(this)
        recipesAdapter = RecipesAdapter(emptyList(), this)
        recycler.apply {
            layoutManager = adapterLayoutManager
            adapter = recipesAdapter
        }

        viewModel.recipes.observe(this@RecipesListActivity, Observer { onResponse(it) })
        viewModel.failure.observe(this@RecipesListActivity, Observer { onFailure(it) })
        viewModel.fetchItems()
        showIndeterminateModalDialog()
    }

    override fun onRecipeClick(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
            .apply { putExtra(KEY_ID, recipe.id.toString()) }
        startActivity(intent)
    }

    private fun onResponse(response: Response<List<Recipe>>) {
        hideIndeterminateModalDialog()
        if (response.isSuccessful) {
            val recipes = response.body()
            if (recipes == null) {
                Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
            } else {
                recipesAdapter.updateContent(recipes)
            }
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }
}
