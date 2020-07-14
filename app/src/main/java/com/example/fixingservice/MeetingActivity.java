package com.example.fixingservice;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MeetingActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate;
    Button continueBtn;
    Spinner spinTime;
    Spinner spinTechnician;
    int mYear, mMonth, mDay;
    String spinnerTechnician;
    String date;
    String spinnerTimeString;
    String name;
    String phone;
    String email;
    int year, month, day;
    DatePickerDialog datePickerDialog;

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_schedule);

        btnDatePicker = (Button)findViewById(R.id.btn_date);
        btnTimePicker = (Button)findViewById(R.id.btn_time);
        txtDate = (EditText)findViewById(R.id.in_date);
        continueBtn = (Button)findViewById(R.id.continueBtn);

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");

        spinTime = (Spinner)findViewById(R.id.in_time);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.time_array,android.R.layout.select_dialog_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinTime.setAdapter(adapter);
        spinTime.setOnItemSelectedListener(this);

        spinTechnician = (Spinner)findViewById(R.id.in_technician);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.tech_array,android.R.layout.select_dialog_item);
        adapter2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinTechnician.setAdapter(adapter2);
        spinTechnician.setOnItemSelectedListener(this);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtDate.getText().toString().matches("")){
                    Toast.makeText(MeetingActivity.this, getResources().getString(R.string.errordate), Toast.LENGTH_SHORT).show();
                }

                else {
                    spinnerTimeString = spinTime.getSelectedItem().toString();
                    spinnerTechnician = spinTechnician.getSelectedItem().toString();
                    date = txtDate.getText().toString();
                    day = datePickerDialog.getDatePicker().getDayOfMonth();
                    month = datePickerDialog.getDatePicker().getMonth();
                    year = datePickerDialog.getDatePicker().getYear();

                    Intent intent = new Intent(MeetingActivity.this, SummaryActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("email", email);
                    intent.putExtra("technician", spinnerTechnician);
                    intent.putExtra("date", date);
                    intent.putExtra("time", spinnerTimeString);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    intent.putExtra("day", day);
                    startActivity(intent);
                }
            }
        });}

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();

            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            Calendar mcurrentDate = Calendar.getInstance();
            mcurrentDate.add(Calendar.DATE, 1);
            datePickerDialog.getDatePicker().setMinDate(mcurrentDate.getTimeInMillis());
            datePickerDialog.show();

        }

        if (v == spinTime) {
            spinTime.performClick();
        }


        if (v == spinTechnician) {
            spinTechnician.performClick();
        }
    }
}
