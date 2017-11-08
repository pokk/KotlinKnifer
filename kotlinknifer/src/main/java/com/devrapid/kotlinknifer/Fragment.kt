@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View

/**
 * @author  jieyi
 * @since   2/15/17
 */

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
fun FragmentManager.addFragment(containerViewId: Int,
                                fragment: Fragment,
                                needBack: Boolean = false,
                                sharedElements: HashMap<View, String>? = null): Int = this.beginTransaction().also {
    it.replace(containerViewId, fragment, fragment.javaClass.name)
    sharedElements?.forEach { value -> it.addSharedElement(value.key, value.value) }
    if (needBack)
        it.addToBackStack(fragment.javaClass.name)
}.commit()

/**
 * Pop a [Fragment] from the [FragmentManager].
 *
 * @return is success to pop a [Fragment].
 */
inline fun FragmentManager.popFragment(): Boolean = if (0 < this.backStackEntryCount) {
    this.popBackStackImmediate()
    true
}
else
    false

/**
 * Clear all [Fragment] in the stack.
 */
inline fun FragmentManager.popAllFragment() = (0..this.backStackEntryCount - 1).forEach { this.popFragment() }

/**
 * Remove a specific [Fragment] from [FragmentManager] stack.
 *
 * @param fragment Specific assigned [Fragment].
 */
inline fun FragmentManager.removeFragment(fragment: Fragment) = this.beginTransaction().remove(fragment).commitNow()

/**
 * Remove all [Fragment] from [FragmentManager] stack.
 */
@SuppressLint("RestrictedApi")
fun FragmentManager.removeRecursiveFragment() = this.fragments?.forEach {
    it?.let { f ->
        it.childFragmentManager?.fragments?.forEach {
            it?.let { f.childFragmentManager.removeFragment(it) }
        }
    }
}

/**
 * Testing code. For showing all fragments and children fragments.
 */
@SuppressLint("RestrictedApi")
fun FragmentManager.showAllFragment() = this.fragments?.forEach {
    it?.let {
        it.childFragmentManager?.fragments?.forEach { Logs.d("child!!!! : $it") }
    }
}

/** **************************************************************************
 * Fragment for [android.app.Fragment]
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
fun android.app.FragmentManager.addFragment(containerViewId: Int,
                                            fragment: android.app.Fragment,
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
inline fun android.app.FragmentManager.popFragment(): Boolean = if (0 < backStackEntryCount) {
    popBackStackImmediate()
    true
}
else
    false

/**
 * Clear all [Fragment] in the stack.
 */
inline fun android.app.FragmentManager.popAllFragment() {
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
inline fun android.app.FragmentManager.removeFragment(fragment: android.app.Fragment) = beginTransaction().remove(
    fragment).commitNow()

