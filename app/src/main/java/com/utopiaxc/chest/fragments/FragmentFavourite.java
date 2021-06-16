package com.utopiaxc.chest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.utopiaxc.chest.adapter.FunctionAdapter;
import com.utopiaxc.chest.beans.BeanUtil;
import com.utopiaxc.chest.databinding.FragmentFavouriteBinding;
import com.utopiaxc.chest.utils.FunctionsUtils;
import com.utopiaxc.chest.utils.VARIABLES;

import java.util.List;

public class FragmentFavourite extends Fragment {

    private FragmentFavouriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.listFavourite;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        FunctionsUtils functionsUtils = new FunctionsUtils(requireActivity(), VARIABLES.MODE_FAVOURITE);
        List<BeanUtil> list = functionsUtils.getUtilBeans();
        FunctionAdapter adapter = new FunctionAdapter(list);
        adapter.registerItemClickID();
        adapter.setOnClickListener();
        adapter.setOnLongClickListener(VARIABLES.MODE_FAVOURITE, requireActivity());
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}