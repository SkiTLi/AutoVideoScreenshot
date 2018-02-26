package com.sktl.videoscreenshot;

import android.app.Activity;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity {

    //    private static final String VIDEO_URL = "https://cache-mskm904.cdn.yandex.net/kp.cdn.yandex.net/814016/kinopoisk.ru-X-Men_-Apocalypse-306465.mp4";
    private static final String VIDEO_URL = "http://ixbt.video/sadm_files/video/2015/12/CoradiaiLint.mp4";
//    private static final String VIDEO_URL = "http://techslides.com/demos/sample-videos/small.mp4";

    VideoView mVideoView;
    ImageView mImageView;

    EditText mEditText;
    List<Bitmap> bitmapList;

    private int duration = 3000;        // 3 sec


    MediaMetadataRetriever myMediaMetadataRetriever;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = (VideoView) findViewById(R.id.video_View);
        mImageView = (ImageView) findViewById(R.id.image_View);
        mEditText = (EditText) findViewById(R.id.edit_text1);

        myMediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            myMediaMetadataRetriever.setDataSource(VIDEO_URL, new HashMap<String, String>());
//
        } catch (Exception e) {
            Log.d("eee", "myMediaMetadataRetriever.setDataSource(VIDEO_URL);"
                    + " ERROR eee: " + e);
        }


        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller);


        findViewById(R.id.start_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mVideoView.setMediaController(controller);
                mVideoView.setVideoURI(Uri.parse(VIDEO_URL));

                mVideoView.setOnPreparedListener(myVideoViewPreparedListener);

                mVideoView.start();

                bitmapList = new ArrayList<Bitmap>();


                final Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap mBitmap;
                        int fps = 100;
                        if (mEditText.getText().length() != 0) {
                            fps = Integer.parseInt(mEditText.getText().toString());
                        }

                        int dur = duration / 10 / fps;
                        for (int i = 1; i < dur; i++) {

                            mBitmap = myMediaMetadataRetriever
                                    .getFrameAtTime(i * 10000 * fps, myMediaMetadataRetriever.OPTION_CLOSEST);
                            Log.d("eee", "mBitmap = " + mBitmap);

                            bitmapList.add(mBitmap);
                        }


                    }
                });

                Log.d("eee", "myMediaMetadataRetriever.getEmbeddedPicture() = " + myMediaMetadataRetriever.getEmbeddedPicture());

                myThread.start();


                if (myThread.isInterrupted()) {
                    myThread.interrupt();
                }
            }
        });

        findViewById(R.id.shot_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mImageView.setImageBitmap(bitmapList.get(bitmapList.size() - 1));

            }
        });

//        findViewById(R.id.buffer_button2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });


    }


    MediaPlayer.OnPreparedListener myVideoViewPreparedListener
            = new MediaPlayer.OnPreparedListener() {

        @Override
        public void onPrepared(MediaPlayer arg0) {
            duration = mVideoView.getDuration();
            Log.d("eee", "mVideoView.getDuration() = " + duration);
        }
    };


}






