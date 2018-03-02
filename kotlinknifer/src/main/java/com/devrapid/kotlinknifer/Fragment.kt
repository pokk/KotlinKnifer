@file:Suppress("NOTHING_TO_INLINE")

package com.devrapid.kotlinknifer

import android.annotation.TargetApi
import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Build
import android.view.View
import java.util.Stack

/**
 * @author  jieyi
 * @since   2/15/17
 */
/**
 * Adds a [Fragment] to this manager's layout.
 * Using the [replace] method, we couldn't obtain the fragments. There a way to get it is that
 * obtaining fragment by [findFragmentByTag]. For convenient, I recommend that using a [Stack]
 * for keeping fragments.
 *
 * @param containerViewId The container view to where add the fragment.
 * @param fragment The fragment to be added.
 * @param needBack Set that it can back to previous fragment.
 * @param fragmentStack The stack for keeping the fragments.
 * @param sharedElements Shared element objects and ids from layout xml [android:transitionName].
 *
 * @return the identifier of this transaction's back stack entry.
 */
fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    needBack: Boolean = false,
    fragmentStack: Stack<Fragment>? = null,
    sharedElements: HashMap<View, String> = hashMapOf(),
    block: ((FragmentTransaction) -> Unit)? = null
) = transaction {
    block?.invoke(this)
    sharedElements.forEach { value -> addSharedElement(value.key, value.value) }
    if (needBack) {
        addToBackStack(fragment::class.java.simpleName)
        fragmentStack?.push(fragment)
    }
    replace(containerViewId, fragment, fragment::class.java.simpleName)
}

/**
 * Pop a stack from the back stack.
 *
 * @param fragmentStack The stack for keeping the fragments.
 *
 * @return is success to pop a stack.
 */
inline fun FragmentManager.popFragment(fragmentStack: Stack<Fragment>? = null) = run {
    fragmentStack?.safePop()
    popBackStackImmediate()
}

/**
 * Clear all stacks from the back stack.
 *
 * @param fragmentStack The stack for keeping the fragments.
 */
inline fun FragmentManager.popAllFragment(fragmentStack: Stack<Fragment>? = null) {
    while (true) if (!popFragment(fragmentStack)) break
}

/**
 * Remove a specific [Fragment] from [FragmentManager]'s fragments.
 * NOTE: Don't mix this function with [popFragment] or [popAllFragment].
 *
 * @param manager [FragmentManager].
 */
inline fun Fragment.removeFrom(manager: FragmentManager) = manager.transaction { remove(this@removeFrom) }

/**
 * Remove all fragments.
 */
@TargetApi(Build.VERSION_CODES.O)
inline fun FragmentManager.removeLastFragment() = fragments.lastOrNull()?.removeFrom(this)

/**
 * Add a [Fragment] and hide the current presenting fragment.
 * NOTE: Don't mix this function with [popFragment] or [popAllFragment].
 *
 * @param manager [FragmentManager].
 */
inline fun Fragment.appendTo(manager: FragmentManager) = manager.transaction {
    hide(this@appendTo)
    add(this@appendTo, this@appendTo::class.java.simpleName)
}

/**
 * Hide a assigned [Fragment].
 *
 * @param manager [FragmentManager].
 */
inline fun Fragment.hideFrom(manager: FragmentManager) = manager.transaction { hide(this@hideFrom) }

/**
 * Decorator between [beginTransaction] and [commit].
 */
inline fun FragmentManager.transaction(block: FragmentTransaction.() -> Unit) =
    beginTransaction().apply(block).commit()

/**
 * Decorator between [beginTransaction] and [commitNow].
 */
inline fun FragmentManager.transactionNow(block: FragmentTransaction.() -> Unit) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        beginTransaction().apply(block).commitNow()
    }
    else {
        TODO("VERSION.SDK_INT < N")
    }