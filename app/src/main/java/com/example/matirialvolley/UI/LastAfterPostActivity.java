package com.example.matirialvolley.UI;



 import androidx.appcompat.app.AppCompatActivity;

 import android.app.Activity;
 import android.content.Intent;
 import android.net.Uri;
 import android.os.Bundle;

 import android.view.View;
 import android.widget.Button;

import com.example.matirialvolley.R;
 import com.example.matirialvolley.databinding.ActivityLastAfterPostBinding;


public class LastAfterPostActivity extends AppCompatActivity {
ActivityLastAfterPostBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLastAfterPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Find the button and set an onClickListener
        binding.buttonSelect.setOnClickListener(v -> {
            // Open the photo gallery to select a photo
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
             startActivityForResult(intent, 1);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the selected photo URI
            Uri selectedImageUri = data.getData();
             binding.imageView.setImageURI(selectedImageUri);
            //  Display the selected photo in an ImageView or do something else with it
        }
    }

}