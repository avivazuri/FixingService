package com.example.fixingservice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {

    final int PHOTO_REQUEST = 1;
    final int VIDEO_REQUEST = 2;
    ImageButton pictureBtn;
    ImageButton videoBtn;
    ImageButton playBtn;
    ImageView imageView;
    VideoView videoView;
    Button continueBtn;
    Uri videoFileUri;
    String name;
    String phone;
    String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);

        continueBtn = findViewById(R.id.btn_continue);
        pictureBtn = findViewById(R.id.pic_button);
        videoBtn = findViewById(R.id.video_button);
        imageView = findViewById(R.id.imageView1);
        videoView = findViewById(R.id.imageView2);
        playBtn = findViewById(R.id.play_button);
        playBtn.setEnabled(false);

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");

        continueBtn.setOnClickListener(new CameraActivity.ContinueButtonListener());
        pictureBtn.setOnClickListener(new CameraActivity.PictureButtonListener());
        videoBtn.setOnClickListener(new CameraActivity.VideoButtonListener());
        playBtn.setOnClickListener(new CameraActivity.PlayButtonListener());
    }

    private class ContinueButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            if(imageView.getDrawable() == null)
            {
                Toast.makeText(CameraActivity.this, getResources().getString(R.string.errorcamera_str), Toast.LENGTH_SHORT).show();
            }

            else {
                Intent intent = new Intent(CameraActivity.this, MeetingActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        }
    }

    private class PictureButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Intent photoIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(photoIntent, PHOTO_REQUEST);
        }
    }


    private class VideoButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Intent videoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        }
    }

    private class PlayButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

                videoView.setVideoURI(videoFileUri);
                videoView.start();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PHOTO_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            Toast.makeText(CameraActivity.this, getResources().getString(R.string.picturesaved_str), Toast.LENGTH_SHORT).show();
        }

        if (requestCode == VIDEO_REQUEST && resultCode == Activity.RESULT_OK)
        {
            videoFileUri = data.getData();
            videoView.setVideoURI(videoFileUri);
            playBtn.setEnabled(true);
            Toast.makeText(CameraActivity.this, getResources().getString(R.string.videosaved_str), Toast.LENGTH_SHORT).show();
        }



    }
}

