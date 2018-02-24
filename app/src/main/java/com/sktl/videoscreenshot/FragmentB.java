package com.sktl.videoscreenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by USER-PC on 22.02.2018.
 */

public class FragmentB extends Fragment {
    public static final String TEXT_KEY = "TEXT_KEY";
    ImageView imageView;


    //    private String text;//должно быть , но я сделал protected
    protected String text;

//   bundle //это типо hashmap


    //нужно gps через google play
    public static FragmentB newInstance(FragmentManager fragmentManager, String text) {

        Fragment fragment = fragmentManager
                .findFragmentByTag(FragmentB.class.getName());

        FragmentB tempFragment;
        if (fragment != null && fragment instanceof FragmentB) {
            tempFragment = (FragmentB) fragment;
        } else {
            tempFragment = new FragmentB();
            Bundle bundle = new Bundle();
            bundle.putString(TEXT_KEY, text);

            tempFragment.setArguments(bundle);

        }

        return tempFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        getArguments()ю//выдираются аргументы


        Bundle bundle = getArguments();
        if (bundle != null) {

            text = bundle.getString(TEXT_KEY);
            //        если брать не activity а fragmen в основном то нужно брать getChildMAngerFragment


        }
    }

    //можно ли во фрагменте определить несколько конструкторов - от вет : создавать можно, но падаватьданные нельзя (те пользоваться можно только без ппапраметров)
//т.к. при востановлении андроид будет использовать конструктор по умолчанию (без параметров)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);//перепишем


        return inflater.inflate(R.layout.buffer_fragment, container, false);


        //здесь биндинг binding.root
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity();
        //создавать вью в onCreateView нельзя, это не правильно


//        Button button = (Button) getActivity().findViewById(R.id.buffer_button2);
//        final ImageView imageView = (ImageView) getActivity().findViewById(R.id.buffer_image_view);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.beast));
//
//            }
//        });


    }


    //этот метод создается только тогда когда ужесоздано активити
    //после этого метода можно использовать метод getActivity()
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    //
    @Override
    public void onStart() {
        super.onStart();


        //+ метод который показывает постоянное состояние (обнавление значения) переменной buffer


        ImageView imageView = (ImageView) getActivity().findViewById(R.id.buffer_image_view);
//        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.beast));


        //ложится приложение
//        Bundle extras =  getActivity().getIntent().getExtras();
//        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
//        imageView.setImageBitmap(bmp );








    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }


    //добавили к активити
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//    }

    //открепили к активити
//    @Override
//    public void onDetach() {
//        super.onDetach();
//    }

    //когда активити умерла вызовится в 99% случаев (во фрагменте на него можно рассчитывать)
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }

}

