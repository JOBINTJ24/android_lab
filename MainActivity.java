package com.example.databasecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For Edittext
        EditText txt1=(EditText) findViewById(R.id.txt1);
        EditText txt2=(EditText) findViewById(R.id.txt2);
        EditText txt3=(EditText) findViewById(R.id.txt3);

        //For Button
        Button btn1=(Button) findViewById(R.id.button1);
        Button btn2=(Button) findViewById(R.id.button2);
        Button btn3=(Button) findViewById(R.id.button3);
        Button btn4=(Button) findViewById(R.id.button4);

        //For Database
        DbClass DB=new DbClass(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txt1.getText().toString();
                String dob=txt2.getText().toString();
                String contact=txt3.getText().toString();

                Boolean b=DB.insertUserData(name,contact,dob);
                if(b==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Unable to insert", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txt1.getText().toString();
                String dob=txt2.getText().toString();
                String contact=txt3.getText().toString();

                Boolean b=DB.updateUserData(name,contact,dob);
                if(b==true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Unable to Update", Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txt1.getText().toString();
                Boolean b=DB.DeleteUserData(name);
                if(b==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Unable to Delete", Toast.LENGTH_SHORT).show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res=DB.getdata();
               if(res.getCount()==0){
                   Toast.makeText(MainActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                   return;
               }
               StringBuffer buffer=new StringBuffer();
               while(res.moveToNext()){
                   buffer.append("Name:"+res.getString(0)+"\n");
                   buffer.append("Contact:"+res.getString(1)+"\n");
                   buffer.append("Date of Birth:"+res.getString(2)+"\n");
               }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
               builder.setCancelable(true);
               builder.setTitle("User Details");
               builder.setMessage(buffer.toString());
               builder.show();
            }
        });

    }
}