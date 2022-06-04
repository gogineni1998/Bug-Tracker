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

public class DeveloperPage extends AppCompatActivity {
TextView t1,t2;
    Button b1,b2;
    SQLiteDatabase db;
    String user;
    String projecttitle=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_page);
        t1=(TextView)findViewById(R.id.developeridview);
        t2=(TextView)findViewById(R.id.developerprojectview);
        b1=(Button)findViewById(R.id.viewbug);
        b2=(Button)findViewById(R.id.sendreport);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        t1.setText(globalvariabel.GetUsername());
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        user=globalvariabel.GetUsername();
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
                StringBuffer buffer = new StringBuffer();
                Cursor c1 = db.rawQuery("SELECT * FROM  Project_assignbug", null);
                while (c1.moveToNext()) {
                    user = c1.getString(0);
                    if (user.equals(globalvariabel.GetUsername())) {
                        buffer.append("BUG NAME: " + c1.getString(1) + "\n\n");
                    }
                }
                showMessage("ALL FOUND BUGS", buffer.toString());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DeveloperPage.this,SendReport.class);
                startActivity(i);
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
