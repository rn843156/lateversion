package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
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

public class VolunteerLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView elderly;
    private TextView login;
    private TextView register;

    private EditText Log_email,Log_password;
    FirebaseAuth FBauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_login);
        elderly = findViewById(R.id.textView_asElderly);
        elderly.setOnClickListener(this);
        login = findViewById(R.id.button_volunteerLogin);
        login.setOnClickListener(this);
        register = findViewById(R.id.button_volunteerRegister);
        register.setOnClickListener(this);


        Log_email = findViewById(R.id.Email_Log_volunteer);
        Log_password = findViewById(R.id.Password_Log_volunteer);
        FBauth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.textView_asElderly){
            startActivity(new Intent(this, ElderlyLoginActivity.class));
        }
        else if (view.getId() == R.id.button_volunteerLogin){
            Login();

        }
        else if (view.getId() == R.id.button_volunteerRegister){
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
                        Toast.makeText(VolunteerLoginActivity.this,"Login Succeed!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(VolunteerLoginActivity.this, VolunteerActivity.class));
                    }
                    else
                    {
                        Toast.makeText(VolunteerLoginActivity.this,"Incorrect Email or PassWord!",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }
}