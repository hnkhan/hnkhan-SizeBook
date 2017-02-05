package com.example.hnkhan.hnkhan_sizebook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.hnkhan.hnkhan_sizebook.MainActivity.FILENAME;
import static com.example.hnkhan.hnkhan_sizebook.MainActivity.adapter;
import static com.example.hnkhan.hnkhan_sizebook.MainActivity.recordsList;

public class EditRecord extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    EditText editName, editDate, editNeck, editBust, editChest, editWaist, editHip, editInseam, editComment;
    private Records record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        //Intent intent = getIntent();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);
        editName = (EditText) findViewById(R.id.editsave_name);
        editDate = (EditText) findViewById(R.id.editsave_date);
        editNeck = (EditText) findViewById(R.id.necksave_field);
        editBust = (EditText) findViewById(R.id.bustsave_field);
        editChest = (EditText) findViewById(R.id.chestsave_field);
        editWaist = (EditText) findViewById(R.id.waistsave_field);
        editHip = (EditText) findViewById(R.id.hipsave_field);
        editInseam = (EditText) findViewById(R.id.inseamsave_field);
        editComment = (EditText) findViewById(R.id.commentsave_field);

        Button saveRecord = (Button) findViewById(R.id.savesave_record_button);

        //load old record
        Intent intent = getIntent();
        final int position_ = intent.getIntExtra("position_id", 0);
        record = recordsList.get(position_);

        editName.setText(record.getName());

        if (record.getDate() != null) {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String formatted_date = dateformat.format(record.getDate());
            editDate.setText(formatted_date);
        }

        if (record.getNeck() != null) {
            editNeck.setText(record.getNeck().toString());
        }

        if (record.getBust() != null) {
            editBust.setText(record.getBust().toString());
        }

        if (record.getChest() != null) {
            editChest.setText(record.getChest().toString());
        }

        if (record.getWaist() != null) {
            editWaist.setText(record.getWaist().toString());
        }

        if (record.getHip() != null) {
            editHip.setText(record.getHip().toString());
        }

        if (record.getInseam() != null) {
            editInseam.setText(record.getInseam().toString());
        }

        if (record.getComment() != null) {
            editComment.setText(record.getComment());
        }

        //editDate.setText(record.getDate().toString());
        //editNeck.setText(record.getNeck().toString());
        //editBust.setText(record.getBust().toString());
        //editChest.setText(record.getBust().toString());
        //editWaist.setText(record.getWaist().toString());
        //editHip.setText(record.getHip().toString());
        //editInseam.setText(record.getInseam().toString());
        //editComment.setText(record.getComment());

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
                    Records record = new Records(editName.getText().toString());
                    //record.setName(editComment.getText().toString());

                    if (editDate.getText() != null) {
                        record.setDate(editDate.getText().toString());
                    }
                    //if they put nothing in the measurements, we will assign it a negative number
                    //so later we don't have to display the fields that have a negative value

                    //neck
                    if (editNeck.getText().toString().length() != 0) {
                        record.setNeck(round(Float.valueOf(editNeck.getText().toString())));
                    }

                    //bust
                    if (editBust.getText().toString().length() != 0) {
                        record.setBust(round(Float.valueOf(editBust.getText().toString())));
                    }

                    //chest
                    if (editChest.getText().toString().length() != 0) {
                        record.setChest(round(Float.valueOf(editChest.getText().toString())));
                    }

                    //waist
                    if (editWaist.getText().toString().length() != 0) {
                        record.setWaist(round(Float.valueOf(editWaist.getText().toString())));
                    }

                    //hip
                    if (editHip.getText().toString().length() != 0) {
                        record.setHip(round(Float.valueOf(editHip.getText().toString())));
                    }

                    //inseam
                    if (editInseam.getText().toString().length() != 0) {
                        record.setInseam(round(Float.valueOf(editInseam.getText().toString())));
                    }

                    //now we should check if the comment is empty
                    if (editComment.getText().toString().length() != 0) {
                        record.setComment(editComment.getText().toString());
                    }

                    setResult(RESULT_OK);
                    //delete record and read
                    recordsList.remove(position_);
                    recordsList.add(position_, record);
                    adapter.notifyDataSetChanged();
                    saveInFile();

/*                    Log.d("Name: ", record.getName());
                    Log.d("Name: ", record.getDate().toString());
                    Log.d("Name: ", record.getNeck().toString());
                    Log.d("Name: ", record.getBust().toString());
                    Log.d("Name: ", record.getChest().toString());
                    Log.d("Name: ", record.getWaist().toString());
                    Log.d("Name: ", record.getHip().toString());
                    Log.d("Name: ", record.getInseam().toString());
                    Log.d("Name: ", record.getInseam().toString());
                    Log.d("Name: ", record.getComment());*/

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
        editDate = (EditText) findViewById(R.id.editsave_date);
        editDate.setText(date_);
    }

    //http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public float round(float floatValue) {
        BigDecimal returnValue = new BigDecimal(floatValue);
        returnValue = returnValue.setScale(1, RoundingMode.HALF_UP);
        return returnValue.floatValue();
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(recordsList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the exception properly later
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
