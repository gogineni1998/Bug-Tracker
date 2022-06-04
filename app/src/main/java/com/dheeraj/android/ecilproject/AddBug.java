package com.dheeraj.android.ecilproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBug extends AppCompatActivity {
EditText e1,e2;
    Button b;
    String project_title,bug_name;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bug);
        e1=(EditText)findViewById(R.id.adproject);
        e2=(EditText)findViewById(R.id.adbug);
        b=(Button)findViewById(R.id.adddbug);
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Project_bug (projecttitle VARCHAR,bugname VARCHAR);");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                project_title=e1.getText().toString().trim();
                bug_name=e2.getText().toString();
                if(project_title.length()==0||bug_name.length()==0)
                {
                    Toast.makeText(AddBug.this,"Enter all fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.execSQL("INSERT INTO Project_bug (projecttitle,bugname) VALUES('"
                            + project_title + "','" + bug_name + "');");
clear();
                    Toast.makeText(AddBug.this,"Bug Added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public  void clear()
    {
        e1.setText("");
        e2.setText("");
    }

}
