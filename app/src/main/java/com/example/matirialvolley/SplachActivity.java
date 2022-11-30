package com.example.matirialvolley;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
 import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.matirialvolley.Sett.TokenSaver;
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
            // تحديد وجهة المستخدم حسب المتغيرات أعلاه (هل متوفر التوكن اولا ++ هل هو مقدم خدمة أو زبون )

            if (TokenSaver.IsFirst(this)) {
                startActivity(new Intent(getApplicationContext(), WelcomingActivity.class));
                finish();
            }else {
            if (TokenSaver.getToken(this).equals("")) {
                Toast.makeText(getApplicationContext(), "token not exist ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
            } else {
                if (TokenSaver.IsDelevery(this) ) {
                    startActivity(new Intent(getApplicationContext(), DeliveryHome.class));
                    finish();
                }else {
                    startActivity(new Intent(getApplicationContext(), CustomerHome.class));
                    finish();
                }
            }}

        }, 2000);
    }


}
