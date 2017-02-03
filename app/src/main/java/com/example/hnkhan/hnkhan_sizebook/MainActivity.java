package com.example.hnkhan.hnkhan_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


// TODO onclick - > new screen. screen has 3 buttons edit and delete and save. edit enables focus.
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView oldRecordsList;
    public static ArrayList<Records> recordsList;
    public static ArrayAdapter<Records> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordsList = new ArrayList<Records>();

        oldRecordsList = (ListView) findViewById(R.id.records_list);
        oldRecordsList.setOnItemClickListener(this);
        Button addRecord = (Button) findViewById(R.id.add_record_button);

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

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        adapter = new ArrayAdapter<Records>(this, R.layout.record_item, recordsList);

        oldRecordsList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

    }
}
