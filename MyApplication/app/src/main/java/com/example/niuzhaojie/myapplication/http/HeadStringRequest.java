package com.example.niuzhaojie.myapplication.http;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.niuzhaojie.myapplication.utils.PhoneUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niuzhaojie on 16/9/20.
 */
public class HeadStringRequest extends StringRequest {

    private Activity mActivity;

    public HeadStringRequest(Activity activity, int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.mActivity = activity;
    }

    public HeadStringRequest(Activity activity, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
        this.mActivity = activity;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        Map<String, String> head = new HashMap<String, String>();
        head.put("requestType", "app");
        head.put("weblogId", "");
        head.put("userId", "");
        head.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        head.put("screenSize", PhoneUtils.getPhonePixels(mActivity)[0] + "x" + PhoneUtils.getPhonePixels(mActivity)[1]);
        head.put("platform", "android");
        head.put("macAddress", PhoneUtils.getMacAddress(mActivity));
        head.put("clientVersion", "1.6.4");
        head.put("protocolVersion", "1.6.4");
        head.put("sourceId", "hggformal");
        head.put("model", PhoneUtils.getModel());
        head.put("carrier", PhoneUtils.getCarrier(mActivity));
        head.put("osversition", PhoneUtils.getSysRelease());
        head.put("udid", PhoneUtils.getUdid(mActivity));
        return head;
    }


}
