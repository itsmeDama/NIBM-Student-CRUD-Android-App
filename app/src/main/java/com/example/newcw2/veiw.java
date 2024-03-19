package com.example.newcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class veiw extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles =new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw);

        SQLiteDatabase db = openOrCreateDatabase("studentDb", Context.MODE_PRIVATE, null);
        lst1=findViewById(R.id.list1);
        final Cursor c=db.rawQuery("select* from records",null);
        int RegID =c.getColumnIndex("RegID");
        int name =c.getColumnIndex("name");
        int age =c.getColumnIndex("age");
        int gender =c.getColumnIndex("gender");
        int phone =c.getColumnIndex("phone");
        int parent =c.getColumnIndex("parent");
        titles.clear();

        arrayAdapter=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<student> stud= new ArrayList<student>();


        if(c.moveToFirst()){
            do {

                student stu = new student();
                stu.RegID=c.getString(RegID);
                stu.name=c.getString(name);
                stu.age=c.getString(age);
                stu.gender=c.getString(gender);
                stu.phone=c.getString(phone);
                stu.parent=c.getString(parent);

                stud.add(stu);

                titles.add(stu.RegID + "\t" +
                        stu.name + "\t" +
                        stu.age + "\t" +
                        stu.gender + "\t" +
                        stu.phone + "\t" +
                        stu.parent);


            } while (c.moveToNext());{
                arrayAdapter.notifyDataSetChanged();
                lst1.invalidateViews();
                }
            }

            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String aa= titles.get (position);

                    student stu=stud.get(position);
                    Intent i=new Intent(getApplicationContext(),edit.class);

                    i.putExtra("RegID",stu.RegID);
                    i.putExtra("name",stu.name);
                    i.putExtra("age",stu.age);
                    i.putExtra("gender",stu.gender);
                    i.putExtra("phone",stu.phone);
                    i.putExtra("parent",stu.parent);
                    startActivity(i);

                }
            });
        }


    }
