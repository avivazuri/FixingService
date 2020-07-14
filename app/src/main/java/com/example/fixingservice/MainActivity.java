package com.example.fixingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btn_schedule;
    ImageButton contactsBtn;
    ImageButton callBtn;
    ImageButton alramBtn;
    Button setBtn;
    Spinner spinTime;
    String spinS;
    int hour;

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_schedule = findViewById(R.id.btn_schedule);
        btn_schedule.setOnClickListener(new ScheduleButtonListener());

        contactsBtn = findViewById(R.id.add_contacts);
        contactsBtn.setOnClickListener(new MainActivity.contactsBtnListener());

        callBtn = findViewById(R.id.call_technician);
        callBtn.setOnClickListener(new MainActivity.callBtnListener());

        alramBtn = findViewById(R.id.set_alaram);
        alramBtn.setOnClickListener(new MainActivity.alaramBtnListener());

        spinTime = (Spinner)findViewById(R.id.in_time2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.time_array2,android.R.layout.select_dialog_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinTime.setAdapter(adapter);
        spinTime.setOnItemSelectedListener(this);
    }

    public void onClick(View v) {

        if (v == spinTime) {
            spinTime.performClick();
        }
    }

    private class ScheduleButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }

    private class contactsBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
            contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, getResources().getString(R.string.app_name));
            contactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, "17007005555");
            startActivityForResult(contactIntent, 1);
        }
    }

    private class callBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Intent.ACTION_DIAL);
            String number="17007005555";
            intent.setData(Uri.parse("tel:"+number));
            startActivity(intent);
        }
    }

    private class alaramBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            spinS = spinTime.getSelectedItem().toString();
            hour = Integer.parseInt(spinS.substring(0, 2));
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, 00);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, getResources().getString(R.string.callremind_str));

            startActivity(intent);
        }
    }
}


