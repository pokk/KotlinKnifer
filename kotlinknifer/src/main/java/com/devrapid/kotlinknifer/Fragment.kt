@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.annotation.SuppressLint
import android.app.Fragment
import android.app.FragmentManager
import android.os.Build
import android.view.View
import java.util.Stack

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
                                fragmentStack: Stack<Fragment>? = null,
                                sharedElements: HashMap<View, String> = hashMapOf()) = beginTransaction().also {
    it.replace(containerViewId, fragment, fragment.javaClass.name)
    sharedElements.forEach { value -> it.addSharedElement(value.key, value.value) }
    if (needBack) {
        it.addToBackStack(fragment.javaClass.name)
        fragmentStack?.push(fragment)
    }
}.commit()

/**
 * Pop a [Fragment] from the [FragmentManager].
 *
 * @return is success to pop a [Fragment].
 */
inline fun FragmentManager.popFragment(fragmentStack: Stack<Fragment>? = null) =
    if (0 < backStackEntryCount) {
        popBackStackImmediate()
        fragmentStack?.safePop()
        true
    }
    else
        false

/**
 * Clear all [Fragment] in the stack.
 */
inline fun FragmentManager.popAllFragment(fragmentStack: Stack<Fragment>? = null) =
    (0..backStackEntryCount - 1).forEach { popFragment(fragmentStack) }

/**
 * Remove a specific [Fragment] from [FragmentManager] stack.
 *
 * @param fragment Specific assigned [Fragment].
 */
inline fun FragmentManager.removeFragment(fragment: Fragment, fragmentStack: Stack<Fragment>? = null) {
    val transaction = beginTransaction().apply {
        fragmentStack?.remove(fragment)
    }.remove(fragment)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        transaction.commitNow()
    else
        transaction.commit()
}

/**
 * Remove all [Fragment] from [FragmentManager] stack.
 */
@SuppressLint("RestrictedApi")
fun FragmentManager.removeRecursiveFragment(fragmentStack: Stack<Fragment>? = null) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        fragments?.forEach {
            it?.let { f ->
                it.childFragmentManager?.fragments?.forEach {
                    it?.let { f.childFragmentManager.removeFragment(it, fragmentStack) }
                }
                removeFragment(f, fragmentStack)
            }
        }
    }
    else {
        TODO("VERSION.SDK_INT < O")
    }

/**
 * Testing code. For showing all fragments and children fragments.
 */
@SuppressLint("RestrictedApi")
fun FragmentManager.showAllFragment() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        fragments?.forEach {
            it?.let { it.childFragmentManager?.fragments?.forEach { Logs.d("child!!!! : $it") } }
        }
    }
    else {
        TODO("VERSION.SDK_INT < O")
    }