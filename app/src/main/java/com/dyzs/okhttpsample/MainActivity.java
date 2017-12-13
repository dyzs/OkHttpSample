package com.dyzs.okhttpsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.background_image)
    public ImageView background_image;
    @BindView(R.id.hello_world)
    public TextView hello_world;
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











    }
}
