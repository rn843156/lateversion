package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String[] options = {"Elderly", "Volunteer"};
    private Spinner spinner;
    private Button register;
    private Button BackToLogin;

    //--------------------------------------------------------

    private EditText REG_name,REG_email,REG_phone,REG_password;
    private FirebaseAuth FBauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spinner = findViewById(R.id.spinner);

        register = findViewById(R.id.button_register);
        register.setOnClickListener(this);

        BackToLogin = findViewById(R.id.button_backtologin);
        BackToLogin.setOnClickListener(this);
        //--------------------------------------------------------
        FBauth = FirebaseAuth.getInstance();
        REG_name = findViewById(R.id.editTextTextPersonName);
        REG_email = findViewById(R.id.editTextTextEmailAddress);
        REG_phone = findViewById(R.id.editTextPhone);
        REG_password = findViewById(R.id.editTextTextPassword);

        //--------------------------------------------------------
        ArrayAdapter ad = new ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, options);
        ad.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(ad);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),
                        options[i],
                        Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_register){
            Register();
        }
        else if(view.getId() == R.id.button_backtologin)
        {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

        }
    }

    public void Register()
    {
        String name = REG_name.getText().toString().trim();
        String email = REG_email.getText().toString().trim();
        String phone = REG_phone.getText().toString().trim();
        String password = REG_password.getText().toString().trim();

        if(email.isEmpty())
        {
            REG_email.setError("USERName can not be empty");
        }
        else if(password.isEmpty())
        {
            REG_password.setError("Password should not be empty");

        }
        else
        {
            FBauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(RegisterActivity.this,"success register!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"fail register"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        System.out.println(task.getException().getMessage());
                    }
                }
            });
        }



    }
}