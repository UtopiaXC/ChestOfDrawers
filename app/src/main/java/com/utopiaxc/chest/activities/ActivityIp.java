package com.utopiaxc.chest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
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
import com.utopiaxc.chest.databinding.ActivityIpBinding;
import com.utopiaxc.chest.utils.VARIABLES;
import com.utopiaxc.chest.utils.WebUtils;

import org.jsoup.nodes.Document;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityIp extends AppCompatActivity {
    private ActivityIpBinding binding;
    private Context context;
    private HandlerIP messageHandler;
    private String handlerMessage = "";
    private String ip;
    private String country;
    private String state;
    private String city;
    private String isp;
    private String longitude;
    private String latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context=this;
        messageHandler=new HandlerIP(this.getMainLooper());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.textViewUseMine.setOnClickListener(v -> {
            binding.spinKit.setVisibility(View.VISIBLE);
            new Thread(new getIpInformation("")).start();
        });
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String rule = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$";
                Pattern pattern = Pattern.compile(rule);
                Matcher matcher = pattern.matcher(query);
                if (matcher.matches()) {
                    new Thread(new getIpInformation(query)).start();
                    binding.spinKit.setVisibility(View.VISIBLE);
                }else{
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.warning)
                            .setMessage(R.string.ip_format_error)
                            .setPositiveButton(R.string.confirm,null)
                            .create()
                            .show();
                }
                return true;
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

    class getIpInformation implements Runnable{
        String ip_to_query;
        public getIpInformation(String ip_to_query){
            this.ip_to_query=ip_to_query;
        }

        @Override
        public void run() {
            try{
                Document document= WebUtils.getFromURL(VARIABLES.IPInquireURL+ip_to_query);
                JSONObject jsonObject= JSON.parseObject(Objects.requireNonNull(document).body().text());
                country=jsonObject.getString("country");
                state=jsonObject.getString("regionName");
                city=jsonObject.getString("city");
                isp=jsonObject.getString("isp");
                longitude=jsonObject.getString("lon");
                latitude=jsonObject.getString("lat");
                ip=jsonObject.getString("query");
                handlerMessage="Success";
                messageHandler.sendMessage(messageHandler.obtainMessage());
            }catch (Exception e){
                e.printStackTrace();
                handlerMessage="Error";
                messageHandler.sendMessage(messageHandler.obtainMessage());
            }

        }
    }

    private class HandlerIP extends Handler {
        public HandlerIP(@NonNull Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            binding.spinKit.setVisibility(View.GONE);
            if (handlerMessage.equals("Success")){
                binding.linearLayout.setVisibility(View.VISIBLE);
                binding.textViewContentCountry.setText(country);
                binding.textViewContentProvince.setText(state);
                binding.textViewContentCity.setText(city);
                binding.textViewContentIp.setText(ip);
                binding.textViewContentIsp.setText(isp);
                binding.textViewContentLatitude.setText(latitude);
                binding.textViewContentLongitude.setText(longitude);
            }if (handlerMessage.equals("Error")){
                new AlertDialog.Builder(context)
                        .setTitle(R.string.sorry)
                        .setMessage(R.string.interface_error)
                        .setPositiveButton(R.string.confirm,null)
                        .create()
                        .show();
            }
        }
    }
}