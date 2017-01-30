package com.example.hnkhan.hnkhan_sizebook;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InputRecordActivity extends AppCompatActivity {

    private Integer recordCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_record);

        //Intent intent = getIntent();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);

        Button saveRecord = (Button) findViewById(R.id.save_record_button);
        saveRecord.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                recordCount++;
                Intent intent = new Intent(InputRecordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        EditText editDate = (EditText) findViewById(R.id.edit_date);
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
}
