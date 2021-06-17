package com.utopiaxc.chest.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.utopiaxc.chest.R;
import com.utopiaxc.chest.databinding.ActivityBase64Binding;

import java.util.Base64;
import java.util.Objects;

public class ActivityBase64 extends AppCompatActivity {
    private Context context;
    private ActivityBase64Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBase64Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context=this;
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.buttonEncryption.setOnClickListener(v -> {
            InputMethodManager inputMethodManager= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
            String text=binding.textBefore.getText().toString();
            if (text.equals("")){
                new AlertDialog.Builder(context)
                        .setTitle(R.string.sorry)
                        .setMessage(R.string.no_text_input)
                        .setPositiveButton(R.string.confirm,null)
                        .create()
                        .show();
                return;
            }
            String ciphertext= Base64.getEncoder().encodeToString(text.getBytes());
            binding.textAfter.setText(ciphertext);
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(null, ciphertext);
            clipboard.setPrimaryClip(clipData);
            Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show();
        });
        binding.buttonDecrypt.setOnClickListener(v -> {
            InputMethodManager inputMethodManager= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
            String ciphertext=binding.textAfter.getText().toString();
            if (ciphertext.equals("")){
                new AlertDialog.Builder(context)
                        .setTitle(R.string.sorry)
                        .setMessage(R.string.no_text_input)
                        .setPositiveButton(R.string.confirm,null)
                        .create()
                        .show();
                return;
            }
            String text= new String(Base64.getDecoder().decode(ciphertext));
            binding.textBefore.setText(text);
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(null, text);
            clipboard.setPrimaryClip(clipData);
            Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show();
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
}