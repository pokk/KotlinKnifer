@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.annotation.SuppressLint
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
                                sharedElements: HashMap<View, String> = hashMapOf()): Int = beginTransaction().also {
    it.replace(containerViewId, fragment, fragment.javaClass.name)
    sharedElements.forEach { value -> it.addSharedElement(value.key, value.value) }
    if (needBack)
        it.addToBackStack(fragment.javaClass.name)
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
inline fun FragmentManager.popAllFragment() = (0..backStackEntryCount - 1).forEach { popFragment() }

/**
 * Remove a specific [Fragment] from [FragmentManager] stack.
 *
 * @param fragment Specific assigned [Fragment].
 */
inline fun FragmentManager.removeFragment(fragment: Fragment) = beginTransaction().remove(fragment).commitNow()

/**
 * Remove all [Fragment] from [FragmentManager] stack.
 */
@SuppressLint("RestrictedApi")
fun FragmentManager.removeRecursiveFragment() = fragments?.forEach {
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
fun FragmentManager.showAllFragment() = fragments?.forEach {
    it?.let {
        it.childFragmentManager?.fragments?.forEach { Logs.d("child!!!! : $it") }
    }
}
