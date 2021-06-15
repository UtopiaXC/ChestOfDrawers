package com.utopiaxc.chest.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.utopiaxc.chest.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NbnhhshAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    List<String> list;
    public NbnhhshAdapter(List<String> list) {
        super(R.layout.relativelayout_nbnhhsh,list);
        this.list=list;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, String s) {
        helper.setText(R.id.nbnhhsh_text_number,String.valueOf(list.indexOf(s)+1));
        helper.setText(R.id.nbnhhsh_text_content,s);
    }
}
