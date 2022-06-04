package com.dheeraj.android.ecilproject;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TesterPage extends AppCompatActivity {

    TextView t1,t2;
    Button b;
    String user;
    String projecttitle=null;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester_page);
        t1=(TextView)findViewById(R.id.testeridview);
        t2=(TextView)findViewById(R.id.testerprojectview);
        b=(Button)findViewById(R.id.addbuggg);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        t1.setText(globalvariabel.GetUsername());
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        user=globalvariabel.GetUsername();
        Cursor c1=db.rawQuery("select * from Project_testers where id='"+user+"'", null);
        if(c1.moveToFirst())
        {
            projecttitle=c1.getString(1);
            t2.setText(projecttitle);
        }
        else
        {
            t2.setText(projecttitle);
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TesterPage.this,AddBug.class);
                startActivity(i);
            }
        });
    }
}
