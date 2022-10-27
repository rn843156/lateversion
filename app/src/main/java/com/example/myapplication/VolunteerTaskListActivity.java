package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class VolunteerTaskListActivity extends AppCompatActivity {


    private String[] data = {"TaskA","TaskB","TaskC","TaskD","TaskE","TaskF","TaskG","TaskH","TaskI","TaskJ","TaskK","TaskL",
                             "TaskM","TaskN","TaskO","TaskP" };
    int P = 0;

    private List<Task> TaskList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_task_list);

        initTaskList();

        TaskAdapter adapter = new TaskAdapter(VolunteerTaskListActivity.this, R.layout.task_item,TaskList);
        ListView lv = (ListView) findViewById(R.id.Tasks_list_data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                 Task task = TaskList.get(position);
                Toast.makeText(VolunteerTaskListActivity.this, "DDD"+task.getTitle(), Toast.LENGTH_LONG).show();



            }
        });


    }

    public void initTaskList() {
        for (int i = 0; i < 2; i++) {
            Task task = new Task("task1");
            TaskList.add(task);

            Task task1 = new Task("task_asd");
            TaskList.add(task1);

            Task apple = new Task("Task_apple");
            TaskList.add(apple);


        }
    }




}