package com.devrapid.kotlinknifer

import android.util.Log

fun logv(vararg msg: Any?) = Logs.v(*msg)
fun logd(vararg msg: Any?) = Logs.d(*msg)
fun logi(vararg msg: Any?) = Logs.i(*msg)
fun logw(vararg msg: Any?) = Logs.w(*msg)
fun loge(vararg msg: Any?) = Logs.e(*msg)

fun logv(msg: Any?) = Logs.v(msg)
fun logd(msg: Any?) = Logs.d(msg)
fun logi(msg: Any?) = Logs.i(msg)
fun logw(msg: Any?) = Logs.w(msg)
fun loge(msg: Any?) = Logs.e(msg)

internal object Logs {
    var _IS_DEBUG = true // Debug mode's switch, default is turn off.
    var TAG = "MY_LOG" // TAG
    private const val COLON = ":"
    private const val LEFT_PARENTHESIS = "("
    private const val RIGHT_PARENTHESIS = ")"
    private const val SPACE_STRING = " "
    private const val METHOD_INDEX = 4
    private val lockLog = Any() // Avoid the threading's race condition.
    private val strBuilder = StringBuilder() // String builder.

    /**
     * VERBOSE log.
     *
     * @param msg output message
     */
    internal fun v(vararg msg: Any?) = showLog(*msg)

    /**
     * DEBUG log.
     *
     * @param msg output message
     */
    internal fun d(vararg msg: Any?) = showLog(*msg)

    /**
     * INFORMATION log.
     *
     * @param msg output message
     */
    internal fun i(vararg msg: Any?) = showLog(*msg)

    /**
     * WARNING log.
     *
     * @param msg output message
     */
    internal fun w(vararg msg: Any?) = showLog(*msg)

    /**
     * ERROR log.
     *
     * @param msg output message
     */
    internal fun e(vararg msg: Any?) {
        if (1 == msg.size && msg[0] is Exception) {
            LogWrapper().debugCheck(Log::class.java, getExceptionMsg(msg[0] as Exception))
        }
        else {
            showLog(*msg)
        }
    }

    /**
     * Wrap the checking condition and msg log.
     */
    private class LogWrapper {
        /**
         * Check debug mode and sync.
         *
         * @param cls class name.
         * @param msg message content.
         * @return success or fail.
         */
        fun debugCheck(cls: Class<*>, msg: Any): Boolean {
            // Checking the debug mode.
            if (_IS_DEBUG) {
                // Because the level of the function depth, the index is 4.
                var methodName = Thread.currentThread().stackTrace[METHOD_INDEX].methodName.substringBefore("$")
                // Only exception msg only is 3.
                if (1 < methodName.length) {
                    methodName = Thread.currentThread().stackTrace[METHOD_INDEX - 1].methodName.substringBefore("$")
                }
                // Avoid the race condition.
                synchronized(lockLog) {
                    return logMsg(cls, methodName, msg)
                }
            }
            return true
        }

        /**
         * Invoke the Log method to show the log msg.
         *
         * @param cls        class name.
         * @param methodName method name.
         * @param msg        message content.
         * @return success or fail.
         */
        private fun logMsg(cls: Class<*>, methodName: String, msg: Any): Boolean {
            try {
                val method = cls.getDeclaredMethod(methodName, String::class.java, String::class.java)
                method.invoke(null, TAG, msg)
            }
            catch (e: Exception) {
                e.printStackTrace()
                return false
            }

            return true
        }
    }

    /**
     * Wrapper the show log method.
     *
     * @param msg output message
     */
    private fun showLog(vararg msg: Any?) {
        val msgString = combineInputArguments(*msg)

        LogWrapper().debugCheck(Log::class.java, getLogMsg(msgString))
    }

    private fun newCombinedString(block: StringBuilder.() -> Unit) =
        strBuilder.apply { setLength(0) }.apply(block).toString()

    /**
     * Combine arguments to a string.
     *
     * @param values multiple arguments.
     * @return output string message
     */
    private fun combineInputArguments(vararg values: Any?) = newCombinedString {
        values.filterNotNull()
            .forEach { append(it.toString()).append(SPACE_STRING) }
    }

    /**
     * Combine the meta information and msg.
     *
     * @param msg log message.
     * @return meta information + msg.
     */
    private fun getLogMsg(msg: String?) = getMetaInfo(null == msg) + COLON + (msg.orEmpty())

    /**
     * Combine the meta information and exception msg.
     *
     * @param msg exception msg.
     * @return meta information + exception msg.
     */
    private fun getExceptionMsg(msg: Exception) = newCombinedString {
        Throwable().stackTrace[6].let { append("${it.methodName}(${it.fileName}:${it.lineNumber}): ${msg.message}\n") }
        msg.stackTrace.forEach { append(it).append("\n") }
    }

    /**
     * Get the file name, method name, line number.
     *
     * @param isNullString the input string is null no not.
     * @return the format is "methodName(fileName:line)"
     */
    private fun getMetaInfo(isNullString: Boolean): String {
        // Because the nest function so we can get in stack index 4. 
        val tempIndex = METHOD_INDEX + 1
        val stackIndex = if (isNullString) tempIndex + 1 else tempIndex
        val ste = Throwable().stackTrace

        return newCombinedString {
            append(ste[stackIndex].methodName)
            append(LEFT_PARENTHESIS)
            append(ste[stackIndex].fileName)
            append(COLON)
            append(ste[stackIndex].lineNumber)
            append(RIGHT_PARENTHESIS)
        }
    }
}
