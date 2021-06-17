package com.utopiaxc.chest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utopiaxc.chest.R;
import com.utopiaxc.chest.adapter.NbnhhshAdapter;
import com.utopiaxc.chest.beans.BeanNbnhhsh;
import com.utopiaxc.chest.databinding.ActivityNbnhhshBinding;
import com.utopiaxc.chest.utils.VARIABLES;
import com.utopiaxc.chest.utils.WebUtils;

import org.jsoup.nodes.Document;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ActivityNbnhhsh extends AppCompatActivity {
    private ActivityNbnhhshBinding binding;
    private HandlerNbnhhsh messageHandler;
    private String handlerMessage = "";
    private Context context;
    private List<BeanNbnhhsh> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNbnhhshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        messageHandler = new HandlerNbnhhsh(this.getMainLooper());
        context = this;
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.spinKit.setVisibility(View.VISIBLE);
                binding.spinKit.bringToFront();
                new Thread(new getResult(query)).start();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class getResult implements Runnable {
        String key;
        public getResult(String key) {
            this.key = key;
        }
        @Override
        public void run() {
            try {
                List<String> data = new ArrayList<>();
                data.add("text");
                data.add(URLEncoder.encode(key,"UTF-8"));
                Document document = WebUtils.postFromURL(VARIABLES.NbnhhshURL, data);
                JSONArray json = JSON.parseArray(Objects.requireNonNull(document).body().text());
                JSONObject jsonObject = JSONObject.parseObject(json.get(0).toString());
                if (!jsonObject.containsKey("trans")) {
                    list = new ArrayList<>();
                    list.add(new BeanNbnhhsh(1,getString(R.string.no_result)));
                    handlerMessage = "GetOver";
                    messageHandler.sendMessage(messageHandler.obtainMessage());
                    return;
                }
                String result = Objects.requireNonNull(jsonObject.get("trans")).toString().replace("[", "").replace("]", "").replaceAll("\"", "");
                String[] arr = result.split(",");
                list=new ArrayList<>();
                int flag_order=1;
                for (String line:arr)
                    list.add(new BeanNbnhhsh(flag_order++,line));
                handlerMessage = "GetOver";
                messageHandler.sendMessage(messageHandler.obtainMessage());
            } catch (Exception e) {
                e.printStackTrace();
                handlerMessage = "GetError";
                messageHandler.sendMessage(messageHandler.obtainMessage());
            }

        }
    }

    private class HandlerNbnhhsh extends Handler {
        public HandlerNbnhhsh(@NonNull Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (handlerMessage.equals("GetError")) {
                binding.spinKit.setVisibility(View.GONE);
                Toast.makeText(context, R.string.interface_error, Toast.LENGTH_SHORT).show();
            } else if (handlerMessage.equals("GetOver")) {
                RecyclerView recyclerView = binding.recyclerView;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                NbnhhshAdapter adapter = new NbnhhshAdapter(list);

                adapter.addChildClickViewIds(R.id.card_view);
                adapter.setOnItemChildClickListener((adapter1, view, position) -> {
                    BeanNbnhhsh copy = list.get(position);
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText(null, copy.getTitle());
                    clipboard.setPrimaryClip(clipData);
                    Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show();
                });

                recyclerView.setAdapter(adapter);
                binding.spinKit.setVisibility(View.GONE);
            }
        }
    }
}