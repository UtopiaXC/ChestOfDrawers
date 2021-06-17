package com.utopiaxc.chest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.utopiaxc.chest.R;
import com.utopiaxc.chest.beans.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class FunctionsUtils {
    List<BeanUtil> utilBeans;
    Context context;

    public FunctionsUtils(Context context, int MODE) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Favourite", Context.MODE_PRIVATE);
        utilBeans = new ArrayList<>();
        this.context = context;
        if (sharedPreferences.getInt("CountFavourite", 0) == 0 && MODE == VARIABLES.MODE_FAVOURITE) {
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.NO_FAVOURITE,
                    context.getString(R.string.no_favourite),
                    context.getString(R.string.no_favourite_tip),
                    R.drawable.ic_baseline_code_24));
        }

        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.WEIBO), false) || MODE == VARIABLES.MODE_ALL) {
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.WEIBO,
                    context.getString(R.string.function_weibo_title),
                    context.getString(R.string.function_weibo_second_title),
                    R.drawable.weibo));
        }

        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.NBNHHSH), false) || MODE == VARIABLES.MODE_ALL) {
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.NBNHHSH,
                    context.getString(R.string.function_nbnhhsh_title),
                    context.getString(R.string.function_nbnhhsh_second_title),
                    R.drawable.question_mark));
        }

        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.BILIBILI), false) || MODE == VARIABLES.MODE_ALL) {
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.BILIBILI,
                    context.getString(R.string.function_bilibili_pic_title),
                    context.getString(R.string.function_bilibili_pic_second_title),
                    R.drawable.bilibili));
        }

        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.MD5), false) || MODE == VARIABLES.MODE_ALL) {
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.MD5,
                    context.getString(R.string.function_md5_title),
                    context.getString(R.string.function_md5_second_title),
                    R.drawable.md5));
        }

        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.BASE64), false) || MODE == VARIABLES.MODE_ALL) {
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.BASE64,
                    context.getString(R.string.function_base64_title),
                    context.getString(R.string.function_base64_second_title),
                    R.drawable.base64));
        }

        if (sharedPreferences.getBoolean(String.valueOf(VARIABLES.FUNCTIONS.IP), false) || MODE == VARIABLES.MODE_ALL) {
            utilBeans.add(new BeanUtil(VARIABLES.FUNCTIONS.IP,
                    context.getString(R.string.function_ip_title),
                    context.getString(R.string.function_ip_second_title),
                    R.drawable.ip));
        }

    }

    public List<BeanUtil> getUtilBeans() {
        return utilBeans;
    }
}
