package com.example.tranquocdung.todo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by nguyenhuyvu on 1/8/15.
 */
public class SecondActivity extends ActionBarActivity {
    //Các hằng dùng cho tạo Option Menu
    private static final int DELETE_WORK = Menu.FIRST;
    private static final int ABOUT = Menu.FIRST + 2;
    ArrayList<Work> array;
    ListWorkApdapter arrayAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        array = new ArrayList<Work>();
        arrayAdapter = new ListWorkApdapter(this,
                R.layout.activity_second, array);
        final EditText workEnter = (EditText)
                findViewById(R.id.work_enter);
        final EditText hourEdit = (EditText)
                findViewById(R.id.hour_edit);
        final EditText minuteEdit = (EditText)
                findViewById(R.id.minute_edit);
        final Button button = (Button)
                findViewById(R.id.button);
        //Tạo list view cho danh sách công việc
        final ListView list = (ListView)
                findViewById(R.id.list);
        list.setAdapter(arrayAdapter);
        View.OnClickListener add = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if
                        (workEnter.getText().toString().equals("") ||
                        hourEdit.getText().toString().equals("") ||
                        minuteEdit.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new
                            AlertDialog.Builder(SecondActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                            builder.setPositiveButton("Continue", new
                                    DialogInterface.OnClickListener() {
                                        public void
                                        onClick(DialogInterface dialog, int which) {
                                            // TODO Auto-generated method stub
                                        }
                                    });
                    builder.show();
                }
                else {
                    String workContent =
                            workEnter.getText().toString();
                    String timeContent =
                            hourEdit.getText().toString() + ":"
                                    +
                                    minuteEdit.getText().toString();
                    Work work = new Work(workContent,
                            timeContent);
                    array.add(0, work);
                    arrayAdapter.notifyDataSetChanged();
                    workEnter.setText("");
                    hourEdit.setText("");
                    minuteEdit.setText("");
                }
            } };
        button.setOnClickListener(add);
    }
    //Tạo Option Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, DELETE_WORK, 0,"Delete"
        ).setIcon(android.R.drawable.ic_delete);
        menu.add(0, ABOUT, 0,"About"
        ).setIcon(android.R.drawable.ic_menu_info_details); return true;
    }
    //Xử lý sự kiện khi các option trong Option Menu được lựa chọn
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_WORK: {
                deleteCheckedWork();
                break;
            }
            case ABOUT: {
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(this);
                builder.setTitle("VietAndroid");
                builder.setMessage("AUTHOR:" + "\n" + " Nguyen Anh Tuan" + "\n" + "SOURCE:" + "\n" + " diendan.vietandroid.com");
                builder.setPositiveButton("Close", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface
                                                        dialog, int which) {
                            }
                        });
                builder.setIcon(android.R.drawable.ic_dialog_info); builder.show();
                break; }
        }
        return true;
    }
    private void deleteCheckedWork() {
        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                if (i > array.size()) {
                    break; }
                if (array.get(i).isChecked()) {
                    array.remove(i);
                    arrayAdapter.notifyDataSetChanged();
                    continue;
                } }
        }
    }
}
