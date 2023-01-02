package com.example.matirialvolley.UI;


import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matirialvolley.R;
import com.example.matirialvolley.Sett.TokenSaver;
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
        binding.gotoSingUpBtn.setOnClickListener(v -> {
            binding.gotoSingUpBtn.setBackground(this.getResources().getDrawable(R.drawable.btn_backgroundselected));
            binding.gotoLogInBtn.setBackground(this.getResources().getDrawable(R.drawable.btn_backgrounselected));
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        });
        binding.signUpText.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        });
        binding.show.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked ) {
                binding.show.setBackground(getDrawable(R.drawable.ic_baseline_lock_open));
                binding.edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                binding.show.setBackground(getDrawable(R.drawable.ic_baseline_lock_24));
                binding.edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

        });
        binding.btnLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Try login     ", Toast.LENGTH_SHORT).show();
            if (binding.checkBox2.isChecked()) {
                url = "https://studentucas.awamr.com/api/auth/login/delivery";

            } else {
                url = "https://studentucas.awamr.com/api/auth/login/user";

            }
            if (isValid()) {
                postReq();
            }
        });

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

                    String token = "Bearer " + response.getJSONObject("data").getString("token");
                    Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                    TokenSaver.setToken(this,token);//تخزين الكود
                    Log.e("Statee",TokenSaver.getToken(this));
                    TokenSaver.setIsDelevery(this,binding.checkBox2.isChecked());// تخزين نوع العميل لاستخدامها بشاشة السبلاش
                    Log.e("Statee",TokenSaver.IsDelevery(this)+"");

                    if (TokenSaver.IsDelevery(this)) {
                        Log.e("Statee","goto homeReq");

                        startActivity(new Intent(getApplicationContext(), DeliveryHome.class));
                        finish();
                    } else {
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