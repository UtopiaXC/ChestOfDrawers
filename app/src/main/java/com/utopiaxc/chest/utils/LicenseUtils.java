package com.utopiaxc.chest.utils;

import android.content.Context;

import com.utopiaxc.chest.R;
import com.utopiaxc.chest.beans.BeanLicense;

import java.util.ArrayList;
import java.util.List;

public class LicenseUtils {
    public static List<BeanLicense> getLicenses(){
        List<BeanLicense> list=new ArrayList<>();

        list.add(new BeanLicense(
                "Android-Iconics",
                "mikepenz",
                "https://github.com/mikepenz/Android-Iconics/blob/develop/LICENSE",
                R.drawable.mikepenz));

        list.add(new BeanLicense("material-about-library",
                "daniel-stoneuk",
                "https://github.com/daniel-stoneuk/material-about-library/blob/master/LICENSE",
                R.drawable.daniel_stoneuk));

        list.add(new BeanLicense("Android-SpinKit",
                "ybq",
                "https://github.com/ybq/Android-SpinKit/blob/master/LICENSE",
                R.drawable.ybq));

        list.add(new BeanLicense("BaseRecyclerViewAdapterHelper",
                "CymChad",
                "https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/LICENSE",
                R.drawable.cym_chad));

        list.add(new BeanLicense("jsoup",
                "jhy",
                "https://github.com/jhy/jsoup/blob/master/LICENSE",
                R.drawable.jhy));

        list.add(new BeanLicense("fastjson",
                "alibaba",
                "https://github.com/alibaba/fastjson/blob/master/license.txt",
                R.drawable.alibaba));

        list.add(new BeanLicense("bilibili-API-collect",
                "SocialSisterYi",
                "https://github.com/SocialSisterYi/bilibili-API-collect/blob/master/LICENSE",
                R.drawable.social_sister_yi));

        list.add(new BeanLicense("nbnhhsh",
                "itorr",
                "https://github.com/itorr/nbnhhsh/blob/master/LICENSE",
                R.drawable.itorr));


        return list;
    }
}
