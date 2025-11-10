package com.example.recipe_app_frontend.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.recipe_app_frontend.R

/**
 * PUBLIC_INTERFACE
 * Navigator handles fragment transitions within MainActivity on Android TV.
 */
class Navigator(private val activity: FragmentActivity) {

    // PUBLIC_INTERFACE
    fun show(fragment: Fragment, addToBackStack: Boolean = true) {
        val tx = activity.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.animator.fade_in,
                android.R.animator.fade_out,
                android.R.animator.fade_in,
                android.R.animator.fade_out
            )
            .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
        if (addToBackStack) {
            tx.addToBackStack(fragment::class.java.simpleName)
        }
        tx.commit()
    }

    // PUBLIC_INTERFACE
    fun back(): Boolean {
        val fm = activity.supportFragmentManager
        return if (fm.backStackEntryCount > 1) {
            fm.popBackStack()
            true
        } else {
            false
        }
    }
}
