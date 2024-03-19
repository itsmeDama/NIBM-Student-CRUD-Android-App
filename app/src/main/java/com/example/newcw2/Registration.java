package com.example.newcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button b1, b2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        ed1 = findViewById(R.id.RegID);
        ed2 = findViewById(R.id.name);
        ed3 = findViewById(R.id.age);
        ed4 = findViewById(R.id.gender);
        ed5 = findViewById(R.id.phone);
        ed6 = findViewById(R.id.parent);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(), veiw.class);
                startActivity(i);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

    }

    public void insert() {
        try {
            String RegID = ed1.getText().toString();
            String name = ed2.getText().toString();
            String age = ed3.getText().toString();
            String gender = ed4.getText().toString();
            String phone = ed5.getText().toString();
            String parent = ed6.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("studentDb", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(RegID INTEGER  PRIMARY KEY AUTOINCREMENT,name VARCHAR, age INTEGER, gender VARCHAR, phone VARCHAR, parent VARCHAR )");

            String sql = "insert into records(RegID,name,age,gender,phone,parent) values (?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, RegID);
            statement.bindString(2, name);
            statement.bindString(3, age);
            statement.bindString(4, gender);
            statement.bindString(5, phone);
            statement.bindString(6, parent);
            statement.execute();

            Toast.makeText(this, "Record added", Toast.LENGTH_SHORT).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, " Record failed", Toast.LENGTH_SHORT).show();

        }

    }
}