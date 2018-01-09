package com.devrapid.kotlinknifer

import android.app.Fragment
import android.app.FragmentManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View

/**
 * @author  jieyi
 * @since   11/8/17
 */
/** **************************************************************************
 * Fragment for [Fragment]
 ** **************************************************************************/
/**
 * Adds a [Fragment] to this manager's layout.
 *
 * @param containerViewId The container view to where add the fragment.
 * @param fragment The fragment to be added.
 * @param needBack Set that it can back to previous fragment.
 * @param sharedElements Shared element objects and ids from layout xml [android:transitionName].
 *
 * @return the identifier of this transaction's back stack entry.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun FragmentManager.addFragment(containerViewId: Int,
                                fragment: Fragment,
                                needBack: Boolean = false,
                                sharedElements: HashMap<View, String> = hashMapOf()): Int = beginTransaction().apply {
    replace(containerViewId, fragment, fragment.javaClass.name)
    sharedElements.forEach { value -> addSharedElement(value.key, value.value) }
    if (needBack)
        addToBackStack(fragment.javaClass.name)
}.commit()

/**
 * Pop a [Fragment] from the [FragmentManager].
 *
 * @return is success to pop a [Fragment].
 */
inline fun FragmentManager.popFragment(): Boolean = if (0 < backStackEntryCount) {
    popBackStackImmediate()
    true
}
else
    false

/**
 * Clear all [Fragment] in the stack.
 */
inline fun FragmentManager.popAllFragment() {
    // Optimized by Kotlin.
    for (i in 0..backStackEntryCount - 1) {
        popFragment()
    }
}

/**
 * Remove a specific [Fragment] from [FragmentManager] stack.
 *
 * @param fragment Specific assigned [Fragment].
 */
@RequiresApi(Build.VERSION_CODES.N)
inline fun FragmentManager.removeFragment(fragment: Fragment) = beginTransaction().remove(
    fragment).commitNow()
