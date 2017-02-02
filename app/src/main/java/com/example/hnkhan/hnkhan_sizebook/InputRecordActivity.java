package com.example.hnkhan.hnkhan_sizebook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputRecordActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{

    EditText editName, editDate, editNeck, editBust, editChest, editWaist, editHip, editInseam, editComment;
    private Integer recordCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_record);

        //Intent intent = getIntent();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);
        editName = (EditText) findViewById(R.id.edit_name);
        editDate = (EditText) findViewById(R.id.edit_date);
        editNeck = (EditText) findViewById(R.id.neck_field);
        editBust = (EditText) findViewById(R.id.bust_field);
        editChest = (EditText) findViewById(R.id.chest_field);
        editWaist = (EditText) findViewById(R.id.waist_field);
        editHip = (EditText) findViewById(R.id.hip_field);
        editInseam = (EditText) findViewById(R.id.inseam_field);
        editComment = (EditText) findViewById(R.id.comment_field);

        Button saveRecord = (Button) findViewById(R.id.save_record_button);
        saveRecord.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                recordCount++;
                Records record = new Records(recordCount);

                //http://stackoverflow.com/questions/8573250/android-how-can-i-convert-string-to-date
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    Date date = dateformat.parse(editDate.getText().toString());
                    record.setDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                record.setName(editName.getText().toString());
                record.setNeck(Float.valueOf(editNeck.getText().toString()));
                record.setBust(Float.valueOf(editBust.getText().toString()));
                record.setChest(Float.valueOf(editComment.getText().toString()));
                record.setWaist(Float.valueOf(editWaist.getText().toString()));
                record.setHip(Float.valueOf(editHip.getText().toString()));
                record.setInseam(Float.valueOf(editInseam.getText().toString()));
                record.setComment(editComment.getText().toString());

                Intent intent = new Intent(InputRecordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        editDate.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDatePickerDialog();
            }
        });

    }

    public void showDatePickerDialog(){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date_ = year + "-" + (month+1) + "-" + day;
        editDate = (EditText) findViewById(R.id.edit_date);
        editDate.setText(date_);
    }
}
