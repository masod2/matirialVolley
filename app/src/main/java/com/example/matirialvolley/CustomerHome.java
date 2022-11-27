package com.example.matirialvolley;

import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matirialvolley.databinding.ActivityCustumerHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomerHome extends AppCompatActivity {
    ActivityCustumerHomeBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل
    SharedPreferences prefs;
    String token, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustumerHomeBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot());
        prefs = getSharedPreferences("TokenSaver", MODE_PRIVATE);
        token = prefs.getString("Token", "");

        binding.logOut.setOnClickListener(v -> {
            url = "https://studentucas.awamr.com/api/auth/logout";
            LogOut();
        });
        binding.loadcu.setOnClickListener(v -> {
            url = "https://studentucas.awamr.com/api/get/all/offer";
            postTokenToHome();
        });
    }

    private void LogOut() {
        Toast.makeText(this, "Start loging out", Toast.LENGTH_SHORT).show();
        binding.progressBar4.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
//انشاء ريكويست جديد
        //end onResponse
        JsonObjectRequest objectRequest = new JsonObjectRequest(POST, url, jsonObject
                , response -> {
            binding.progressBar4.setVisibility(View.INVISIBLE);
            //فحص حالة استجابة السيرفر
            try {
                if (response.getBoolean("success")) {                    //ما يحدث عند نجاح الاستقبال
                    Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.clear();
                    editor.apply();
                } else {
                    // (اخطاء مدخلات )ما يحدث عند فشل  الاستقبال
                    Toast.makeText(getApplicationContext(), response.getString("Unauthenticated") + response.getString("error"), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), response.getString("التوكن منتهى الصلاحية ") + response.getString("error"), Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            binding.progressBar4.setVisibility(View.INVISIBLE);

            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;

            }
        };
        binding.progressBar4.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(objectRequest);
    } // end method LogOut

    private void postTokenToHome() {
        Toast.makeText(this, "on postTokenToHome", Toast.LENGTH_SHORT).show();
        binding.progressBar4.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
//انشاء ريكويست جديد
        //end onResponse
        JsonObjectRequest objectRequest = new JsonObjectRequest(POST, url, jsonObject
                , response -> {
            binding.progressBar4.setVisibility(View.INVISIBLE);
            //فحص حالة استجابة السيرفر
            try {
                if (response.getBoolean("success")) {                    //ما يحدث عند نجاح الاستقبال

                    JSONArray jsonArray = response.getJSONArray("data");//قراءة الاري الموجود بالرد الذى تم استلامه
                    binding.reesponnse.setText(response.toString());
                    //    for (int i = 0; i < jsonArray.length(); i++) { // قراءة عناصر المصفوفة الموجودة بالرد
                    JSONObject jsonObject1 = jsonArray.getJSONObject(1); // قراءة العنصر iمن المصفوفة
                    //     String name = jsonObject1.getString("created_at");//قراءة كل قيمة بالاوبجيتك عل حدة حسب ال key الموجود بالسيرفر وتخزينها بمتغير محلى
                    //   int id = jsonObject1.getInt("user_id");
                    binding.data.setText(jsonObject1.toString());


                    //  }
                    Toast.makeText(getApplicationContext(), " " + response.getString("message"), Toast.LENGTH_SHORT).show();

                } else {

                    // (اخطاء مدخلات )ما يحدث عند فشل  الاستقبال
                    Toast.makeText(getApplicationContext(), " " + response.getString("error"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            binding.progressBar4.setVisibility(View.INVISIBLE);

            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;

            }
        };
        binding.progressBar4.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(objectRequest);
    } // end method postTokenToHome


}