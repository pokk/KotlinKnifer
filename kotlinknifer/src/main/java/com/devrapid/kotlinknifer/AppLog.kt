package taiwan.no1.app.utilies

import android.util.Log

/**
 * Log Module
 *
 * @author Jieyi
 * @since 8/1/15
 */

object AppLog {
    var _IS_DEBUG: Boolean = java.lang.Boolean.TRUE  // Debug mode's switch, default is turn off.
    var TAG: String = "MY_LOG"  // TAG

    private const val COLON: String = ":"
    private const val LEFT_PARENTHESIS: String = "("
    private const val RIGHT_PARENTHESIS: String = ")"
    private const val SPACE_STRING: String = " "
    private val lockLog: Any = Any()  // Avoid the threading's race condition.
    private val strBuilder: StringBuilder = StringBuilder()  // String builder.

    /**
     * VERBOSE log.
     *
     * @param msg output message
     */
    @JvmStatic fun v(vararg msg: Any?) = this.showLog(*msg)

    /**
     * DEBUG log.
     *
     * @param msg output message
     */
    @JvmStatic fun d(vararg msg: Any?) = this.showLog(*msg)

    /**
     * INFORMATION log.
     *
     * @param msg output message
     */
    @JvmStatic fun i(vararg msg: Any?) = this.showLog(*msg)

    /**
     * WARNING log.
     *
     * @param msg output message
     */
    @JvmStatic fun w(vararg msg: Any?) = this.showLog(*msg)

    /**
     * ERROR log.
     *
     * @param msg output message
     */
    @JvmStatic fun e(vararg msg: Any?) {
        if (1 == msg.size && msg[0] is Exception)
            LogWrapper().debugCheck(Log::class.java, getExceptionMsg(msg[0] as Exception))
        else
            this.showLog(*msg)
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
                var methodName = Thread.currentThread().stackTrace[4].methodName
                // Only exception msg only is 3.
                if (1 < methodName.length)
                    methodName = Thread.currentThread().stackTrace[3].methodName

                // Avoid the race condition.
                synchronized(lockLog) {
                    return this.logMsg(cls, methodName, msg)
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

    private fun newCombinedString(block: StringBuilder.() -> Unit): String = this.strBuilder.apply { setLength(0) }.
            apply(block).toString()

    /**
     * Combine arguments to a string.
     *
     * @param values multiple arguments.
     * @return output string message
     */
    private fun combineInputArguments(vararg values: Any?): String = this.newCombinedString {
        values.filter { null != it }.forEach { this.append(it.toString()).append(SPACE_STRING) }
    }

    /**
     * Combine the meta information and msg.
     *
     * @param msg log message.
     * @return meta information + msg.
     */
    private fun getLogMsg(msg: String?): String = getMetaInfo(null == msg) + COLON + (msg.orEmpty())

    /**
     * Combine the meta information and exception msg.
     *
     * @param msg exception msg.
     * @return meta information + exception msg.
     */
    private fun getExceptionMsg(msg: Exception): String = this.newCombinedString {
        msg.stackTrace.forEach { this.append(it).append("\n") }
    }

    /**
     * Get the file name, method name, line number.
     *
     * @param isNullString the input string is null no not.
     * @return the format is "methodName(fileName:line)"
     */
    private fun getMetaInfo(isNullString: Boolean): String {
        // Because the nest function so we can get in stack index 4. 
        val tempIndex = 4
        val stackIndex = if (isNullString) tempIndex + 1 else tempIndex
        val ste = Throwable().stackTrace

        return this.newCombinedString {
            append(ste[stackIndex].methodName)
            append(LEFT_PARENTHESIS)
            append(ste[stackIndex].fileName)
            append(COLON)
            append(ste[stackIndex].lineNumber)
            append(RIGHT_PARENTHESIS)
        }
    }
}
