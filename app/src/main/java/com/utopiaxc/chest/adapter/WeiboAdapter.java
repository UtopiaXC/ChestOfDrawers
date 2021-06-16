package com.utopiaxc.chest.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.utopiaxc.chest.R;
import com.utopiaxc.chest.beans.BeanWeibo;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public class WeiboAdapter extends BaseQuickAdapter<BeanWeibo, BaseViewHolder> {
    public WeiboAdapter(List<BeanWeibo> list) {
        super(R.layout.relativelayout_single_weibo,list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull BeanWeibo item) {
        helper.setText(R.id.text_number, String.valueOf(item.getOrder()));
        helper.setText(R.id.text_weibo_title, item.getTitle());
    }
}
