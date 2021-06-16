package com.utopiaxc.chest.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.utopiaxc.chest.R;
import com.utopiaxc.chest.beans.BeanNbnhhsh;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NbnhhshAdapter extends BaseQuickAdapter<BeanNbnhhsh, BaseViewHolder> {
    List<String> list;
    public NbnhhshAdapter(List<BeanNbnhhsh> list) {
        super(R.layout.relativelayout_nbnhhsh,list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, BeanNbnhhsh s) {
        helper.setText(R.id.nbnhhsh_text_number,String.valueOf(s.getOrder()));
        helper.setText(R.id.nbnhhsh_text_content,s.getTitle());
    }
}
