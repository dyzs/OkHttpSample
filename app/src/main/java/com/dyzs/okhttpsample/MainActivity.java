package com.dyzs.okhttpsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.background_image)
    public ImageView background_image;
    @BindView(R.id.hello_world)
    public TextView hello_world;

    @BindViews({R.id.tv_1st, R.id.tv_2nd, R.id.tv_3rd})
    public List<TextView> textViews;

    @OnClick(R.id.background_image)
    public void showImageClick() {
        Toast.makeText(MainActivity.this, "image background", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        hello_world.setText("Hi! Nick!!  Bye ! Nick");




        String url = "http://snakeapi2.afunapp.com/top_list_v2/update_score/login_type=4&push_id=AvRMMWeACYv8cfABWvkISZFIof082H2AWKEKzeY2zj8%3D&channel=flyme&platform=2&sid=424d1d28f3e3790c60df009522547163&new_score=0&uid=0979a246-8e7d-43b3-8a50-9a0a498e0335&first_charge_state=0&snake_sign=lxy9YF7t8AgSxvGAUd27iNfxe%2BQ%3D&skin_id=72&device_id=imei_867246029816387_uuid_151006610628543813&length=5002&relive_count=0&version_code=2101&kill=0&version=3.9.6&nonce=1513592346&market=flyme&android_os_version=5.1&phone=MX5&name=%E9%83%BD%E6%80%AA%E8%BF%99%E6%9C%88%E8%89%B2_&game_mode=1&push_channel=2&buff=0";
        OkHttpHelper.getInstance().getStringMethodGet(url, new ICallBack() {
            @Override
            public void onFailure(Object... objects) {
                String x = "";
            }

            @Override
            public void onSuccess(Object... objects) {
                String succ = (String) objects[0];
                String threadName = Thread.currentThread().getName();
                String sc = (String) objects[0];
            }
        });









    }
}
