package com.utopiaxc.chest.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.utopiaxc.chest.R;
import com.utopiaxc.chest.beans.BeanUtil;
import com.utopiaxc.chest.utils.FunctionsUtils;
import com.utopiaxc.chest.utils.VARIABLES;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FunctionAdapter extends BaseQuickAdapter<BeanUtil, BaseViewHolder> {


    public FunctionAdapter(List<BeanUtil> list) {
        super(R.layout.relativelayout_utils,list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull BeanUtil beanUtil) {
        helper.setText(R.id.function_title, beanUtil.getTitle());
        helper.setImageResource(R.id.function_icon, beanUtil.getIcon_id());
        helper.setText(R.id.function_second_title, beanUtil.getSecond_title());
    }

    public void registerItemClickID(){
        this.addChildClickViewIds(R.id.card_view);
        this.addChildLongClickViewIds(R.id.card_view);
    }

    public void setOnClickListenerAllMode(){
        this.setOnItemChildClickListener((adapter1, view, position) -> {
            if (view.getId() == R.id.card_view) {
                BeanUtil beanUtil= (BeanUtil) adapter1.getItem(position);
                if (beanUtil.getFUNCTION_ID()== VARIABLES.FUNCTIONS.WEIBO){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityWeibo);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.NBNHHSH){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityNbnhhsh);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.BILIBILI){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityBilibiliPic);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.MD5){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityMD5);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.BASE64){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityBase64);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.IP){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityIp);
                }
            }
        });
    }

    public void setOnClickListenerFavouriteMode(){
        this.setOnItemChildClickListener((adapter1, view, position) -> {
            if (view.getId() == R.id.card_view) {
                BeanUtil beanUtil= (BeanUtil) adapter1.getItem(position);
                if (beanUtil.getFUNCTION_ID()== VARIABLES.FUNCTIONS.WEIBO){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_activityWeibo);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.NBNHHSH){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_activityNbnhhsh);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.BILIBILI){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_activityBilibiliPic);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.MD5){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_activityMD5);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.BASE64){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_activityBase64);
                }else if (beanUtil.getFUNCTION_ID()==VARIABLES.FUNCTIONS.IP){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_activityIp);
                }
            }
        });
    }



    public void setOnLongClickListener(int MODE,Context context){
        this.setOnItemChildLongClickListener((adapter1, view, position) -> {
            BeanUtil beanUtil= (BeanUtil) adapter1.getItem(position);
            if (beanUtil.getFUNCTION_ID()== VARIABLES.FUNCTIONS.NO_FAVOURITE)
                return true;
            SharedPreferences sharedPreferences=view.getContext().getSharedPreferences("Favourite", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            VARIABLES.FUNCTIONS function=beanUtil.getFUNCTION_ID();
            if (!sharedPreferences.getBoolean(function.toString(), false)){
                editor.putBoolean(function.toString(),true);
                editor.putInt("CountFavourite",sharedPreferences.getInt("CountFavourite",0)+1);
                editor.apply();
                Toast.makeText(getContext(),R.string.do_favourite,Toast.LENGTH_SHORT).show();
            }else{
                editor.putBoolean(function.toString(),false);
                editor.putInt("CountFavourite",sharedPreferences.getInt("CountFavourite",0)-1);
                editor.apply();
                if (MODE==VARIABLES.MODE_FAVOURITE) {
                    this.remove(beanUtil);
                    if (sharedPreferences.getInt("CountFavourite",0)==0){
                        FunctionsUtils functionsUtils=new FunctionsUtils(context,VARIABLES.MODE_FAVOURITE);
                        this.addData(functionsUtils.getUtilBeans());
                    }
                }

                Toast.makeText(getContext(),R.string.cancel_favourite,Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }
}
