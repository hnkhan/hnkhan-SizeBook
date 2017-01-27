package com.example.hnkhan.hnkhan_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InputRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_record);

        Intent intent = getIntent();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);

        Button addRecord = (Button) findViewById(R.id.save_record_button);

        addRecord.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(InputRecordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
