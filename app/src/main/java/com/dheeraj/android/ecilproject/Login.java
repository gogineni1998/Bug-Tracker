package com.dheeraj.android.ecilproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

EditText e1,e2;
    int developer=0,manager=0,tester=0,flag=0,flag1=0,flag2=0;
    CheckBox c1,c2,c3;
    SQLiteDatabase db;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.logid);
        e2=(EditText)findViewById(R.id.logpass);
        b=(Button) findViewById(R.id.logbutton);
          c1 = (CheckBox) findViewById(R.id.checkBox1);
          c2 = (CheckBox) findViewById(R.id.checkBox2);
          c3 = (CheckBox) findViewById(R.id.checkBox3);
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
       c1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               developer=1;
               manager=0;
               tester=0;
               c2.setChecked(false);
               c3.setChecked(false);
           }
       });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                developer=0;
                manager=1;
                tester=0;
                c1.setChecked(false);
                c3.setChecked(false);

            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                developer=0;
                manager=0;
                tester=1;
                c1.setChecked(false);
                c2.setChecked(false);

            }
        });
b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       String u=e1.getText().toString().trim();
        String p=e2.getText().toString().trim();
        globalvariabel.Setusername(u);

        if(u.length()==0||p.length()==0)
        {
            Toast.makeText(Login.this,"Enter all the fileds",Toast.LENGTH_SHORT).show();
            return;
        }
        if(u.equals("admin")&&p.equals("admin"))
        {
            clear();
            Intent i=new Intent(Login.this,Adminpage.class);
            startActivity(i);
        }
       else  if(developer==1)
        {
            Cursor dr = db.rawQuery("SELECT * FROM Regdeveloper",null);
            if (dr != null ) {
                if  (dr.moveToFirst()) {
                    do {
                        String f = dr.getString(dr.getColumnIndex("id"));
                        String l=dr.getString(dr.getColumnIndex("password"));
                        if(u.equals(f)&&p.equals(l))
                        {
                            flag=1;
                            clear();
                            Toast.makeText(Login.this,"FOUND",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(Login.this,DeveloperPage.class);
                            startActivity(i);
                            break;
                        }
                    }while (dr.moveToNext());
                    if(flag==0)
                    {
                        Toast.makeText(Login.this,"NOT FOUND",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        else  if(manager==1)
        {
            Cursor mr = db.rawQuery("SELECT * FROM Regmanager",null);
            if (mr != null ) {
                if  (mr.moveToFirst()) {
                    do {
                        String f = mr.getString(mr.getColumnIndex("id"));
                        String l=mr.getString(mr.getColumnIndex("password"));
                        if(u.equals(f)&&p.equals(l))
                        {
                            flag1=1;
                            clear();
                            Toast.makeText(Login.this,"FOUND",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(Login.this,ManagerPage.class);
                            startActivity(i);
                            break;
                        }
                    }while (mr.moveToNext());
                    if(flag1==0)
                    {
                        Toast.makeText(Login.this,"NOT FOUND",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        else  if(tester==1)
        {
            Cursor tr = db.rawQuery("SELECT * FROM Regtester",null);
            if (tr != null ) {
                if  (tr.moveToFirst()) {
                    do {
                        String f = tr.getString(tr.getColumnIndex("id"));
                        String l=tr.getString(tr.getColumnIndex("password"));
                        if(u.equals(f)&&p.equals(l))
                        {
                            flag2=1;
                            Toast.makeText(Login.this,"FOUND",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(Login.this,TesterPage.class);
                            startActivity(i);
                            break;
                        }
                    }while (tr.moveToNext());
                    if(flag2==0)
                    {
                        Toast.makeText(Login.this,"NOT FOUND",Toast.LENGTH_SHORT).show();
                    }
                }
            }
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
