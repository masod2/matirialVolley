package com.example.matirialvolley;


import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
 import com.example.matirialvolley.databinding.ActivityLogInBinding;

import org.json.JSONException;
import org.json.JSONObject;


public class LogInActivity extends AppCompatActivity {
    ActivityLogInBinding binding;
    RequestQueue queue;
    JsonObjectRequest objectRequest;
    String url, password, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot()); // عمل فيو لها
        queue = Volley.newRequestQueue(this);



        binding.btnLogin.setOnClickListener(v -> {

            url = "https://studentucas.awamr.com/api/auth/login/user";
            Toast.makeText(this, "Try login     ", Toast.LENGTH_SHORT).show();
            if (isValid()) {postReq();}});

    }

    private boolean isValid() {
        boolean isValid = false;
        if (binding.edEmail.getText().length() < 10 || binding.edEmail.getText().toString().equals("")) {
            binding.edEmail.setError("This field is required Do not leave it empty ");
            binding.edEmail.requestFocus();
            isValid = false;

        } else {
            email = binding.edEmail.getText().toString().trim();
            if (binding.edPassword.getText().length() < 3 || binding.edPassword.getText().toString().equals("")) {
                binding.edPassword.setError("This field is required Do not leave it empty ");
                binding.edPassword.requestFocus();
                isValid = false;

            } else {
                password = binding.edPassword.getText().toString().trim();
                isValid = true;
            }
        }
        return isValid;
    }

    public void postReq() {

        binding.progressBar2.setVisibility(View.VISIBLE);

        JSONObject jsonObject = new JSONObject(); // انشاء اوبجكت لارساله بالريكويست
        try {
            jsonObject.put("email", email); // تخزين المتغيرات بالاوبجكت
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }//catch end
        objectRequest = new JsonObjectRequest(POST, url,  //انشاء ريكويست جديد
                jsonObject, response -> {
            //فحص حالة استجابة السيرفر
            try {
                if (response.getBoolean("success")) {  // تخزين التوكن بشيرد برييفرنس فى حال نجاح تسجيل الدخول
                    String token = "Bearer "+   response.getJSONObject("data").getString("token");
                    Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                    SharedPreferences prefs = getSharedPreferences("TokenSaver", MODE_PRIVATE);
                    prefs.edit().putString("Token", token).apply();
                    if (binding.checkBox2.isChecked()) {
                        startActivity(new Intent(getApplicationContext(), DeliveryHome.class));
                        finish();
                    }else {
                        startActivity(new Intent(getApplicationContext(), CustomerHome.class));
                        finish();
                    }


                } else {
                    Toast.makeText(LogInActivity.this, " " + response.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            binding.progressBar2.setVisibility(View.INVISIBLE);
        }, error -> {          //end onResponse and start on eror

            binding.progressBar2.setVisibility(View.INVISIBLE);
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        });
        queue.add(objectRequest);
    } // end method postReq

}//end class LogInActivity