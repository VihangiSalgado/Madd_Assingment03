package com.example.fixdepositapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class History extends AppCompatActivity {

    DbHelper myDb;
    Button hB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        /*myDb = new DbHelper(this);
        hB = (Button) findViewById(R.id.buttonH);
        view();*/
    }

    /*public void view(){
        hB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0) {
                    //show message
                    showMessage("Error", "Nothing Found");
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Amount : " + res.getString(5) + "\n");
                }

                // show all data
                showMessage("Data", buffer.toString());

            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    } */
}