package com.example.matirialvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.matirialvolley.databinding.ActivityWelcomingBinding;


public class WelcomingActivity extends AppCompatActivity {
    ActivityWelcomingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final int[] i = {1};
        binding.button2.setOnClickListener(v -> {
            if (i[0] == 1) {
                binding.imageView6.setImageDrawable(getDrawable(R.drawable.fbng));
                binding.textView.setText("   Fast reservation with technicians \n" +
                        "               and craftsmen");
                i[0]++;
            } else if (i[0] == 2) {
                binding.imageView6.setImageDrawable(getDrawable(R.drawable.ppp));
                binding.textView.setText("   Sign in to take advantage of the  \n" +
                        "              app's unique features");
                i[0]++;
            } else {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
                SharedPreferences prefs = getSharedPreferences("TokenSaver", MODE_PRIVATE);
                prefs.edit().putBoolean("isFirst", false).apply(); // تخزين العملية  لاستخدامها بشاشة السبلاش
            }

        });
    }
}