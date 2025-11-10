# Recipe TV - Android TV App

This container provides an Android TV app for browsing and viewing recipes using the Ocean Professional theme.

Features:
- Home screen with side navigation (Home, Categories, Favorites, Settings)
- Search input (client-side) for filtering placeholder recipe data
- Grid of recipe cards optimized for D-pad navigation with focus glow and scale
- Recipe detail screen with hero image, ingredients, and step-by-step instructions
- Back navigation via TV remote

Build and Run:
1. Open this project in Android Studio (Giraffe+), or use Gradle.
2. Select a TV emulator (Android TV) or a physical Android TV device.
3. Build and run the `app` module.

Navigation:
- Use D-pad arrows to move focus across navigation and grid items.
- Press OK/Enter to open a recipe.
- Press Back to return to the previous screen.
- Focus the search field and press OK/Enter to apply the current query.

Design:
- Ocean Professional theme with primary #2563EB, secondary #F59E0B, background #f9fafb, surface #ffffff, text #111827.
- Rounded corners, subtle shadows, and focus glow.
