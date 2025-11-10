package com.example.recipe_app_frontend.model

object MockData {
    val recipes: List<Recipe> = listOf(
        Recipe(
            id = "1",
            title = "Grilled Lemon Herb Chicken",
            imageUrl = "https://images.unsplash.com/photo-1553163147-622ab57be1c7?q=80&w=1080&auto=format&fit=crop",
            category = "Dinner",
            servings = 4,
            prepMinutes = 20,
            cookMinutes = 25,
            ingredients = listOf(
                Ingredient("Chicken breasts", "4"),
                Ingredient("Lemon", "1"),
                Ingredient("Olive oil", "2 tbsp"),
                Ingredient("Garlic", "3 cloves"),
                Ingredient("Rosemary", "1 tsp"),
                Ingredient("Salt & Pepper", "to taste")
            ),
            steps = listOf(
                Step(1, "Marinate chicken with lemon juice, oil, garlic, rosemary, salt and pepper for 15 minutes."),
                Step(2, "Preheat grill to medium-high heat."),
                Step(3, "Grill chicken 6-7 minutes per side until cooked through."),
                Step(4, "Rest for 5 minutes and serve with lemon wedges.")
            ),
            videoUrl = null
        ),
        Recipe(
            id = "2",
            title = "Classic Margherita Pizza",
            imageUrl = "https://images.unsplash.com/photo-1548365328-9f547fb09567?q=80&w=1080&auto=format&fit=crop",
            category = "Lunch",
            servings = 2,
            prepMinutes = 30,
            cookMinutes = 12,
            ingredients = listOf(
                Ingredient("Pizza dough", "1"),
                Ingredient("Tomato sauce", "1/2 cup"),
                Ingredient("Mozzarella", "150 g"),
                Ingredient("Fresh basil", "a handful"),
                Ingredient("Olive oil", "1 tbsp")
            ),
            steps = listOf(
                Step(1, "Preheat oven to highest setting with a pizza stone."),
                Step(2, "Stretch dough, add sauce, mozzarella and basil."),
                Step(3, "Bake for 8-12 minutes until crust is golden."),
                Step(4, "Drizzle olive oil and slice.")
            ),
            videoUrl = null
        ),
        Recipe(
            id = "3",
            title = "Avocado Toast Deluxe",
            imageUrl = "https://images.unsplash.com/photo-1541516160079-ff13f6b0313a?q=80&w=1080&auto=format&fit=crop",
            category = "Breakfast",
            servings = 1,
            prepMinutes = 10,
            cookMinutes = 5,
            ingredients = listOf(
                Ingredient("Sourdough bread", "2 slices"),
                Ingredient("Avocado", "1"),
                Ingredient("Cherry tomatoes", "6"),
                Ingredient("Lemon juice", "1 tsp"),
                Ingredient("Red pepper flakes", "pinch")
            ),
            steps = listOf(
                Step(1, "Toast bread to preference."),
                Step(2, "Mash avocado with lemon juice, salt and pepper."),
                Step(3, "Spread on toast, top with tomatoes and pepper flakes.")
            ),
            videoUrl = null
        )
    )

    fun search(query: String): List<Recipe> {
        if (query.isBlank()) return recipes
        val q = query.lowercase()
        return recipes.filter { r ->
            r.title.lowercase().contains(q) ||
                r.category.lowercase().contains(q) ||
                r.ingredients.any { it.name.lowercase().contains(q) }
        }
    }

    fun byCategory(category: String): List<Recipe> =
        recipes.filter { it.category.equals(category, ignoreCase = true) }

    fun byId(id: String): Recipe? = recipes.find { it.id == id }
}
