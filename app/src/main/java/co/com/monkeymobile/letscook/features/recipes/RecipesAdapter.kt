package co.com.monkeymobile.letscook.features.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import co.com.monkeymobile.letscook.R

class RecipesAdapter(private var recipes: List<Recipe>, private val listener: RecipeListener) :
    RecyclerView.Adapter<RecipesAdapter.ItemViewHolder>() {

    interface RecipeListener {
        fun onRecipeClick(recipe: Recipe)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val adapterContainer =
            itemView.findViewById<ConstraintLayout>(R.id.adapterContainer)
        private val attributeTwo = itemView.findViewById<TextView>(R.id.recipeTitle)

        fun bind(recipe: Recipe, listener: RecipeListener) {
            attributeTwo.text = recipe.title
            adapterContainer.setOnClickListener { listener.onRecipeClick(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recipes_adapter, parent, false)
    )

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(recipes[position], listener)

    fun updateContent(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}