package com.devrapid.kotlinknifer

import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.ref.PhantomReference
import java.lang.ref.ReferenceQueue
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegate methods.
 *
 * @author  jieyi
 * @since   6/12/17
 */
/**
 * Wrap the [SoftReference] by kotlin delegate. Don't have to use [get]/[set] method
 * for accessing or assigning a variable each of times.
 *
 * ```
 * val data by SoftRef(TestData())
 * ```
 */
class SoftRef<T>(
    default: T? = null,
    private val queue: ReferenceQueue<T>? = null
) : ReadWriteProperty<Any?, T?> {
    private var variable: SoftReference<T>?

    init {
        variable = initSoftReference(default, queue)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = variable?.get()

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        variable = initSoftReference(value, queue)
    }

    private fun initSoftReference(value: T? = null, queue: ReferenceQueue<T>? = null) =
        value?.run {
            queue?.let { SoftReference(this, it) } ?: SoftReference(this)
        }
}

/**
 * Wrap the [WeakReference] by kotlin delegate. Don't have to use [get]/[set] method
 * for accessing or assigning a variable each of times.
 *
 * ```
 * val data by SoftRef(TestData())
 * ```
 */
class WeakRef<T>(
    default: T? = null,
    private val queue: ReferenceQueue<T>? = null
) : ReadWriteProperty<Any?, T?> {
    private var variable: WeakReference<T>?

    init {
        variable = initWeakReference(default, queue)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = variable?.get()

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        variable = initWeakReference(value, queue)
    }

    private fun initWeakReference(value: T? = null, queue: ReferenceQueue<T>? = null) =
        value?.run {
            queue?.let { WeakReference(this, it) } ?: WeakReference(this)
        }
}

/**
 * Wrap the [PhantomReference] by kotlin delegate. Don't have to use [get]/[set] method
 * for accessing or assigning a variable each of times.
 *
 * ```
 * val data by SoftRef(TestData())
 * ```
 */
class PhantomRef<T>(
    default: T? = null,
    private val queue: ReferenceQueue<T>
) : ReadWriteProperty<Any?, T?> {
    private var variable: PhantomReference<T>?

    init {
        variable = default?.let { PhantomReference(it, queue) }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = variable?.get()

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        variable = value?.let { PhantomReference(it, queue) }
    }
}

/** Delegate the shared preferences variable. */
class SharedPrefs<T>(var defaultValue: T, val objectType: Class<T>? = null, var onChange: (() -> Unit)? = null) :
    ReadWriteProperty<Any, T> {
    companion object {
        var prefs: SharedPreferences by Delegates.notNull()
        fun setPrefSettings(pref: SharedPreferences) {
            prefs = pref
        }
    }

    // For storing an object to the preferences. This method needs to add the `Gson` dependency.
    private val gson by lazy { Gson() }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val name = property.name

        return when (this.defaultValue) {
            is Boolean -> prefs.getBoolean(name, defaultValue as Boolean) as T
            is Float -> prefs.getFloat(name, defaultValue as Float) as T
            is Int -> prefs.getInt(name, defaultValue as Int) as T
            is Long -> prefs.getLong(name, defaultValue as Long) as T
            is String -> prefs.getString(name, defaultValue as String) as T
            is Set<*> -> prefs.getStringSet(name, defaultValue as Set<String>) as T
        // Using json format to deserialize a string to an object.
            else -> this.gson.fromJson(prefs.getString(name,
                                                       null) ?: throw KotlinNullPointerException("There is no kind of $name was stored in the shared preferences."),
                                       objectType)
        }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val name = property.name

        prefs.edit().also {
            when (this.defaultValue) {
                is Boolean -> it.putBoolean(name, value as Boolean)
                is Float -> it.putFloat(name, value as Float)
                is Int -> it.putInt(name, value as Int)
                is Long -> it.putLong(name, value as Long)
                is String -> it.putString(name, value as String)
                is Set<*> -> it.putStringSet(name, value as Set<String>)
            // Using json format to serialize an object to a string.
                else -> it.putString(name, this.gson.toJson(value))
            }
            this.onChange?.invoke()
        }.apply()
    }
}
