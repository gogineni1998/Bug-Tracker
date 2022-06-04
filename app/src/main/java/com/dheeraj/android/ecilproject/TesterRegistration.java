package com.dheeraj.android.ecilproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TesterRegistration extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b;
    String user,password,mobileno;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester_registration);
        e1=(EditText)findViewById(R.id.testid);
        e2=(EditText)findViewById(R.id.testpassword);
        e3=(EditText)findViewById(R.id.testphoneno);
        b=(Button)findViewById(R.id.testreg);
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Regtester (id VARCHAR, password VARCHAR,mobile VARCHAR);");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=e1.getText().toString().trim();
                password=e2.getText().toString().trim();
                mobileno=e3.getText().toString().trim();
                if(user.length()==0||password.length()==0||mobileno.length()==0)
                {
                    Toast.makeText(TesterRegistration.this,"Please Enter all coloumns",Toast.LENGTH_SHORT).show();
                }
                if (mobileno.length()!=10)
                {
                    Toast.makeText(TesterRegistration.this,"Incorrect mobile number",Toast.LENGTH_SHORT).show();
                }
                Cursor c=db.rawQuery("select * from Regtester where id='"+user+"'", null);
                Cursor c1=db.rawQuery("select * from Regdeveloper where id='"+user+"'", null);
                Cursor c2=db.rawQuery("select * from Regmanager where id='"+user+"'", null);


                if(c.moveToFirst()||c1.moveToFirst()||c2.moveToFirst())
                {
                    Toast.makeText(TesterRegistration.this,"User Already Exists",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.execSQL("INSERT INTO RegTester (id,password,mobile) VALUES('"
                            + user + "','" + password + "','"+ mobileno + "');");
                    Toast.makeText(TesterRegistration.this,"Registration Done",Toast.LENGTH_SHORT).show();
                    clear();
                }
            }
        });
    }
    public  void clear()
    {
        e1.setText("");
        e2.setText("");
        e3.setText("");
    }
}
