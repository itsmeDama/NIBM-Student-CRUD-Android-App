package com.example.newcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

    }


    public void registrationForm(View view) {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (username.getText().toString().equals("admin") && password.getText().toString().equals("12345")) {
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        openRegistration();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }
    public void openRegistration() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}