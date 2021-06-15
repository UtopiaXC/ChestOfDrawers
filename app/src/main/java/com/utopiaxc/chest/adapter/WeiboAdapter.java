package com.utopiaxc.chest.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.utopiaxc.chest.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class WeiboAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    Map<Integer, ArrayList<String>> weibo_map;

    public WeiboAdapter(Map<Integer, ArrayList<String>> weibo_map) {
        super(R.layout.relativelayout_single_weibo,new ArrayList<>(weibo_map.keySet()));
        this.weibo_map=weibo_map;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull Integer item) {
        helper.setText(R.id.text_number, item.toString());
        helper.setText(R.id.text_weibo_title, weibo_map.get(item).get(0));
    }
}
