package com.example.fixingservice;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SummaryActivity extends AppCompatActivity {

    TextView nameTV;
    TextView phoneTV;
    TextView emailTV;
    TextView technicianTV;
    TextView dateTv;
    TextView timeTv;
    String name;
    String phone;
    String email;
    String date;
    String time;
    int hour;
    String techincian;
    int year;
    int month;
    int day;
    Button finishBtn;
    ImageButton emailBtn;
    ImageButton calendarBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_details);

        nameTV = findViewById(R.id.details_name);
        phoneTV = findViewById(R.id.details_phone);
        emailTV = findViewById(R.id.details_email);
        technicianTV = findViewById(R.id.details_technician);
        dateTv = findViewById(R.id.details_date);
        timeTv = findViewById(R.id.details_time);

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        techincian = getIntent().getStringExtra("technician");

        year = getIntent().getExtras().getInt("year");
        month = getIntent().getExtras().getInt("month");
        day = getIntent().getExtras().getInt("day");

        hour = Integer.parseInt(time.substring(0, 2));

        nameTV.setText(getResources().getString(R.string.name_str) + " " + name);
        phoneTV.setText(getResources().getString(R.string.phone_str) + " " + phone);
        emailTV.setText(getResources().getString(R.string.email_str) + " " + email);
        technicianTV.setText(getResources().getString(R.string.technicianname_str) + " " + techincian);
        dateTv.setText(getResources().getString(R.string.date_str) + " " + date);
        timeTv.setText(getResources().getString(R.string.time_str) + " " + time);

        finishBtn = findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(new SummaryActivity.finishBtnListener());

        emailBtn = findViewById(R.id.send_email);
        emailBtn.setOnClickListener(new SummaryActivity.emailBtnListener());

        calendarBtn = findViewById(R.id.add_calendar);
        calendarBtn.setOnClickListener(new SummaryActivity.calendarBtnListener());


    }

    private class finishBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(SummaryActivity.this, getResources().getString(R.string.thankyou_str), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SummaryActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private class emailBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:contactus@fixingservice.com?subject=");
            intent.setData(data);
            startActivity(intent);
        }
    }

    private class calendarBtnListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {

                Calendar beginCal = Calendar.getInstance();
                beginCal.set(year, month, day, hour, 0);
                long startTime = beginCal.getTimeInMillis();
                Calendar endCal = Calendar.getInstance();
                endCal.set(year, month, day, hour+1,0);
                long endTime = endCal.getTimeInMillis();

                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, getResources().getString(R.string.subjectEmail_str));
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, getResources().getString(R.string.home_str));
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginCal.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis());
                intent.putExtra(CalendarContract.Events.STATUS, 1);
                intent.putExtra(CalendarContract.Events.VISIBLE, 0);
                intent.putExtra(CalendarContract.Events.HAS_ALARM, 1);
                startActivity(intent);
            }
    }

}
