package com.utopiaxc.chest.adapter;

import android.content.Intent;
import android.net.Uri;

import androidx.navigation.Navigation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.utopiaxc.chest.R;
import com.utopiaxc.chest.beans.BeanLicense;
import com.utopiaxc.chest.beans.BeanNbnhhsh;
import com.utopiaxc.chest.beans.BeanUtil;
import com.utopiaxc.chest.utils.VARIABLES;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LicenseAdapter extends BaseQuickAdapter<BeanLicense, BaseViewHolder> {
    public LicenseAdapter(List<BeanLicense> list) {
        super(R.layout.relativelayout_utils,list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, BeanLicense s) {
        helper.setText(R.id.function_title,s.getProject());
        helper.setText(R.id.function_second_title,s.getAuthor());
        helper.setImageResource(R.id.function_icon,s.getPic());
    }

    public void registerItemClickID(){
        this.addChildClickViewIds(R.id.card_view);
    }
}