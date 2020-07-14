package com.example.fixingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class SecondActivity extends AppCompatActivity {

    Button continueBtn;
    EditText nameEt;
    EditText phoneEt;
    EditText emailEt;
    EditText problemEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        continueBtn = findViewById(R.id.btn_continue);
        continueBtn.setOnClickListener(new ContinueButtonListener());

        nameEt = findViewById(R.id.in_name);
        phoneEt = findViewById(R.id.in_phone);
        emailEt = findViewById(R.id.in_emaul);
        problemEt = findViewById(R.id.in_problem);
    }

    private class ContinueButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            String nameStr = nameEt.getText().toString();
            String phoneStr = phoneEt.getText().toString();
            String emailStr = emailEt.getText().toString();
            String problemStr = problemEt.getText().toString();

            if (nameStr.matches("") || phoneStr.matches("") || emailStr.matches("") || problemStr.matches(""))
            {
                Toast.makeText(SecondActivity.this, getResources().getString(R.string.error_empty_str), Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(SecondActivity.this, CameraActivity.class);
                intent.putExtra("name", nameStr);
                intent.putExtra("phone", phoneStr);
                intent.putExtra("email", emailStr);
                startActivity(intent);
            }
        }
    }
}
