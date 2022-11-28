package com.xfg.simple_build.xlog

class XThreadFormatter: XLogFormatter<Thread> {
    override fun format(data: Thread): String {
        return "Thread: " + data.name
    }
}