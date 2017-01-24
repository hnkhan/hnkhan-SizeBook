package com.example.hnkhan.hnkhan_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    private ListView recordsList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordsList = (ListView) findViewById(R.id.records_list);
        Button addRecord = (Button) findViewById(R.id.add_record_button);
        //Button deleteRecord = (Button) findViewById(R.id.delete_record_button);

        addRecord.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, InputRecordActivity.class);
                startActivity(intent);
            }
        });
    }
}
