/*
 * Copyright (C) 2012-2016 Markus Junginger, greenrobot (http://greenrobot.org)
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
package com.isoft.eventbusmodule.android;


import com.isoft.eventbusmodule.Logger;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.logging.Level;


public class AndroidLogger implements Logger {
    private HiLogLabel label=new HiLogLabel(HiLog.DEBUG,01*00101,"test");

    private static final boolean ANDROID_LOG_AVAILABLE;

    static {
        boolean android = false;
        try {
            android = Class.forName("android.util.Log") != null;
        } catch (ClassNotFoundException e) {
            // OK
        }
        ANDROID_LOG_AVAILABLE = android;
    }

    public static boolean isAndroidLogAvailable() {
        return ANDROID_LOG_AVAILABLE;
    }


    private final String tag;

    public AndroidLogger(String tag) {
        this.tag = tag;
    }

    public void log(Level level, String msg) {
        if (level != Level.OFF) {
//            Log.println(mapLevel(level), tag, msg);
            HiLog.debug(label,tag,msg,mapLevel(level));
        }
    }

    public void log(Level level, String msg, Throwable th) {
        if (level != Level.OFF) {
            // That's how Log does it internally
//            Log.println(mapLevel(level), tag, msg + "\n" + Log.getStackTraceString(th));
            System.out.println(msg);
            HiLog.debug(label,tag,msg,mapLevel(level));

        }
    }

    private int mapLevel(Level level) {
        int value = level.intValue();
        if (value < 800) { // below INFO
            if (value < 500) { // below FINE
                return HiLog.FATAL;
            } else {
                return HiLog.DEBUG;
            }
        } else if (value < 900) { // below WARNING
            return HiLog.INFO;
        } else if (value < 1000) { // below ERROR
            return HiLog.WARN;
        } else {
            return HiLog.ERROR;
        }
    }
}
