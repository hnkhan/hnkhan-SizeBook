package com.example.hnkhan.hnkhan_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/*
This is the main activity where the list view appears
 */

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    ListView oldRecordsList;
    TextView displayCount;

    public static ArrayList<Records> recordsList;
    public static ArrayAdapter<Records> adapter;

    public static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        recordsList = new ArrayList<Records>();
        loadFromFile();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldRecordsList = (ListView) findViewById(R.id.records_list);
        oldRecordsList.setOnItemClickListener(this);
        registerForContextMenu(oldRecordsList);

        Button addRecord = (Button) findViewById(R.id.add_record_button);
        displayCount= (TextView) findViewById(R.id.records_count);

        displayCount.setText(": " + recordsList.size());

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

    //update the count
    @Override
    public void onResume(){
        super.onResume();
        displayCount.setText(": " + recordsList.size());
    }

    //on click for displaying the record
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Intent intent = new Intent(MainActivity.this, DisplayRecord.class);
        intent.putExtra("position_id", position);
        startActivity(intent);
    }

    //http://stackoverflow.com/questions/17207366/creating-a-menu-after-a-long-click-event-on-a-list-view
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.records_list) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.drop_menu, menu);
        }
    }

    //long click for editing and deleting
    //http://stackoverflow.com/questions/17207366/creating-a-menu-after-a-long-click-event-on-a-list-view
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(MainActivity.this, EditRecord.class);
                intent.putExtra("position_id", info.position);
                startActivity(intent);
                return true;
            case R.id.delete:
                setResult(RESULT_OK);
                recordsList.remove(info.position);
                adapter.notifyDataSetChanged();
                saveInFile();
                displayCount.setText(": " + recordsList.size());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //taken from lonelyTwitter
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Records>>(){}.getType();

            recordsList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            recordsList = new ArrayList<Records>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    //taken from lonelyTwitter
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
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
