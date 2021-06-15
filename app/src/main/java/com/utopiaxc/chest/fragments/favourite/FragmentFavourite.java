package com.utopiaxc.chest.fragments.favourite;

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
import com.utopiaxc.chest.adapter.FunctionAdapter;
import com.utopiaxc.chest.databinding.FragmentFavouriteBinding;
import com.utopiaxc.chest.utils.VARIABLES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentFavourite extends Fragment {

    private FragmentFavouriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("Favourite", Context.MODE_PRIVATE);
        RecyclerView recyclerView = binding.listFavourite;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        Map<String, Integer> map_icon = new HashMap<>();
        Map<String, String> map_second_title = new HashMap<>();
        List<String> list = new ArrayList<>();
        if (sharedPreferences.getInt("CountFavourite", 0) == 0) {
            map_icon.put(getString(R.string.no_favourite), R.drawable.ic_baseline_code_24);
            map_second_title.put(getString(R.string.no_favourite), getString(R.string.no_favourite_tip));
            list.add(getString(R.string.no_favourite));
            FunctionAdapter adapter = new FunctionAdapter(list, map_icon, map_second_title);
            recyclerView.setAdapter(adapter);
        } else {
            if (sharedPreferences.getBoolean(getString(R.string.function_weibo_title), false)) {
                map_icon.put(getString(R.string.function_weibo_title), R.drawable.weibo);
                map_second_title.put(getString(R.string.function_weibo_title), getString(R.string.function_weibo_second_title));
                list.add(getString(R.string.function_weibo_title));
            }
            if (sharedPreferences.getBoolean(getString(R.string.function_nbnhhsh_title), false)) {
                map_icon.put(getString(R.string.function_nbnhhsh_title), R.drawable.question_mark);
                map_second_title.put(getString(R.string.function_nbnhhsh_title), getString(R.string.function_nbnhhsh_second_title));
                list.add(getString(R.string.function_nbnhhsh_title));
            }


            FunctionAdapter adapter = new FunctionAdapter(list, map_icon, map_second_title);

            adapter.addChildClickViewIds(R.id.card_view);
            adapter.addChildLongClickViewIds(R.id.card_view);


            // 设置子控件点击监听
            adapter.setOnItemChildClickListener((adapter1, view, position) -> {
                if (view.getId() == R.id.card_view) {
                    if (position == VARIABLES.FUNCTIONS.WEIBO.ordinal()) {
                        Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_activityWeibo);
                    } else if (position == VARIABLES.FUNCTIONS.NBNHHSH.ordinal()) {
                        Navigation.findNavController(view).navigate(R.id.action_navigation_favourite_to_activityNbnhhsh);
                    }
                }
            });
            adapter.setOnItemChildLongClickListener((adapter12, view, position) -> {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String Tool = list.get(position);
                if (sharedPreferences.getBoolean(Tool, false)) {
                    editor.putBoolean(Tool, false);
                    editor.putInt("CountFavourite", sharedPreferences.getInt("CountFavourite", 0) - 1);
                    editor.apply();
                    Toast.makeText(getContext(), R.string.cancel_favourite, Toast.LENGTH_SHORT).show();
                }
                adapter.remove(Tool);
                if (sharedPreferences.getInt("CountFavourite", 0) == 0) {
                    map_icon.clear();
                    map_second_title.clear();
                    list.clear();
                    map_icon.put(getString(R.string.no_favourite), R.drawable.ic_baseline_code_24);
                    map_second_title.put(getString(R.string.no_favourite), getString(R.string.no_favourite_tip));
                    list.add(getString(R.string.no_favourite));
                    FunctionAdapter new_adapter = new FunctionAdapter(list, map_icon, map_second_title);
                    recyclerView.setAdapter(new_adapter);
                }
                return true;
            });
            recyclerView.setAdapter(adapter);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}