package com.devrapid.kotlinknifer

import android.animation.Animator

/**
 *
 * @author  jieyi
 * @since   6/12/17
 */
fun animatorListener(init: AnimatorListenerWrapper.() -> Unit): Animator.AnimatorListener {
    val wrapper = AnimatorListenerWrapper()

    wrapper.init()

    return onListener(wrapper)
}

    /**
     * Using chain style for [animatorListener].
     */
typealias animatorWithSelf = AnimatorListener.(animator: Animator) -> Unit

class AnimatorListener : Animator.AnimatorListener {
    private var startFunction: animatorWithSelf = {}
    private var endFunction: animatorWithSelf = {}
    private var cancelFunction: animatorWithSelf = {}
    private var repeatFunction: animatorWithSelf = {}

    override fun onAnimationStart(animator: Animator) = this.startFunction(animator)
    override fun onAnimationEnd(animator: Animator) = this.endFunction(animator)
    override fun onAnimationCancel(animator: Animator) = this.cancelFunction(animator)
    override fun onAnimationRepeat(animator: Animator) = this.repeatFunction(animator)

    fun onAnimationStart(onStartFun: animatorWithSelf): AnimatorListener = this.also { it.startFunction = onStartFun }
    fun onAnimationEnd(onEndFun: animatorWithSelf): AnimatorListener = this.also { it.endFunction = onEndFun }
    fun onAnimationCancel(onCancelFun: animatorWithSelf): AnimatorListener =
        this.also { it.cancelFunction = onCancelFun }

    fun onAnimationRepeat(onRepeatFun: animatorWithSelf): AnimatorListener =
        this.also { it.repeatFunction = onRepeatFun }
}

    /**
     * Using DSL style for [animatorListener].
     */
typealias animatorEmpty = (animator: Animator) -> Unit

class AnimatorListenerWrapper {
    internal var _start: (animator: Animator) -> Unit = {}
    internal var _end: (animator: Animator) -> Unit = {}
    internal var _cancel: (animator: Animator) -> Unit = {}
    internal var _repeat: (animator: Animator) -> Unit = {}

    fun onAnimationStart(onStart: animatorEmpty) {
        this._start = onStart
    }

    fun onAnimationEnd(onEnd: animatorEmpty) {
        this._end = onEnd
    }

    fun onAnimationEndonAnimationCancel(onCancel: animatorEmpty) {
        this._cancel = onCancel
    }

    fun onAnimationRepeat(onRepeat: animatorEmpty) {
        this._repeat = onRepeat
    }
}

private fun onListener(wrapper: AnimatorListenerWrapper): Animator.AnimatorListener {
    return object : Animator.AnimatorListener {
        override fun onAnimationStart(animator: Animator) = wrapper._start(animator)
        override fun onAnimationEnd(animator: Animator) = wrapper._end(animator)
        override fun onAnimationCancel(animator: Animator) = wrapper._cancel(animator)
        override fun onAnimationRepeat(animator: Animator) = wrapper._repeat(animator)
    }
}