package com.example.hnkhan.hnkhan_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import static com.example.hnkhan.hnkhan_sizebook.MainActivity.recordsList;

/*
This class for displaying a record activity
 */

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

        displayRecord.setText("Name: " + record.getName());

        //if there not null we will display them. otherwise not to save space
        if (record.getDate() != null) {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String formatted_date = dateformat.format(record.getDate());
            displayRecord.append("\r\n" + "Date: " + formatted_date);
        }

        if (record.getNeck() != null) {
            displayRecord.append("\r\n" + "Neck: " + record.getNeck().toString());
        }

        if (record.getBust() != null) {
            displayRecord.append("\r\n" + "Bust: " + record.getBust().toString());
        }

        if (record.getChest() != null) {
            displayRecord.append("\r\n" + "Chest: " + record.getChest().toString());
        }

        if (record.getWaist()!= null) {
            displayRecord.append("\r\n" + "Waist: " + record.getWaist().toString());
        }

        if (record.getHip() != null) {
            displayRecord.append("\r\n" + "Hip: " + record.getHip().toString());
        }

        if (record.getInseam() != null) {
            displayRecord.append("\r\n" + "Inseam: " + record.getInseam().toString());
        }

        if (record.getComment() != null) {
            displayRecord.append("\r\n" + "Comment: " + record.getComment());
        }
    }
}
