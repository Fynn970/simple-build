package com.xfg.simple_build.xlog

class XStackTraceUtil {

    companion object{

        fun getCropReadStackTrace(stackTrace: Array<StackTraceElement?>, ignorePackage: String, maxDepth: Int): Array<StackTraceElement?> {
            return cropStackTrace(getRealStackTrace(stackTrace, ignorePackage), maxDepth)
        }

        /**
         * 获取出忽略包外的堆栈信息
         */
        private fun getRealStackTrace(stackTrace: Array<StackTraceElement?>, ignorePackage: String): Array<StackTraceElement?> {
            var ignoreDepth = 0
            val allDepth = stackTrace.size
            var className = ""
            for (i in allDepth - 1 downTo 0){
                className = stackTrace[i]!!.className
                if (ignorePackage != "" && className.startsWith(ignorePackage)){
                    ignoreDepth = i + 1
                    break
                }
            }
            val realDepth = allDepth - ignoreDepth
            val realStack = arrayOfNulls<StackTraceElement>(realDepth)
            System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth)
            return realStack
        }

        /**
         * 裁剪堆栈信息
         */
        private fun cropStackTrace(callStack: Array<StackTraceElement?>, maxDepth: Int): Array<StackTraceElement?> {
            var readDepth = callStack.size
            if (maxDepth > 0){
                readDepth = Math.min(maxDepth, readDepth)
            }
            val realStack = arrayOfNulls<StackTraceElement>(readDepth)
            System.arraycopy(callStack, 0, realStack, 0, readDepth)
            return realStack
        }
    }
}