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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.hnkhan.hnkhan_sizebook.MainActivity.adapter;
import static com.example.hnkhan.hnkhan_sizebook.MainActivity.recordsList;

// TODO -handle empty dates

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

        //start of "on saveRecord button click"
        saveRecord.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                boolean nameValid, neckValid, bustValid, chestValid,
                        waistValid, hipValid, inseamValid = false;

                //must enter a name
                if (editName.getText().toString().length() == 0) {
                    editName.setError("You must enter a name");
                    nameValid = false;
                } else {
                    nameValid = true;
                }

                //make sure neck measurement is between 0 and 200
                if ((editNeck.getText().toString().length() != 0) &&
                        (Float.valueOf(editNeck.getText().toString()) < 0 ||
                        Float.valueOf(editNeck.getText().toString()) > 200)) {
                    editNeck.setError("Enter a measurement between 0 and 200");
                    neckValid = false;
                } else {
                    neckValid = true;
                }

                //make sure bust measurement is between 0 and 200
                if ((editBust.getText().toString().length() != 0) &&
                        (Float.valueOf(editBust.getText().toString()) < 0 ||
                        Float.valueOf(editBust.getText().toString()) > 200)) {
                    editBust.setError("Enter a measurement between 0 and 200");
                    bustValid = false;
                } else {
                    bustValid = true;
                }

                //make sure chest measurement is between 0 and 200
                if ((editChest.getText().toString().length() != 0) &&
                        (Float.valueOf(editChest.getText().toString()) < 0 ||
                        Float.valueOf(editChest.getText().toString()) > 200)) {
                    editChest.setError("Enter a measurement between 0 and 200");
                    chestValid = false;
                } else {
                    chestValid = true;
                }

                //make sure waist measurement is between 0 and 200
                if ((editWaist.getText().toString().length() != 0) &&
                        (Float.valueOf(editWaist.getText().toString()) < 0 ||
                        Float.valueOf(editWaist.getText().toString()) > 200)) {
                    editWaist.setError("Enter a measurement between 0 and 200");
                    waistValid = false;
                } else {
                    waistValid = true;
                }

                //make sure hip measurement is between 0 and 200
                if ((editHip.getText().toString().length() != 0) &&
                        (Float.valueOf(editHip.getText().toString()) < 0 ||
                        Float.valueOf(editHip.getText().toString()) > 200)) {
                    editHip.setError("Enter a measurement between 0 and 200");
                    hipValid = false;
                } else {
                    hipValid = true;
                }

                //make sure inseam measurement is between 0 and 200
                if ((editInseam.getText().toString().length() != 0) &&
                        (Float.valueOf(editInseam.getText().toString()) < 0 ||
                        Float.valueOf(editInseam.getText().toString()) > 200)) {
                    editInseam.setError("Enter a measurement between 0 and 200");
                    inseamValid = false;
                } else {
                    inseamValid = true;
                }

                //if all the information is valid
                if (nameValid && neckValid && bustValid && chestValid &&
                    waistValid && hipValid && inseamValid) {
                    //now we can create an instance of the records class and start
                    //setting the data because we know its all valid
                    recordCount++;
                    Records record = new Records(recordCount);

                    record.setName(editComment.getText().toString());

                    //http://stackoverflow.com/questions/8573250/android-how-can-i-convert-string-to-date
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date date = dateformat.parse(editDate.getText().toString());
                        record.setDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //if they put nothing in the measurements, we will assign it a negative number
                    //so later we don't have to display the fields that have a negative value

                    //neck
                    if (editNeck.getText().toString().length() == 0) {
                        record.setNeck(-1f);
                    } else {
                        record.setNeck(round(Float.valueOf(editNeck.getText().toString())));
                    }

                    //bust
                    if (editBust.getText().toString().length() == 0) {
                        record.setBust(-1f);
                    } else {
                        record.setBust(round(Float.valueOf(editBust.getText().toString())));
                    }

                    //chest
                    if (editChest.getText().toString().length() == 0) {
                        record.setChest(-1f);
                    } else {
                        record.setChest(round(Float.valueOf(editChest.getText().toString())));
                    }

                    //waist
                    if (editWaist.getText().toString().length() == 0) {
                        record.setWaist(-1f);
                    } else {
                        record.setWaist(round(Float.valueOf(editWaist.getText().toString())));
                    }

                    //hip
                    if (editHip.getText().toString().length() == 0) {
                        record.setHip(-1f);
                    } else {
                        record.setHip(round(Float.valueOf(editHip.getText().toString())));
                    }

                    //inseam
                    if (editInseam.getText().toString().length() == 0) {
                        record.setInseam(-1f);
                    } else {
                        record.setInseam(round(Float.valueOf(editInseam.getText().toString())));
                    }

                    //now we should check if the comment is empty
                    if (editComment.getText().toString().length() == 0) {
                        //fill it to a special character so we dont display
                    } else {
                        record.setComment(editComment.getText().toString());
                    }

                    setResult(RESULT_OK);
                    recordsList.add(record);
                    adapter.notifyDataSetChanged();

                    finish();

                    //Intent intent = new Intent(InputRecordActivity.this, MainActivity.class);
                    //startActivity(intent);
                }
            }
        });
        //end of "on saveRecord button click"

        //date picker
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

    //http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public float round(float floatValue) {
        BigDecimal returnValue = new BigDecimal(floatValue);
        returnValue = returnValue.setScale(1, RoundingMode.HALF_UP);
        return returnValue.floatValue();
    }
}
