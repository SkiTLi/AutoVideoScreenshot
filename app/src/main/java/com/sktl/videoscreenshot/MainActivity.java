package com.sktl.videoscreenshot;

import android.app.Activity;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import java.util.HashMap;

public class MainActivity extends Activity {

//    private static final String VIDEO_URL = "https://cache-mskm904.cdn.yandex.net/kp.cdn.yandex.net/814016/kinopoisk.ru-X-Men_-Apocalypse-306465.mp4";
    private static final String VIDEO_URL = "http://ixbt.video/sadm_files/video/2015/12/CoradiaiLint.mp4";
//    private static final String VIDEO_URL = "http://techslides.com/demos/sample-videos/small.mp4";

    VideoView mVideoView;
    ImageView mImageView;
    Bitmap mBitmap;

    MediaMetadataRetriever myMediaMetadataRetriever;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = (VideoView) findViewById(R.id.video_View);
        mImageView = (ImageView) findViewById(R.id.image_View);


        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller);


        findViewById(R.id.start_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVideoView.setVideoURI(Uri.parse(VIDEO_URL));
                mVideoView.start();


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

        findViewById(R.id.buffer_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int currentPosition = mVideoView.getCurrentPosition(); //in millisecond

                int pos = currentPosition * 1000;   //unit in microsecond

                mBitmap = myMediaMetadataRetriever
                        .getFrameAtTime(pos);
                Log.d("eee", "mBitmap = " + mBitmap);

                mImageView.setImageBitmap(mBitmap);
            }
        });

        findViewById(R.id.shot_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    //addToBackStack //позволяет сохранить состояниие, те будет работать кнопка назад


}






