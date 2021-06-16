package com.utopiaxc.chest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.utopiaxc.chest.R;
import com.utopiaxc.chest.beans.BeanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionsUtils {
    List<BeanUtil> utilBeans;
    Context context;

    public FunctionsUtils(Context context, int MODE) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Favourite", Context.MODE_PRIVATE);
        utilBeans = new ArrayList<>();
        this.context = context;
        if (sharedPreferences.getInt("CountFavourite", 0) == 0 && MODE == VARIABLES.MODE_FAVOURITE) {
            //无收藏提示
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.NO_FAVOURITE,
                    context.getString(R.string.no_favourite),
                    context.getString(R.string.no_favourite_tip),
                    R.drawable.ic_baseline_code_24));
        }

        //微博热搜
        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.WEIBO), false) || MODE == VARIABLES.MODE_ALL) {
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.WEIBO,
                    context.getString(R.string.function_weibo_title),
                    context.getString(R.string.function_weibo_second_title),
                    R.drawable.weibo));
        }

        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.NBNHHSH), false) || MODE == VARIABLES.MODE_ALL) {
            //能不能好好说话
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.NBNHHSH,
                    context.getString(R.string.function_nbnhhsh_title),
                    context.getString(R.string.function_nbnhhsh_second_title),
                    R.drawable.question_mark));
        }

        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.BILIBILI), false) || MODE == VARIABLES.MODE_ALL) {
            //哔哩哔哩封面
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.BILIBILI,
                    context.getString(R.string.function_bilibili_pic_title),
                    context.getString(R.string.function_bilibili_pic_second_title),
                    R.drawable.bilibili));
        }

    }

    public List<BeanUtil> getUtilBeans() {
        return utilBeans;
    }
}
