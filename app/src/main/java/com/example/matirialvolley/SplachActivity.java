package com.example.matirialvolley;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
 import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.matirialvolley.databinding.ActivitySplachBinding;


public class SplachActivity extends AppCompatActivity {
    ActivitySplachBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySplachBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot()); // عمل فيو لها
    }
    @Override
    protected void onStart() {
        super.onStart();
        controlSplashActivity();
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void controlSplashActivity() {
        new Handler().postDelayed(() -> {

            SharedPreferences prefs = getSharedPreferences("TokenSaver", MODE_PRIVATE);  // استدعاء ملف البريفرانس
            String token = prefs.getString("Token", ""); // احضار قيمة التوكن
            boolean isDelivery = prefs.getBoolean("isDelivery", false);
            // تحديد وجهة المستخدم حسب المتغيرات أعلاه (هل متوفر التوكن اولا ++ هل هو مقدم خدمة أو زبون )

            if (prefs.getBoolean("isFirst",true)) {
                startActivity(new Intent(getApplicationContext(), WelcomingActivity.class));
                finish();
            }else {
            if (token.equals("")) {
                Toast.makeText(getApplicationContext(), "token not exist ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
            } else {
                if (isDelivery ) {
                    startActivity(new Intent(getApplicationContext(), DeliveryHome.class));
                    finish();
                }else {
                    startActivity(new Intent(getApplicationContext(), CustomerHome.class));
                    finish();
                }
                Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
            }}

        }, 2000);
    }


}
