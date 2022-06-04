package com.dheeraj.android.ecilproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminpage extends AppCompatActivity {
    Button b1,b2,b3,b4;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        b1=(Button)findViewById(R.id.registereddevelopersv);
        b2=(Button)findViewById(R.id.registeredtestersv);
        b3=(Button)findViewById(R.id.managersettings);
        b4=(Button)findViewById(R.id.Reportsa);
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer=new StringBuffer();
                Cursor c=db.rawQuery("SELECT * FROM Regdeveloper",null);
                while (c.moveToNext())
                {
                    buffer.append("ID: "+c.getString(0)+"\n");
                    buffer.append("Mobile No: "+c.getString(2)+"\n\n\n");
                }
                showMessage("ALL DEVELOPERS DETAILS",buffer.toString());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer=new StringBuffer();
                Cursor c=db.rawQuery("SELECT * FROM Regtester",null);
                while (c.moveToNext())
                {
                    buffer.append("ID: "+c.getString(0)+"\n");
                    buffer.append("Mobile No: "+c.getString(2)+"\n\n\n");
                }
                showMessage("ALL TESTERS DETAILS",buffer.toString());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Adminpage.this,ManagerSettings.class);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Cursor c1=db.rawQuery("select * from Report ", null);
                StringBuffer buffer=new StringBuffer();
                while (c1.moveToNext())
                {

                       buffer.append("Project :"+c1.getString(0)+"\n");
                        buffer.append("BUG: "+c1.getString(1)+"\n");
                        buffer.append("STATUS: "+c1.getString(2)+"\n\n");

                }
                showMessage("REPORT DETAILS",buffer.toString());
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
}
