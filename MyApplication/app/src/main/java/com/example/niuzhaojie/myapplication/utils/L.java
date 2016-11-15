package com.example.niuzhaojie.myapplication.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by niuzhaojie on 16/10/8.
 */

public class L {
    private static boolean sDebug = true;
    private static String sTag = "nzj";


    public static void init(boolean debug, String tag) {
        L.sDebug = debug;
        L.sTag = tag;
    }

    public static void e(String msg, Object... params) {
        e(null, msg, params);
    }

    public static void e(String tag, String msg, Object[] params) {
        if (!sDebug) return;
        LogText.e(getFinalTag(tag), String.format(msg, params));
    }

    private static String getFinalTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            return tag;
        }
        return sTag;
    }


    private static String getPrettyJsonStr(String content) {

        String str = content;
        JSONObject obj = null;
        try {
            obj = new JSONObject(str);
            return obj.toString(1);

        } catch (JSONException e) {
            return content;
        }
    }


    private static class LogText {
        //        private static final String DOUBLE_DIVIDER = "-";
        private static final String SINGLE_DIVIDER = "---------------------------------------------------------------\n";

        private String mTag;

        public LogText(String tag) {
            mTag = tag;
        }


        public static void e(String tag, String content) {
            LogText logText = new LogText(tag);
            logText.setup(content);
        }

        public void setup(String content) {
            setUpHeader();
            setUpContent(content);
            setUpFooter();

        }

        private void setUpHeader() {
            Log.e(mTag, SINGLE_DIVIDER);
        }

        private void setUpFooter() {
            Log.e(mTag, SINGLE_DIVIDER);
        }

        public void setUpContent(String content) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.e(mTag, "\t(" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
            Log.e(mTag, "\t" + getPrettyJsonStr(content));
        }

        private StackTraceElement getTargetStackTraceElement() {
            // find the target invoked method
            StackTraceElement targetStackTrace = null;
            boolean shouldTrace = false;
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                boolean isLogMethod = stackTraceElement.getClassName().equals(L.class.getName());
                if (shouldTrace && !isLogMethod) {
                    targetStackTrace = stackTraceElement;
                    break;
                }
                shouldTrace = isLogMethod;
            }
            return targetStackTrace;
        }

    }
}
