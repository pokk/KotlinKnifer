package com.devrapid.kotlinshaver

inline fun <reified T> castOrNull(from: Any?) = from as? T

inline fun <reified T> cast(from: Any?): T {
    if (null == from) throw NullPointerException("The null object can't cast.")

    try {
        return T::class.java.cast(from)
    }
    catch (castExcept: ClassCastException) {
        throw ClassCastException("Class casting failed.")
    }
}
