package com.dyzs.okhttpsample;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
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

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpHelper() {
        mOkHttpClient = new OkHttpClient.Builder()
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
     */
    public void pullStringMethodGet(String url, final ICallBack iCallBack) {
        Request request = new Request.Builder()
                .url(url)
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


    public void pushJsonMethodPost(String url, String json) throws IOException{
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







}
