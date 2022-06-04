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

public class TesterSettings extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;
    String u;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester_settings);

        e1=(EditText)findViewById(R.id.addtesterid1);
        e2=(EditText)findViewById(R.id.addprojectid1);
        b1=(Button)findViewById(R.id.registeredtestersv1);
        b2=(Button)findViewById(R.id.addtester);
        db = openOrCreateDatabase("Track", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Project_testers (id VARCHAR,projecttitle VARCHAR);");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c1=db.rawQuery("SELECT * FROM  Regtester",null);
                StringBuffer buffer=new StringBuffer();
                while (c1.moveToNext())
                {
                    buffer.append("ID: "+c1.getString(0)+"\n");
                    buffer.append("Mobile no: "+c1.getString(2)+"\n\n");
                }
                showMessage("ALL TESTERS DETAILS",buffer.toString());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u=e1.getText().toString().trim();
                Cursor c=db.rawQuery("select * from Regtester where id='"+u+"'", null);
                Cursor c2=db.rawQuery("select * from Project_testers where id='"+u+"'", null);
                if(c2.moveToFirst())
                {
                    Toast.makeText(TesterSettings.this,"Project Already Assigned",Toast.LENGTH_SHORT).show();
                }
                if(c.moveToFirst())
                {
                    db.execSQL("INSERT INTO Project_testers (id,projecttitle) VALUES('"
                            +  u + "','"+e2.getText().toString()+ "');");
                    clear();
                    Toast.makeText(TesterSettings.this,"Project Assigned",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(TesterSettings.this,"NO SUCH CREDIANTIALS",Toast.LENGTH_SHORT).show();
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
