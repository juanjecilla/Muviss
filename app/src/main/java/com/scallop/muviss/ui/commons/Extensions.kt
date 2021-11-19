package com.scallop.muviss.ui.commons

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment

fun View.visible(visible: Boolean, animate: Boolean = true) {
    if (visible) {
        if (animate) {
            animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    visibility = View.VISIBLE
                }
            })
        } else {
            visibility = View.VISIBLE
        }
    } else {
        this.visibility = View.GONE
    }
}

fun View.visibleWithExpandAnimation(visible: Boolean) {
    if (visible) {
        if (visibility != View.VISIBLE) {
            visible(true, animate = false)
            val expandIn: Animation = AnimationUtils.loadAnimation(
                context,
                resources.getIdentifier("expand_in", "anim", context.packageName)
            )
            startAnimation(expandIn)
        }
    } else {
        if (visibility != View.GONE) {
            val expandIn: Animation = AnimationUtils.loadAnimation(
                context,
                resources.getIdentifier("expand_out", "anim", context.packageName)
            )
            startAnimation(expandIn)
            visible(false, animate = false)
        }
    }
}

fun EditText.on(actionId: Int, func: () -> Unit) {
    setOnEditorActionListener { _, receivedActionId, _ ->

        if (actionId == receivedActionId) {
            func()
        }

        true
    }
}

fun Fragment.hideKeyboard() {
    val inputManager: InputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        activity?.currentFocus?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}

inline fun <T : Any> withNotNull(item: T?, closure: (T) -> Unit) {
    with(item) {
        this?.let(closure)
    }
}

fun <T : Any, R : Any> Collection<T?>.whenAllNotNull(block: (List<T>) -> R) {
    if (this.all { it != null }) {
        block(this.filterNotNull()) // or do unsafe cast to non null collectino
    }
}

inline fun <S, T : S> List<T>.reduceRightDefault(
    defaultIfEmpty: S,
    operation: (T, acc: S) -> S
): S {
    return if (isEmpty()) defaultIfEmpty
    else reduceRight(operation)
}
