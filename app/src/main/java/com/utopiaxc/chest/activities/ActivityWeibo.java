package com.utopiaxc.chest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.utopiaxc.chest.R;
import com.utopiaxc.chest.adapter.WeiboAdapter;
import com.utopiaxc.chest.databinding.ActivityWeiboBinding;
import com.utopiaxc.chest.utils.VARIABLES;
import com.utopiaxc.chest.utils.WebUtils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityWeibo extends AppCompatActivity {
    ActivityWeiboBinding binding;
    private final Context context = this;
    private String handlerMessage = "";
    private Map<Integer, ArrayList<String>> weibo_map;
    private boolean isRefresh = false;
    private HandlerWeibo messageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeiboBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        messageHandler = new HandlerWeibo(this.getMainLooper());
        new Thread(new getWeibo()).start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class getWeibo implements Runnable {
        @Override
        public void run() {
            weibo_map = new HashMap<>();
            Document document = WebUtils.getFromURL(VARIABLES.WeiboURL);

            Elements elements = document.getElementsByTag("tbody");
            if (elements.isEmpty()) {
                handlerMessage = "WeiboGotFailed";
                messageHandler.sendMessage(messageHandler.obtainMessage());
                return;
            }
            elements = elements.first().getElementsByTag("tr");
            for (Element element : elements) {
                Elements elements_single = element.getElementsByTag("td");
                try {
                    int number = Integer.parseInt(elements_single.get(0).text());
                    Elements elements_goal = elements_single.get(1).getElementsByTag("a");
                    String title = elements_goal.get(0).text();
                    String url = elements_goal.get(0).attr("href");
                    ArrayList<String> list = new ArrayList<>();
                    list.add(title);
                    list.add("https://s.weibo.com" + url);
                    weibo_map.put(number, list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            handlerMessage = "LayoutRenderingOver";
            messageHandler.sendMessage(messageHandler.obtainMessage());
        }
    }

    private class HandlerWeibo extends Handler {
        public HandlerWeibo(@NonNull Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            binding.refresh.setRefreshing(false);
            if (handlerMessage.equals("LayoutRenderingOver")) {
                RecyclerView recyclerView = binding.recyclerView;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                WeiboAdapter adapter = new WeiboAdapter(weibo_map);
                adapter.addChildClickViewIds(R.id.card_view);
                adapter.addChildLongClickViewIds(R.id.card_view);
                adapter.setOnItemChildClickListener((adapter1, view, position) -> {
                    if (view.getId() == R.id.card_view) {
                        Uri uri = Uri.parse(weibo_map.get(position + 1).get(1));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                adapter.setOnItemChildLongClickListener((adapter12, view, position) -> {
                    if (view.getId() == R.id.card_view) {
                        String copy = (position + 1) + ". " + weibo_map.get(position + 1).get(0) + " : " + weibo_map.get(position + 1).get(1);
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText(null, copy);
                        clipboard.setPrimaryClip(clipData);
                        Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show();
                    }
                    return true;
                });
                recyclerView.setAdapter(adapter);
                if (isRefresh)
                    Toast.makeText(context, R.string.success_refresh, Toast.LENGTH_SHORT).show();
                binding.spinKit.setVisibility(View.GONE);
                binding.refresh.setVisibility(View.VISIBLE);
                binding.refresh.setOnRefreshListener(() -> {
                    isRefresh = true;
                    new Thread(new getWeibo()).start();
                });
            }
        }
    }

}