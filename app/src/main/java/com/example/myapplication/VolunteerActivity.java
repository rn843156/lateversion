package com.example.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VolunteerActivity extends AppCompatActivity {

    Button Tasklist;





    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.top_right_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_item:
                Toast.makeText(this, "为什么这里会和button冲突", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(VolunteerActivity.this,VolunteerTaskListActivity.class));
                return true;
            case R.id.logout_item:
                startActivity(new Intent(VolunteerActivity.this,LoginActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

//        Tasklist.findViewById(R.id.Button_TaskList_Volunteer);
//        Tasklist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(VolunteerActivity.this, "试试", Toast.LENGTH_SHORT).show();
//            }
//        });



    }


}