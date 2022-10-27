package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ElderlyLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView volunteer;
    private TextView login;
    private TextView register;

    private EditText Log_email,Log_password;
    private FirebaseAuth FBauth;

    private static String UserEmail = "default";  //whether use static？

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elderly_login);
        volunteer = findViewById(R.id.textView_asVolunteer);
        volunteer.setOnClickListener(this);
        login = findViewById(R.id.button_elderlyLogin);
        login.setOnClickListener(this);
        register = findViewById(R.id.button_elderlyRegister);
        register.setOnClickListener(this);

        Log_email = findViewById(R.id.Email_Log_elder);
        Log_password = findViewById(R.id.Password_Log_elder);
        FBauth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.textView_asVolunteer){
            startActivity(new Intent(this, VolunteerLoginActivity.class));
        }
        else if (view.getId() == R.id.button_elderlyLogin){
            Login();
        }
        else if (view.getId() == R.id.button_elderlyRegister){
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    public void Login()
    {

        String email = Log_email.getText().toString().trim();

        String password = Log_password.getText().toString().trim();

        if(email.isEmpty())
        {
            Log_email.setError("USERName can not be empty");
        }
        else if(password.isEmpty())
        {
            Log_password.setError("Password should not be empty");

        }
        else {
            FBauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ElderlyLoginActivity.this,"Login Succeed!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ElderlyLoginActivity.this, ElderlyActivity.class));
                        UserEmail = email;
                    }
                    else
                    {
                        Toast.makeText(ElderlyLoginActivity.this,"Incorrect Email or PassWord!",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }


    public static String GetUserEmail() {
        return  UserEmail;
    }
}