package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.control.BitmapCache;
import com.example.niuzhaojie.myapplication.http.GsonRequest;
import com.example.niuzhaojie.myapplication.http.HeadStringRequest;
import com.example.niuzhaojie.myapplication.http.XMLRequest;
import com.example.niuzhaojie.myapplication.model.Images;
import com.example.niuzhaojie.myapplication.model.Weather;
import com.example.niuzhaojie.myapplication.utils.L;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by niuzhaojie on 16/9/19.
 */
public class VolleyTestActivity extends Activity {
    private String TAG = this.getClass().getSimpleName();
    private RequestQueue queue;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        imageView = (ImageView) findViewById(R.id.image);

        queue = Volley.newRequestQueue(this);

    }

    public void startRequest(View view) {


//        testStringRequest();

//        testJsonRequest();

//        testImageRequest();

//        testImageLoader();

//        testNetworkImageView();

//        testXMLRequest();

//        testGsonRequest();

        testHeadStringRequest();


    }

    private void testHeadStringRequest() {

        HeadStringRequest headStringRequest = new HeadStringRequest(this, "http://luke.dev.hotelgg.net//api/apphgg/version",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.e(TAG, response);
                        L.e(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage() + "");
                    }
                });

        queue.add(headStringRequest);

    }

    private void testGsonRequest() {

        GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(
                "http://www.weather.com.cn/data/sk/101010100.html?key=5a81c68d9eb088f5f012939fedd6ca10", Weather.class,
                new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        Weather.Weatherinfo weatherInfo = weather.getWeatherinfo();
                        Log.d("TAG", "city is " + weatherInfo.getCity());
                        Log.d("TAG", "temp is " + weatherInfo.getTemp());
                        Log.d("TAG", "time is " + weatherInfo.getTime());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage() + "");
            }
        });

        queue.add(gsonRequest);
    }

    private void testXMLRequest() {
        XMLRequest xmlRequest = new XMLRequest("http://flash.weather.com.cn/wmaps/xml/china.xml?key=5a81c68d9eb088f5f012939fedd6ca10",
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {
                        xmlParser(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "" + error.getMessage());
                    }
                });

        queue.add(xmlRequest);

    }

    private void xmlParser(XmlPullParser response) {
        try {
            int eventType = response.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String nodeName = response.getName();
                        if ("city".equals(nodeName)) {
                            String pName = response.getAttributeValue(0);
                            Log.e("TAG", "pName is " + pName);
                            ToastUtils.showToast(VolleyTestActivity.this, pName);
                        }
                        break;
                }
                eventType = response.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Response.Listener bitmapListener = new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap response) {
            imageView.setImageBitmap(response);
        }

    };

    private Response.Listener successListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("TAG", response.toString());
        }
    };

    private Response.ErrorListener errorErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("TAG", error.getMessage(), error);
        }
    };

    private void testStringRequest() {
        StringRequest stringRequest = new StringRequest("http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "success================>" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "error================>" + error.getMessage());
                    }
                });
        queue.add(stringRequest);


//        stringRequest = new StringRequest(Request.Method.POST,
//                "",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("", "");
//                return map;
//            }
//        };
    }

    private void testJsonRequest() {

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://api.weatherdt.com/common/?area=101020100&type=forecast&key=5a81c68d9eb088f5f012939fedd6ca10", null,
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.weather.com.cn/data/sk/101010100.html?key=5a81c68d9eb088f5f012939fedd6ca10", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Weather weather = new Gson().fromJson(response.toString(), Weather.class);

                        Log.d("TAG", weather.getWeatherinfo().getCity());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        queue.add(jsonObjectRequest);

    }

    private void testImageRequest() {

        ImageRequest imageRequest = new ImageRequest(Images.imageUrls[0], bitmapListener, 0, 0, Bitmap.Config.RGB_565, errorErrorListener);

        queue.add(imageRequest);

    }

    private void testImageLoader() {
//        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
//            @Override
//            public Bitmap getBitmap(String url) {
//                return null;
//            }
//
//            @Override
//            public void putBitmap(String url, Bitmap bitmap) {
//
//            }
//        });

        ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.drawable.mdd_banner, R.drawable.mdd_beijing);

        imageLoader.get(Images.imageUrls[2], listener);

    }

    private void testNetworkImageView() {
//        1. 创建一个RequestQueue对象。
//        2. 创建一个ImageLoader对象。
//        3. 在布局文件中添加一个NetworkImageView控件。
//        4. 在代码中获取该控件的实例。
//        5. 设置要加载的图片地址。

//        networkImageView.setDefaultImageResId(R.drawable.default_image);
//        networkImageView.setErrorImageResId(R.drawable.failed_image);
//        networkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", imageLoader);


    }
}