package com.example.recipe_app_frontend.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app_frontend.R

/**
 * Helper to attach item decoration once the layout is ready.
 */
fun Fragment.setupGridGutters(root: View) {
    val grid: RecyclerView = root.findViewById(R.id.recipes_grid)
    val spacing = resources.getDimensionPixelSize(R.dimen.grid_gutter)
    if (grid.itemDecorationCount == 0) {
        grid.addItemDecoration(GridSpacingItemDecoration(spacing))
    }
}
