package com.dheeraj.android.ecilproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SendReport extends AppCompatActivity {
    EditText e1,e2;
    CheckBox c1,c2;
    Button b;
    int a=0;
    int c=0;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);
        e1=(EditText)findViewById(R.id.one);
        e2=(EditText)findViewById(R.id.two);
        c1=(CheckBox)findViewById(R.id.three);
        c2=(CheckBox)findViewById(R.id.four);
        b=(Button)findViewById(R.id.five);
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Report (project VARCHAR, bug VARCHAR,status VARCHAR);");
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=1;
                c=0;
                c2.setChecked(false);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=0;
                c=1;
  c1.setChecked(false);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().length()==0||e2.getText().length()==0)
                {
                    clear();
                    Toast.makeText(SendReport.this,"Enter all fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(a==1) {
                    db.execSQL("INSERT INTO Report (project,bug,status) VALUES('"
                            + e1.getText().toString() + "','" + e2.getText().toString() + "','" + "FIXED" + "');");
                    clear();
                    Toast.makeText(SendReport.this,"Report Send",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    db.execSQL("INSERT INTO Report (project,bug,status) VALUES('"
                            + e1.getText().toString() + "','" + e2.getText().toString() + "','" + "UN-FIXED" + "');");
                    clear();
                    Toast.makeText(SendReport.this,"Report Send",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
    public  void clear()
    {
        e1.setText("");
        e2.setText("");
        c1.setChecked(false);
        c2.setChecked(false);
    }
}
