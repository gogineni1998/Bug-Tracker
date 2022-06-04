package com.dheeraj.android.ecilproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManagerSettings extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2;
    String manager_id,project_title;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_settings);
b1=(Button)findViewById(R.id.registeredmanagersv);
        b2=(Button)findViewById(R.id.add);
        e1=(EditText)findViewById(R.id.pn);
        e2=(EditText)findViewById(R.id.mn);
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Project_manager (id VARCHAR,projecttitle VARCHAR);");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c1=db.rawQuery("SELECT * FROM  Regmanager",null);
                StringBuffer buffer=new StringBuffer();
                while (c1.moveToNext())
                {
                    buffer.append("ID: "+c1.getString(0)+"\n");
                    buffer.append("Mobile no: "+c1.getString(2)+"\n\n");
                }
                showMessage("ALL MANAGERS DETAILS",buffer.toString());
            }
        });
b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        manager_id=e1.getText().toString().trim();
        project_title=e2.getText().toString().trim();
        Cursor c3=db.rawQuery("SELECT * FROM  Regmanager  where id='"+manager_id+"'",null);
        if(manager_id.length()==0||project_title.length()==0)
        {
            Toast.makeText(ManagerSettings.this,"Fill all the Fields",Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor c=db.rawQuery("select * from Project_manager where id='"+manager_id+"'", null);
        if(c.moveToFirst())
        {
            Toast.makeText(ManagerSettings.this,"Manager Is Busy With Project",Toast.LENGTH_SHORT).show();
            return;
        }
         if(c3.moveToFirst())
        {
            db.execSQL("INSERT INTO Project_manager (id,projecttitle) VALUES('"
                    +  manager_id + "','"+ project_title+ "');");
            Toast.makeText(ManagerSettings.this,"Project Assigned To Manager",Toast.LENGTH_SHORT).show();
            clear();
        }
        else
         {
             Toast.makeText(ManagerSettings.this,"No Manager with these credientials ",Toast.LENGTH_SHORT).show();
         }
    }
});

    }
    public void showMessage(String tl,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(tl);
        builder.setMessage(msg);
        builder.show();
    }
    public  void clear()
    {
        e1.setText("");
        e2.setText("");
    }
}
