package com.hzhu.networkrequestreport.report

import android.app.Application
import com.hzhu.networkrequestreport.widget.floatwindow.FloatWindow

class NetWorkReport {

    companion object {
        var canReport = false
            get() {
                return if (app == null) {
                    false
                } else {
                    field
                }
            }
        var app: Application? = null

        fun startRecorder() {
            canReport = true
            FloatWindow.init(app)
            FloatWindow.start()
        }

        fun init(app: Application) {
            this.app = app
        }

    }
}