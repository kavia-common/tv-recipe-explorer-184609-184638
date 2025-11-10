package com.example.recipe_app_frontend.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app_frontend.R
import com.example.recipe_app_frontend.model.MockData
import com.example.recipe_app_frontend.model.Recipe
import com.example.recipe_app_frontend.navigation.Navigator

/**
 * PUBLIC_INTERFACE
 * HomeFragment displays a grid of recipes with a search bar for Android TV.
 * D-pad navigation is optimized and focus moves predictably across grid cells.
 */
class HomeFragment : Fragment() {

    private lateinit var grid: RecyclerView
    private lateinit var searchInput: EditText
    private lateinit var emptyText: TextView
    private lateinit var adapter: RecipeCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        grid = view.findViewById(R.id.recipes_grid)
        searchInput = view.findViewById(R.id.search_input)
        emptyText = view.findViewById(R.id.empty_text)

        // TV-friendly padding within safe viewport
        view.updatePadding(
            left = resources.getDimensionPixelSize(R.dimen.content_padding),
            right = resources.getDimensionPixelSize(R.dimen.content_padding),
            top = resources.getDimensionPixelSize(R.dimen.content_padding),
            bottom = resources.getDimensionPixelSize(R.dimen.content_padding)
        )

        val spanCount = 4 // conservative for TV height with card aspect ratio
        grid.layoutManager = object : GridLayoutManager(requireContext(), spanCount) {
            override fun onInterceptFocusSearch(focused: View, direction: Int): View? {
                // Let RecyclerView handle default focus search
                return super.onInterceptFocusSearch(focused, direction)
            }
        }.apply {
            orientation = RecyclerView.VERTICAL
        }

        adapter = RecipeCardAdapter(onClick = ::openDetail)
        grid.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(grid, false)

        // Add item spacing decoration
        val spacing = resources.getDimensionPixelSize(R.dimen.grid_gutter)
        if (grid.itemDecorationCount == 0) {
            grid.addItemDecoration(GridSpacingItemDecoration(spacing))
        }

        // Load initial data
        submitList(MockData.recipes)

        // Search using DPAD input; Enter triggers filter; Back clears focus
        searchInput.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_ENTER, KeyEvent.KEYCODE_DPAD_CENTER -> {
                        performSearch(searchInput.text.toString())
                        return@setOnKeyListener true
                    }
                    KeyEvent.KEYCODE_BACK -> {
                        if (searchInput.text.isNotEmpty()) {
                            searchInput.setText("")
                            performSearch("")
                            return@setOnKeyListener true
                        }
                    }
                }
            }
            false
        }

        // Let grid take focus first so users can start browsing immediately
        grid.isFocusable = true
        grid.requestFocus()
    }

    private fun performSearch(query: String) {
        val results = MockData.search(query)
        submitList(results)
    }

    private fun submitList(list: List<Recipe>) {
        adapter.submitList(list)
        emptyText.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun openDetail(recipe: Recipe) {
        val frag = RecipeDetailFragment.newInstance(recipe.id)
        (activity?.let { Navigator(it) })?.show(frag, addToBackStack = true)
    }

    companion object {
        // PUBLIC_INTERFACE
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
