package com.utopiaxc.chest.fragments.all;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.utopiaxc.chest.R;
import com.utopiaxc.chest.databinding.FragmentAllBinding;
import com.utopiaxc.chest.adapter.FunctionAdapter;
import com.utopiaxc.chest.utils.VARIABLES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentAll extends Fragment {

    private FragmentAllBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAllBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView=binding.listAll;
        AddFunctionsEntrance(recyclerView);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //添加功能入口
    private void AddFunctionsEntrance(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        //创建数据结构
        Map<String, Integer> map_icon = new HashMap<>();
        Map<String, String> map_second_title = new HashMap<>();
        List<String> list = new ArrayList<>();

        //添加微博入口
        map_icon.put(getString(R.string.function_weibo_title),R.drawable.weibo);
        map_second_title.put(getString(R.string.function_weibo_title),getString(R.string.function_weibo_second_title));
        list.add(getString(R.string.function_weibo_title));

        //添加缩写翻译入口
        map_icon.put(getString(R.string.function_nbnhhsh_title),R.drawable.question_mark);
        map_second_title.put(getString(R.string.function_nbnhhsh_title),getString(R.string.function_nbnhhsh_second_title));
        list.add(getString(R.string.function_nbnhhsh_title));

        //添加bilibili视频封面获取入口
        map_icon.put(getString(R.string.function_bilibili_pic_title),R.drawable.bilibili);
        map_second_title.put(getString(R.string.function_bilibili_pic_title),getString(R.string.function_bilibili_pic_second_title));
        list.add(getString(R.string.function_bilibili_pic_title));


        //创建适配器
        FunctionAdapter adapter = new FunctionAdapter(list, map_icon, map_second_title);

        adapter.addChildClickViewIds(R.id.card_view);
        adapter.addChildLongClickViewIds(R.id.card_view);


        // 设置子控件点击监听
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            if (view.getId() == R.id.card_view) {
                if (position== VARIABLES.FUNCTIONS.WEIBO.ordinal()){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityWeibo);
                }else if (position==VARIABLES.FUNCTIONS.NBNHHSH.ordinal()){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityNbnhhsh);
                }else if (position==VARIABLES.FUNCTIONS.BILIBILI.ordinal()){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_all_to_activityBilibiliPic);
                }
            }
        });


        adapter.setOnItemChildLongClickListener((adapter12, view, position) -> {
            doFavourite(position);
            return true;
        });

        //添加显示适配器
        recyclerView.setAdapter(adapter);
    }

    private void doFavourite(int position){
        SharedPreferences sharedPreferences=requireActivity().getSharedPreferences("Favourite", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String function=String.valueOf(position);
        if (!sharedPreferences.getBoolean(function, false)){
            editor.putBoolean(function,true);
            editor.putInt("CountFavourite",sharedPreferences.getInt("CountFavourite",0)+1);
            editor.apply();
            Toast.makeText(getContext(),R.string.do_favourite,Toast.LENGTH_SHORT).show();
        }else{
            editor.putBoolean(function,false);
            editor.putInt("CountFavourite",sharedPreferences.getInt("CountFavourite",0)-1);
            editor.apply();
            Toast.makeText(getContext(),R.string.cancel_favourite,Toast.LENGTH_SHORT).show();
        }
    }
}