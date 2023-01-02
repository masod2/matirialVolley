package com.example.matirialvolley.UI;

import static com.android.volley.Request.Method.GET;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matirialvolley.Sett.SpinAdapter;
import com.example.matirialvolley.Sett.TokenSaver;
import com.example.matirialvolley.databinding.ActivityCustumerHomeBinding;
import com.example.matirialvolley.models.DataWork;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerHome extends AppCompatActivity {
    ActivityCustumerHomeBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل
     String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustumerHomeBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot());

        binding.najjar.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), BindOrderActivity.class);
            intent.putExtra("work", 2);
            startActivity(intent);
            finish();
        });
        binding.hadad.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BindOrderActivity.class);
            intent.putExtra("work", 1);
            startActivity(intent);
            finish();
        });

        binding.logout.setOnClickListener(v -> {
            url = "https://studentucas.awamr.com/api/auth/logout";
            LogOut();
        });
    }


    private void LogOut() {
        String token = TokenSaver.getToken(this);
        Toast.makeText(this, "Start loging out", Toast.LENGTH_SHORT).show();
        binding.progressBar4.setVisibility(View.VISIBLE);
        Log.e("Stateee", "LogOut 1 ");

//انشاء ريكويست جديد
        JsonObjectRequest objectRequest = new JsonObjectRequest(GET, url, null
                , response -> {
            binding.progressBar4.setVisibility(View.INVISIBLE);
            Log.e("Stateee", "on Request 3 ");

            //فحص حالة استجابة السيرفر
            try {
                // يتم الانتقال لشاشة تسجيل الدخول بكل الحالات
                if (response.getBoolean("success")) {                    //ما يحدث عند نجاح الاستقبال
                    Log.e("Stateee", "on success 4 ");

                    TokenSaver.logout(this);
                    Log.e("Stateee", "  SharedPreferences  cleared 4 ");

                } else {

                    // (اخطاء مدخلات )ما يحدث عند فشل  الاستقبال
                    Toast.makeText(getApplicationContext(), response.getString("Unauthenticated") + response.getString("error"), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), response.getString("التوكن منتهى الصلاحية أو غير موجود   ") + response.getString("error"), Toast.LENGTH_SHORT).show();
                    Log.e("Stateee", "  input rong  5 ");

                }
                Log.e("Stateee", "  after intent   6 ");

                startActivity(new Intent(getApplicationContext(), LogInActivity.class)); // يتم الانتقال لشاشة تسجيل الدخول بكل الحالات
                finish();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
            binding.progressBar4.setVisibility(View.INVISIBLE);

            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;

            }

        };
        binding.progressBar4.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(objectRequest);

    } // end method LogOut



}