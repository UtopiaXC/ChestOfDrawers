package com.utopiaxc.chest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.utopiaxc.chest.beans.BeanUtil;
import com.utopiaxc.chest.databinding.FragmentAllBinding;
import com.utopiaxc.chest.adapter.FunctionAdapter;
import com.utopiaxc.chest.utils.FunctionsUtils;
import com.utopiaxc.chest.utils.VARIABLES;

import java.util.List;

public class FragmentAll extends Fragment {

    private FragmentAllBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAllBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.listAll;
        AddFunctionsEntrance(recyclerView);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //添加功能入口
    private void AddFunctionsEntrance(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        FunctionsUtils functionsUtils = new FunctionsUtils(requireActivity(), VARIABLES.MODE_ALL);
        List<BeanUtil> list = functionsUtils.getUtilBeans();

        //创建适配器
        FunctionAdapter adapter = new FunctionAdapter(list);
        adapter.registerItemClickID();

        // 设置子控件点击监听
        adapter.setOnClickListener();
        adapter.setOnLongClickListener(VARIABLES.MODE_ALL, requireActivity());

        //添加显示适配器
        recyclerView.setAdapter(adapter);
    }
}