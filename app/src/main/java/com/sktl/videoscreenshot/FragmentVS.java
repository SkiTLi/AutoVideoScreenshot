package com.sktl.videoscreenshot;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by USER-PC on 22.02.2018.
 */

public class FragmentVS extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getArguments()ю//выдираются аргументы
    }

    //можно ли во фрагменте определить несколько конструкторов - от вет : создавать можно, но падаватьданные нельзя (те пользоваться можно только без ппапраметров)
//т.к. при востановлении андроид будет использовать конструктор по умолчанию (без параметров)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);//перепишем
        return inflater.inflate(R.layout.shot_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getActivity();
        //создавать вью в onCreateView нельзя, это не правильно
    }


    //этот метод создается только тогда когда ужесоздано активити
    //после этого метода можно использовать метод getActivity()
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();


        //+ метод который забирает последнее значение из переменной buffer

        ImageView imageView = (ImageView) getActivity().findViewById(R.id.shot_image_view);
        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.venom));
    }
}
