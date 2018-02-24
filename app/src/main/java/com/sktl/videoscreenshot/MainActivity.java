package com.sktl.videoscreenshot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.security.spec.ECField;
import java.util.HashMap;
import java.util.logging.LogManager;


public class MainActivity extends FragmentActivity {

    private static final String VIDEO_URL = "https://ivi-storage33.cdnvideo.ru/mp4-hi/PbRhBEwLfJ5FD0jKY1zdnQ,1519450861/storage33/contents/f/2/bd2e668ddb6a3fa0a9e1471bc31860.mp4";
//    private static final String VIDEO_URL = "http://techslides.com/demos/sample-videos/small.mp4";

    VideoView mVideoView;
    ImageView mImageView;
    Bitmap mBitmap;
    Drawable mDrawable;
    MediaMetadataRetriever myMediaMetadataRetriever;


    public static void show(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    //bundle это то же что и intent только здесь не передается инфа
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView = (VideoView) findViewById(R.id.videoView);



        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller);


        findViewById(R.id.start_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVideoView.setVideoURI(Uri.parse(VIDEO_URL));
                mVideoView.start();


//погнали пробовать
                myMediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    myMediaMetadataRetriever.setDataSource(VIDEO_URL, new HashMap<String, String>());
//
                } catch (Exception e) {
                    Log.d("eee", "myMediaMetadataRetriever.setDataSource(VIDEO_URL);"
                            + " ERROR eee: " + e);
                }






            }
        });


        //FragmentManager fragmentManager = getSupportFragmentManager();//вынисли в отдельный метод
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//вынисли в отдельный метод
        //тразакция - этосписок операций только после прохождения полного перчня этих операций транзакция считается успешной

        if (savedInstanceState == null) {
            showFragment(getSupportFragmentManager(), new FragmentB(), false);
        }
        findViewById(R.id.buffer_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(getSupportFragmentManager(),
                        FragmentB.newInstance(getSupportFragmentManager(),
                                "какой-то текст где-то в 13 уроке"), true);

//погнали пробовать


                int currentPosition = mVideoView.getCurrentPosition(); //in millisecond

                int pos = currentPosition * 30000;   //unit in microsecond
//                int pos = currentPosition;   //unit in microsecond

                mBitmap = myMediaMetadataRetriever
                        .getFrameAtTime(pos);
                    Log.d("eee", "mBitmap = " + mBitmap);


//                mImageView.setImageBitmap(mBitmap);//поместили кадр
//                mDrawable  = mImageView.getDrawable(); // получим картинку у первого компонента







                //первый способ (не работает)

                //нужно разобраться с интент
//                Intent intent = new Intent(getBaseContext(), FragmentB.class);
//                Bundle extras = new Bundle();
//                try {
//                    extras.putParcelable("imagebitmap", mBitmap);
//                    intent.putExtras(extras);
//                    startActivity(intent);
//                } catch (Exception e) {
//                    Log.d("eee", "intent.. extras.. extras.putParcelable.. intent.putExtras.. startActivity..;"
//                            + " ERROR eee: " + e);
//                }

                //второй способ





            }
        });

        findViewById(R.id.shot_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(getSupportFragmentManager(),
                        new FragmentVS(), true);

            }
        });
    }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragment, fragment.getClass().getName());
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();//выполнить
    }
    //addToBackStack //позволяет сохранить состояниие, те будет работать кнопка назад


}






