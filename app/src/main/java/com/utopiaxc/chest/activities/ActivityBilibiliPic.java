package com.utopiaxc.chest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utopiaxc.chest.R;
import com.utopiaxc.chest.databinding.ActivityBilibiliPicBinding;
import com.utopiaxc.chest.utils.VARIABLES;
import com.utopiaxc.chest.utils.WebUtils;

import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityBilibiliPic extends AppCompatActivity {
    private ActivityBilibiliPicBinding binding;
    private Context context;
    private String handlerMessage = "";
    private HandlerBilibiliPic messageHandler;
    private String pic;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBilibiliPicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        messageHandler=new HandlerBilibiliPic(context.getMainLooper());

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.contains("AV") || query.contains("av") || query.contains("Av") || query.contains("aV")) {
                    query = query.replace("AV", "")
                            .replace("av", "")
                            .replace("Av", "")
                            .replace("aV", "");
                    binding.spinKit.setVisibility(View.VISIBLE);
                    binding.spinKit.bringToFront();
                    new Thread(new getPic(VARIABLES.BilibiliVideoMessageFromAVURL + query)).start();
                } else if (query.contains("BV") || query.contains("bv") || query.contains("Bv") || query.contains("bV")) {
                    binding.spinKit.setVisibility(View.VISIBLE);
                    binding.spinKit.bringToFront();
                    new Thread(new getPic(VARIABLES.BilibiliVideoMessageFromBVURL + query)).start();
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.warning)
                            .setMessage(R.string.bilibili_code_format_error)
                            .setPositiveButton(R.string.confirm, null)
                            .create()
                            .show();
                }
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

    class getPic implements Runnable {
        String URL;

        public getPic(String URL) {
            this.URL = URL;
        }

        @Override
        public void run() {
            try {
                Document document = WebUtils.getFromURL(URL);

                JSONObject jsonObject = JSON.parseObject(document.body().text());
                if (Integer.parseInt(jsonObject.get("code").toString())!=0){
                    handlerMessage="PicGetError";
                    messageHandler.sendMessage(messageHandler.obtainMessage());
                    return;
                }
                jsonObject=JSONObject.parseObject(jsonObject.get("data").toString());
                pic=jsonObject.get("pic").toString().replace("http","https");
                bitmap = getHttpBitmap(pic);

                handlerMessage="PicGetSuccessfully";
                messageHandler.sendMessage(messageHandler.obtainMessage());

            } catch (Exception e) {
                handlerMessage="PicGetError";
                messageHandler.sendMessage(messageHandler.obtainMessage());
                e.printStackTrace();
            }
        }
    }

    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return bitmap;

    }

    private class HandlerBilibiliPic extends Handler {
        public HandlerBilibiliPic(@NonNull Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            binding.spinKit.setVisibility(View.GONE);
            if (handlerMessage.equals("PicGetSuccessfully")){
                System.out.println(pic);
                binding.imageView.setImageBitmap(bitmap);
            }else if(handlerMessage.equals("PicGetError")){
                new AlertDialog.Builder(context)
                        .setTitle(R.string.sorry)
                        .setMessage(R.string.bilibili_http_error)
                        .setPositiveButton(R.string.confirm, null)
                        .create()
                        .show();
            }
        }
    }

}