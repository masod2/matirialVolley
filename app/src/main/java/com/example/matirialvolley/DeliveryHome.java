package com.example.matirialvolley;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matirialvolley.databinding.ActivityDeleveryHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeliveryHome extends AppCompatActivity {
    ActivityDeleveryHomeBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل
    String token, url;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleveryHomeBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot());
        prefs = getSharedPreferences("TokenSaver", MODE_PRIVATE);
        token = prefs.getString("Token", "");
        binding.progressBar3.setVisibility(View.INVISIBLE);
        binding.logout.setOnClickListener(v -> {
            url = "https://studentucas.awamr.com/api/auth/logout";
            // التحقق من وجود توكن
            if (!token.equals("")) {
                LogOut();


            } else {
                binding.responce.setText("token not exist please log in to load data ");

            }
        });
        binding.load.setOnClickListener(v -> {
            url = "https://studentucas.awamr.com/api/home/deliver";

// التحقق من وجود توكن
            if (!token.equals("")) {
                postTokenToHome();


            } else {
                binding.responce.setText("token not exist please log in to load data ");

            }
        });


    }

    private void LogOut() {
        Toast.makeText(this, "Start loging out", Toast.LENGTH_SHORT).show();
        binding.progressBar3.setVisibility(View.VISIBLE);
        Log.e("Stateee", "LogOut 1 ");

//انشاء ريكويست جديد
        JsonObjectRequest objectRequest = new JsonObjectRequest(GET, url, null
                , response -> {
            binding.progressBar3.setVisibility(View.INVISIBLE);
            Log.e("Stateee", "on Request 3 ");

            //فحص حالة استجابة السيرفر
            try {
                // يتم الانتقال لشاشة تسجيل الدخول بكل الحالات
                if (response.getBoolean("success")) {                    //ما يحدث عند نجاح الاستقبال
                    Log.e("Stateee", "on success 4 ");

                    Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.clear();
                    editor.apply();
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
            binding.progressBar3.setVisibility(View.INVISIBLE);

            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;

            }

        };
        binding.progressBar3.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(objectRequest);

    } // end method LogOut

    private void postTokenToHome() {
        binding.progressBar3.setVisibility(View.VISIBLE);
        Log.e("Statee", " on postTokenToHome 1");
        //انشاء ريكويست جديد
         JsonObjectRequest objectRequest = new JsonObjectRequest(POST, url, null
                , response -> {
            Log.e("Statee", " on response 2");

            binding.progressBar3.setVisibility(View.INVISIBLE);
            //فحص حالة استجابة السيرفر
            try {
                if (response.getBoolean("success")) {                    //ما يحدث عند نجاح الاستقبال
                    Log.e("Statee", " on success 3");

                    JSONArray jsonArray = response.getJSONArray("data");//قراءة الاري الموجود بالرد الذى تم استلامه
                    binding.responce.setText(response.toString());
                    Log.e("Statee", response.toString());

                    //    for (int i = 0; i < jsonArray.length(); i++) { // قراءة عناصر المصفوفة الموجودة بالرد
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0); // قراءة العنصر iمن المصفوفة
                    //     String name = jsonObject1.getString("created_at");//قراءة كل قيمة بالاوبجيتك عل حدة حسب ال key الموجود بالسيرفر وتخزينها بمتغير محلى
                    //   int id = jsonObject1.getInt("user_id");
                    binding.oneresponce.setText(jsonObject1.toString());
                    Log.e("Statee", jsonObject1.toString());



                    //  }
                    Toast.makeText(getApplicationContext(), " " + response.getString("message"), Toast.LENGTH_SHORT).show();
                    Log.e("Statee", " end of  success 4"+response.getString("message"));

                } else {

                    // (اخطاء مدخلات )ما يحدث عند فشل  الاستقبال
                    Toast.makeText(getApplicationContext(), " " + response.getString("error"), Toast.LENGTH_SHORT).show();
                    Log.e("Statee", " اخطاء مدخلات "+response.getString("message"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            binding.progressBar3.setVisibility(View.INVISIBLE);

            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;
            }
        };
        binding.progressBar3.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(objectRequest);
    } // end method postTokenToHome


}