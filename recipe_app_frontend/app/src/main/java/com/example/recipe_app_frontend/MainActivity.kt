package com.example.recipe_app_frontend

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.example.recipe_app_frontend.navigation.Navigator
import com.example.recipe_app_frontend.ui.HomeFragment

/**
 * Main Activity for Android TV
 * Extends FragmentActivity for Leanback compatibility
 */
class MainActivity : FragmentActivity() {

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = Navigator(this)

        // Wire side navigation
        findViewById<Button>(R.id.nav_home).setOnClickListener {
            navigator.show(HomeFragment.newInstance(), addToBackStack = true)
        }
        findViewById<Button>(R.id.nav_categories).setOnClickListener {
            navigator.show(HomeFragment.newInstance(), addToBackStack = true)
        }
        findViewById<Button>(R.id.nav_favorites).setOnClickListener {
            navigator.show(HomeFragment.newInstance(), addToBackStack = true)
        }
        findViewById<Button>(R.id.nav_settings).setOnClickListener {
            navigator.show(HomeFragment.newInstance(), addToBackStack = true)
        }

        // Initial fragment
        if (supportFragmentManager.backStackEntryCount == 0) {
            navigator.show(HomeFragment.newInstance(), addToBackStack = true)
        }

        // Optional: listen for fragment views to adjust decorations (no-op if not Home)
        supportFragmentManager.addOnBackStackChangedListener {
            val frag = supportFragmentManager.findFragmentById(R.id.fragment_container)
            // No hard dependency; HomeFragment handles its own spacing.
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Handle TV remote BACK to navigate fragments
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                val handled = navigator.back()
                if (!handled) finish()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}
