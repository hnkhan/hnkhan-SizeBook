package com.example.hnkhan.hnkhan_sizebook;

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

import java.util.ArrayList;


// TODO onclick - > new screen. screen has 3 buttons edit and delete and save. edit enables focus.
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView oldRecordsList;
    public static ArrayList<Records> recordsList;
    public static ArrayAdapter<Records> adapter;
    public static TextView rowName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordsList = new ArrayList<Records>();

        oldRecordsList = (ListView) findViewById(R.id.records_list);
        oldRecordsList.setOnItemClickListener(this);
        registerForContextMenu(oldRecordsList);
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
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
