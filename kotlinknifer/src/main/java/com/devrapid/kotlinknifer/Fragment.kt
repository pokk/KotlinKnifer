@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import java.util.*

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
    sharedElements?.forEach { k, v -> it.addSharedElement(k, v) }
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
fun FragmentManager.showAllFragment() = this.fragments?.forEach {
    it?.let {
        AppLog.v("parent : $it")
        it.childFragmentManager?.fragments?.forEach { AppLog.d("child!!!! : $it") }
    }
}
