package com.example.recipe_app_frontend.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * PUBLIC_INTERFACE
 * Adds spacing between grid items equally on all sides.
 */
class GridSpacingItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(spacing, spacing, spacing, spacing)
    }
}
