package com.example.niuzhaojie.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

/**
 * Created by niuzhaojie on 16/9/20.
 */
public class PhoneUtils {


    /**
     * 手机分辨率
     */
    public static int[] getPhonePixels(Activity activity) {
        int[] data = new int[2];
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        data[0] = mDisplayMetrics.widthPixels;
        data[1] = mDisplayMetrics.heightPixels;
        return data;
    }

    /**
     * 获取手机mac地址<br/>
     * 错误返回12个0
     */
    public static String getMacAddress(Context context) {
        // 获取mac地址：
        String macAddress = "000000000000";
        try {
            WifiManager wifiMgr = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
            if (null != info) {
                if (!TextUtils.isEmpty(info.getMacAddress()))
                    macAddress = info.getMacAddress().replace(":", "");
                else
                    return macAddress;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return macAddress;
        }
        return macAddress;
    }

    /**
     * 获取手机型号
     *
     * @return String 手机型号
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取运营商信息
     *
     * @param con 上下文
     * @return String 运营商信息
     */
    public static String getCarrier(Context con) {
        TelephonyManager telManager = (TelephonyManager) con
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = telManager.getSubscriberId();
        if (imsi != null && !"".equals(imsi)) {
            if (imsi.startsWith("46000") || imsi.startsWith("46002")) {// 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
                return "中国移动";
            } else if (imsi.startsWith("46001")) {
                return "中国联通";
            } else if (imsi.startsWith("46003")) {
                return "中国电信";
            }
        }
        return "";
    }

    /**
     * 获取操作系统的版本号
     *
     * @return String 系统版本号
     */
    public static String getSysRelease() {
        return android.os.Build.VERSION.RELEASE;
    }


    public static String getUdid(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        return telephonyManager.getDeviceId();
    }

}
