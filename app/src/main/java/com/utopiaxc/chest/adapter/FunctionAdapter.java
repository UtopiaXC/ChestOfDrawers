package com.utopiaxc.chest.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.utopiaxc.chest.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class FunctionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    Map<String,Integer> map_icon;
    Map<String,String> map_second_title;

    public FunctionAdapter(List<String> list,Map<String,Integer> map_icon,Map<String,String> map_second_title) {
        super(R.layout.relativelayout_utils,list);
        this.map_icon=map_icon;
        this.map_second_title=map_second_title;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull String item) {
        helper.setText(R.id.function_title, item);
        try {
            helper.setImageResource(R.id.function_icon, map_icon.get(item));
            helper.setText(R.id.function_second_title,map_second_title.get(item));
        }catch (NullPointerException e){
            System.out.println("Map content error");
            e.printStackTrace();
        }
    }
}
