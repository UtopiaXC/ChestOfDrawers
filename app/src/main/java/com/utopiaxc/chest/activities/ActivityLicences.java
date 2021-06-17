package com.utopiaxc.chest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.utopiaxc.chest.R;
import com.utopiaxc.chest.adapter.LicenseAdapter;
import com.utopiaxc.chest.beans.BeanLicense;
import com.utopiaxc.chest.databinding.ActivityLicencesBinding;
import com.utopiaxc.chest.utils.LicenseUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ActivityLicences extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.utopiaxc.chest.databinding.ActivityLicencesBinding binding = ActivityLicencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Context context = this;
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        List<BeanLicense> list = LicenseUtils.getLicenses();
        LicenseAdapter licenseAdapter=new LicenseAdapter(list);
        licenseAdapter.registerItemClickID();
        licenseAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.card_view) {
                BeanLicense beanLicense= (BeanLicense) adapter.getItem(position);
                Uri uri = Uri.parse(beanLicense.getLicense_link());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(licenseAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}