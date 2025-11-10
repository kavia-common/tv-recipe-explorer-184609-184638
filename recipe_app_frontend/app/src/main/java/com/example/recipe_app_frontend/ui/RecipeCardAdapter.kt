package com.example.recipe_app_frontend.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe_app_frontend.R
import com.example.recipe_app_frontend.model.Recipe

/**
 * PUBLIC_INTERFACE
 * Adapter for displaying recipe cards in a TV-optimized grid.
 */
class RecipeCardAdapter(
    private val onClick: (Recipe) -> Unit
) : ListAdapter<Recipe, RecipeCardAdapter.CardVH>(RecipeDiff) {

    object RecipeDiff : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_card, parent, false)
        return CardVH(v, onClick)
    }

    override fun onBindViewHolder(holder: CardVH, position: Int) {
        holder.bind(getItem(position))
    }

    class CardVH(
        itemView: View,
        private val onClick: (Recipe) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.card_image)
        private val title: TextView = itemView.findViewById(R.id.card_title)
        private val meta: TextView = itemView.findViewById(R.id.card_meta)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            meta.text = "${recipe.category} • ${recipe.cookMinutes}m"
            Glide.with(itemView)
                .load(recipe.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(image)

            itemView.setOnClickListener { onClick(recipe) }

            // Focus scale animation
            itemView.setOnFocusChangeListener { v, hasFocus ->
                val scale = if (hasFocus) 1.08f else 1.0f
                v.animate()
                    .scaleX(scale)
                    .scaleY(scale)
                    .setDuration(120)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .start()
            }
        }
    }
}
