package com.example.recipe_app_frontend.model

/**
 * PUBLIC_INTERFACE
 * Recipe data model representing a TV-friendly recipe entity.
 */
data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String?,
    val category: String,
    val servings: Int,
    val prepMinutes: Int,
    val cookMinutes: Int,
    val ingredients: List<Ingredient>,
    val steps: List<Step>,
    val videoUrl: String? = null,
)

/**
 * PUBLIC_INTERFACE
 * Ingredient data model
 */
data class Ingredient(
    val name: String,
    val quantity: String
)

/**
 * PUBLIC_INTERFACE
 * Step data model
 */
data class Step(
    val index: Int,
    val instruction: String,
    val imageUrl: String? = null
)
