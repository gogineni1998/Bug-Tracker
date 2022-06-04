package com.dheeraj.android.ecilproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManagerPage extends AppCompatActivity {
    TextView t1,t2;
    Button b1,b2,b3,b4;
    String user;
    String h;
    String projecttitle=null;
   SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        setContentView(R.layout.activity_manager_page);
        t1=(TextView)findViewById(R.id.idview);
        t2=(TextView) findViewById(R.id.projectview);
        b1=(Button)findViewById(R.id.developersettings);
        b2=(Button)findViewById(R.id.testersettings);
        b3=(Button)findViewById(R.id.assignbugs);
        b4=(Button)findViewById(R.id.addreport);
        t1.setText(globalvariabel.GetUsername());
        user=globalvariabel.GetUsername();
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        Cursor c1=db.rawQuery("select * from Project_manager where id='"+user+"'", null);
        if(c1.moveToFirst())
        {
            projecttitle=c1.getString(1);
            t2.setText(projecttitle);
        }
        else
        {
            t2.setText(projecttitle);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ManagerPage.this,DeveloperSettings.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ManagerPage.this,TesterSettings.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ManagerPage.this,AssignBugs.class);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=globalvariabel.GetUsername();
                Cursor c5=db.rawQuery("select * from Project_manager where id='"+user+"'", null);
                if(c5.moveToFirst())
                {
                    projecttitle=c5.getString(1);

                }
                Cursor c1=db.rawQuery("select * from Report where project='"+projecttitle+"'", null);
                StringBuffer buffer=new StringBuffer();
                while (c1.moveToNext())
                {
                    h=c1.getString(0);
                    if(h.equals(projecttitle))
                    {
                        buffer.append("BUG: "+c1.getString(1)+"\n");
                        buffer.append("STATUS: "+c1.getString(2)+"\n\n");
                    }
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