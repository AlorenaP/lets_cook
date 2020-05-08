package co.com.monkeymobile.letscook.features.recipe_detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import co.com.monkeymobile.letscook.R
import co.com.monkeymobile.letscook.core.platform.BaseActivity
import co.com.monkeymobile.letscook.core.utils.KEY_ID
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import retrofit2.Response
import javax.inject.Inject

class RecipeDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: RecipeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        appComponent.inject(this)

        viewModel.recipeDetails.observe(this@RecipeDetailActivity, Observer { onResponse(it) })
        viewModel.failure.observe(this@RecipeDetailActivity, Observer { onFailure(it) })

        val recipeId = intent.getStringExtra(KEY_ID)
        if (recipeId.isNullOrBlank()) {
            Toast.makeText(this, getString(R.string.no_param_error), Toast.LENGTH_LONG).show()
        } else {
            viewModel.fetchRecipeDetails(recipeId)
            showIndeterminateModalDialog()
        }
    }

    private fun onResponse(response: Response<RecipeDetails>) {
        hideIndeterminateModalDialog()
        if (response.isSuccessful) {
            val recipeDetails = response.body()
            if (recipeDetails == null) {
                Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
            } else {
                updateUI(recipeDetails)
            }
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(details: RecipeDetails) {
        val EMPTY_STRING = getString(R.string.empty_string)
        this@RecipeDetailActivity.title = details.title

        Glide.with(this@RecipeDetailActivity)
            .load(details.image)
            .into(recipeImage)

        var rating = details.rating ?: 0
        val fillStar = resources.getDrawable(R.drawable.fill_star, theme)
        val unfilledStar = resources.getDrawable(R.drawable.unfilled_star, theme)
        for (i in 1..5) {
            val star = ImageView(this@RecipeDetailActivity)
            val image = if (rating > 0) {
                fillStar.also { rating-- }
            } else {
                unfilledStar
            }
            Glide.with(this@RecipeDetailActivity)
                .load(image)
                .override(150, 150)
                .into(star)

            starsContainer.addView(star)
        }

        recipeInstructions.text = details.instructions ?: EMPTY_STRING
    }
}
