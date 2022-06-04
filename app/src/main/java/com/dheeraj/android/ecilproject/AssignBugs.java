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

public class AssignBugs extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2,b0;
    String d;
    String t1,t2,p;
    String project_title;
    String project_title1;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_bugs);
        final GlobalClass globalvariabel = (GlobalClass) getApplicationContext();
        e1 = (EditText) findViewById(R.id.entersolver);
        e2 = (EditText) findViewById(R.id.enterbuggy);

        b1 = (Button) findViewById(R.id.viewbuggy);
        b2 = (Button) findViewById(R.id.assignbuggy);
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Project_assignbug (developerid VARCHAR,bugname VARCHAR);");
        Cursor c1 = db.rawQuery("select * from Project_manager where id='" + globalvariabel.GetUsername() + "'", null);
        if (c1.moveToFirst()) {
            project_title = c1.getString(1);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor c1 = db.rawQuery("SELECT * FROM  Project_bug", null);
                while (c1.moveToNext()) {
                    project_title1 = c1.getString(0);
                    if (project_title.equals(project_title1)) {
                        buffer.append("BUG NAME: " + c1.getString(1) + "\n\n");
                    }
                }
                showMessage("ALL FOUND BUGS", buffer.toString());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1 = e1.getText().toString();
                t2 = e2.getText().toString();




                    db.execSQL("INSERT INTO Project_assignbug (developerid,bugname) VALUES('"
                            + t1 + "','" + t2 + "');");
                Toast.makeText(AssignBugs.this, "bug assigned", Toast.LENGTH_SHORT).show();
                clear();


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
