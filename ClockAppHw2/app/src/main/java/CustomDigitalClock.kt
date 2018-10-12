/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.widget

import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.os.SystemClock
import android.provider.Settings
import android.text.format.DateFormat
import android.util.AttributeSet
import com.example.bioni.clockapphw2.CustomTime
import com.example.bioni.clockapphw2.MainActivity
import java.util.*

/**
 * Like AnalogClock, but digital.  Shows seconds.
 *
 */
class CustomDigitalClock : TextView {
    // FIXME: implement separate views for hours/minutes/seconds, so
    // proportional fonts don't shake rendering

    private var mClock: CustomTime? = null
    private var mTicker: Runnable? = null
    private var mHandler: Handler? = null

    private var mTickerStopped = false

    constructor(context: Context) : super(context) {
        initClock()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initClock()
    }

    private fun initClock() {
        if (mClock == null) {
            mClock = CustomTime()
        }
    }
    fun clockSet(custClock: CustomTime) {
        mClock?.killThread()
        mClock = custClock
    }

    override fun onAttachedToWindow() {
        mTickerStopped = false
        super.onAttachedToWindow()

        mHandler = Handler()

        /**
         * requests a tick on the next hard-second boundary
         */
        mTicker = Runnable {
            if (mTickerStopped) return@Runnable
            text = mClock!!.cal.time.toString()
            invalidate()
            mHandler!!.postDelayed(mTicker, 100)
        }
        mTicker!!.run()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mTickerStopped = true
    }
}
