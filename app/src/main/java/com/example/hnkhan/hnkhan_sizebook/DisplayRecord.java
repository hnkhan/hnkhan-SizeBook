package com.example.hnkhan.hnkhan_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.hnkhan.hnkhan_sizebook.MainActivity.recordsList;

public class DisplayRecord extends AppCompatActivity {

    private TextView displayRecord;
    private Records record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_record);

        displayRecord = (TextView) findViewById(R.id.display_record);

        Intent intent = getIntent();
        int position_ = intent.getIntExtra("position_id", 0);
        record = recordsList.get(position_);

        displayRecord.setText(record.getName());
    }
}
