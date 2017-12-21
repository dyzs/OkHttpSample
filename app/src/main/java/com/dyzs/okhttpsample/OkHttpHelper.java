package com.dyzs.okhttpsample;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by maidou on 2017/12/14.
 *
 *
 * public boolean isSuccessful()
 * Returns true if the code is in [200..300),
 * which means the request was successfully received, understood, and accepted.
 */

public class OkHttpHelper {
    private static OkHttpHelper mInstance;
    private final OkHttpClient mOkHttpClient;

    // see MultipartBody
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public static final MediaType MIXED = MediaType.parse("multipart/mixed");
    public static final MediaType ALTERNATIVE = MediaType.parse("multipart/alternative");
    public static final MediaType DIGEST = MediaType.parse("multipart/digest");
    public static final MediaType PARALLEL = MediaType.parse("multipart/parallel");
    public static final MediaType FORM = MediaType.parse("multipart/form-data");



    private OkHttpHelper() {
        mOkHttpClient = new OkHttpClient.Builder()
                /*.cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        return null;
                    }
                })*/
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * response.body().string();
     * response.body().bytes();
     * response.body().byteStream();
     * @param url
     * @param iCallBack
     *
     *
     * 下载一个文件，打印他的响应头，以string形式打印响应体。
     * 响应体的 string() 方法对于小文档来说十分方便、高效。但是如果响应体太大（超过1MB），应避免适应 string()方法 ，因为他会将把整个文档加载到内存中。
     * 对于超过1MB的响应body，应使用流的方式来处理body。
     */
    public void getStringMethodGet(String url, final ICallBack iCallBack) {
        Request request = new Request.Builder()
                .url(url)
                //.header("Cookie", "xxx")
                .build();
        Call call = mOkHttpClient.newCall(request);

        try {

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    iCallBack.onFailure(call, e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        iCallBack.onSuccess(response.body().string());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            iCallBack.onFailure(e.getMessage());
        }
    }


    public void getStringHeaderMethodGet(String url, final ICallBack iCallBack) {
        Request request = new Request.Builder()
                .url(url)
                //.header("Cookie", "xxx")
                .build();
        Call call = mOkHttpClient.newCall(request);

        try {

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    iCallBack.onFailure(call, e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        iCallBack.onSuccess(response.body().string());
                    }
                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            iCallBack.onFailure(e.getMessage());
        }
    }


    public void postJsonMethodPost(String url, String json) throws IOException{
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = mOkHttpClient.newCall(request);
        try {

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // ("Unexpected code " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void postKeyValueMethodPost(String url, String json) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("platform", "android")
                .add("username", "bug")
                .add("subject", "osChina")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        try {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        //response.body().string();
                    } else {
                        // throw new IOException("Unexpected code " + response);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 基于 Http 的文件上传
     *
     */
    public void uploadFile(File file) {

    }




}
