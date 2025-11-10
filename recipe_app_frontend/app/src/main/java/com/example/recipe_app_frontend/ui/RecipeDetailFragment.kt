package com.example.recipe_app_frontend.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.recipe_app_frontend.R
import com.example.recipe_app_frontend.model.MockData
import com.example.recipe_app_frontend.model.Recipe

/**
 * PUBLIC_INTERFACE
 * RecipeDetailFragment shows a large hero image, meta info, ingredients and steps.
 */
class RecipeDetailFragment : Fragment() {

    private var recipeId: String? = null
    private var recipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeId = arguments?.getString(ARG_ID)
        recipe = recipeId?.let { MockData.byId(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val r = recipe ?: return
        val hero: ImageView = view.findViewById(R.id.hero_image)
        val title: TextView = view.findViewById(R.id.detail_title)
        val meta: TextView = view.findViewById(R.id.detail_meta)
        val ingContainer: LinearLayout = view.findViewById(R.id.ingredients_container)
        val stepsContainer: LinearLayout = view.findViewById(R.id.steps_container)
        val backBtn: ImageButton = view.findViewById(R.id.back_button)

        Glide.with(this)
            .load(r.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_image_placeholder)
            .into(hero)

        title.text = r.title
        val metaText = getString(R.string.servings, r.servings) +
                "  •  " + getString(R.string.prep_time, r.prepMinutes) +
                "  •  " + getString(R.string.cook_time, r.cookMinutes)
        meta.text = metaText

        // Ingredients
        ingContainer.removeAllViews()
        r.ingredients.forEach { ing ->
            val tv = layoutInflater.inflate(R.layout.item_bullet_text, ingContainer, false) as TextView
            tv.text = "• ${ing.quantity} ${ing.name}"
            ingContainer.addView(tv)
        }

        // Steps
        stepsContainer.removeAllViews()
        r.steps.forEach { step ->
            val tv = layoutInflater.inflate(R.layout/item_step_text, stepsContainer, false) as TextView
            tv.text = "${step.index}. ${step.instruction}"
            tv.isFocusable = true
            tv.movementMethod = ScrollingMovementMethod()
            stepsContainer.addView(tv)
        }

        backBtn.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        backBtn.requestFocus()
    }

    companion object {
        private const val ARG_ID = "id"

        // PUBLIC_INTERFACE
        fun newInstance(id: String): RecipeDetailFragment {
            val f = RecipeDetailFragment()
            f.arguments = Bundle().apply { putString(ARG_ID, id) }
            return f
        }
    }
}
