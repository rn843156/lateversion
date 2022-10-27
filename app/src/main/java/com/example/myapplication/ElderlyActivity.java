package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.ui.notifications.NotificationsFragment;
import com.example.myapplication.ui.notifications.NotificationsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityElderlyBinding;


import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;


public class ElderlyActivity extends AppCompatActivity{

    private ActivityElderlyBinding binding;

    private EditText Name_Profile,email_Profile,phone_Profile,Address_Profile,Description_Profile;
    private Button Update_Button;

    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);

        binding = ActivityElderlyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        BottomNavigationView navView = findViewById(R.id.nav_view); //

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_elderly);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(binding.navView, navController);

        //--------------------------------Try storage-------------------------------------

        //Update_Button = (Button)findViewById(R.id.update_button);
        //Name_Profile = findViewById(R.id.name_profile_elder);
        //Update_Button.setOnClickListener(NotificationsFragment.class);






    }


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
                Toast.makeText(this, "还没想好做啥", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout_item:
                startActivity(new Intent(ElderlyActivity.this,LoginActivity.class));
                break;
        }
        return true;
    }



   /*
    public void onClick(View view) {
        if (view.getId() == R.id.update_button){
            Update_profile();
        }

    }

    public void Update_profile() {

        Toast.makeText(ElderlyActivity.this, "开始尝试", Toast.LENGTH_LONG).show();


        String name =  Name_Profile.getText().toString().trim();
        HashMap<String,Object> users = new HashMap<>();
        users.put("Name",name);


    }
    */
}