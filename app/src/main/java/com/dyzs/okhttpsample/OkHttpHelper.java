package com.dyzs.okhttpsample;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by maidou on 2017/12/14.
 */

public class OkHttpHelper {
    private static OkHttpHelper mInstance;
    private final OkHttpClient mOkHttpClient;
    private OkHttpHelper() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpHelper();
                }
            }
        }
        return mInstance;
    }



    public void getString(String url, final ICallBack iCallBack) {
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

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}
