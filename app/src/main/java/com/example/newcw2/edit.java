package com.example.newcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ed1 = findViewById(R.id.RegID);
        ed2 = findViewById(R.id.name);
        ed3 = findViewById(R.id.age);
        ed4 = findViewById(R.id.gender);
        ed5 = findViewById(R.id.phone);
        ed6 = findViewById(R.id.parent);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i =getIntent();

        String t1=i.getStringExtra("RegID").toString();
        String t2=i.getStringExtra("name").toString();
        String t3=i.getStringExtra("age").toString();
        String t4=i.getStringExtra("gender").toString();
        String t5=i.getStringExtra("phone").toString();
        String t6=i.getStringExtra("parent").toString();

        ed1.setText(t1);
        ed2.setText(t2);
        ed3.setText(t3);
        ed4.setText(t4);
        ed5.setText(t5);
        ed6.setText(t6);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),veiw.class);
                startActivity(i);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });
    }
    public void Delete() {
        try {
            String RegID = ed1.getText().toString();



            SQLiteDatabase db = openOrCreateDatabase("studentDb", Context.MODE_PRIVATE, null);


            String sql = "delete from records  where RegID =?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, RegID);

            statement.execute();

            Toast.makeText(this, " Record Deleted", Toast.LENGTH_SHORT).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, " Fail to Delete Records", Toast.LENGTH_SHORT).show();

        }

    }


    public void Edit() {
        try {
            String RegID = ed1.getText().toString();
            String name = ed2.getText().toString();
            String age = ed3.getText().toString();
            String gender = ed4.getText().toString();
            String phone = ed5.getText().toString();
            String parent = ed6.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("nib m", Context.MODE_PRIVATE, null);


            String sql = "UPDATE records SET name=?, age=?, gender=?, phone=?, parent=? WHERE RegID=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, age);
            statement.bindString(3, gender);
            statement.bindString(4, phone);
            statement.bindString(5, parent);
            statement.bindString(6, RegID);
            statement.execute();

            Toast.makeText(this, " Records Updated", Toast.LENGTH_SHORT).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Failed to update record", Toast.LENGTH_SHORT).show();

        }

    }
}