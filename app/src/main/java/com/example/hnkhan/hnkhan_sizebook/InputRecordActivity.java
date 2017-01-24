package com.example.hnkhan.hnkhan_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

public class InputRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_record);

        Intent intent = getIntent();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_input_record);
    }
}
