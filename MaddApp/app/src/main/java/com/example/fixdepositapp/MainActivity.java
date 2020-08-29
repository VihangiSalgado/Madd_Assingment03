package com.example.fixdepositapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DbHelper myDb;
    EditText editAmount, editRate, editTime, editInterest, editTotal, amount2;
    Button save, history, start, cal, clear;
    private double val1, val2, val3, val4, val5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DbHelper(this);

        /*Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); */

        editAmount = (EditText) findViewById(R.id.Amount1);
        editRate = (EditText) findViewById(R.id.Rate1);
        editTime = (EditText) findViewById(R.id.Time1);
        editInterest = (EditText) findViewById(R.id.TotInt);
        editTotal = (EditText) findViewById(R.id.Total);
        save = (Button) findViewById(R.id.SaveB);
        history = (Button) findViewById(R.id.button7);
        cal = (Button) findViewById(R.id.BC);
        clear = (Button) findViewById(R.id.clear);
        amount2 = (EditText) findViewById(R.id.Amount2);
        AddData();
        viewAll();

       /* history = (Button) findViewById(R.id.button7);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        start = (Button) findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAc();
            }
        }); */

       cal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               double interest;
               double totalAmt;
               interest = CalculateInterest();
               totalAmt = TotalAmount();
               //TotalAmount();
              //amount2.setText(String.valueOf(val1));
               amount2.setText(String.valueOf(Double.parseDouble(editAmount.getText().toString())));
               editInterest.setText(String.valueOf(interest));
               editTotal.setText(String.valueOf(totalAmt));
               //editAmount.setText(String.valueOf(val2));
           }
       });

       clear.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               val1 = Double.NaN;
               val2 = Double.NaN;
               val3 = Double.NaN;
               val4 = Double.NaN;
               val5 = Double.NaN;
               editAmount.setText(null);
               editRate.setText(null);
               editInterest.setText(null);
               editTime.setText(null);
               editTotal.setText(null);
               amount2.setText(null);
           }
       });

    }

   /* public void openActivity2(){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

   /* public void openAc(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    } */

   private double CalculateInterest(){
       val1 = Double.parseDouble(editAmount.getText().toString());
       val2 = Double.parseDouble(editRate.getText().toString());
       val3 = Double.parseDouble(editTime.getText().toString());

       val4 = (val1/100.00) * val2 * val3;
       return val4;

       //amount2.setText(String.valueOf(val1));
       //editInterest.setText(String.valueOf(val4));
       //TotalAmount();

   }

   private double TotalAmount(){
       val5 = val1 + val4;
       return val5;
       //editTotal.setText(String.valueOf(val5));
   }

    public void AddData(){
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       boolean isInserted =  myDb.insertData(editAmount.getText().toString(),
                                editRate.getText().toString(),
                                editTime.getText().toString(),
                                editInterest.getText().toString(),
                                editTotal.getText().toString());
                       if(isInserted = true)
                           Toast.makeText(MainActivity.this,"Saved", Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(MainActivity.this,"Something Went Wrong", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll(){
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0){
                    //message
                    showMessage("Error", "Nothing to Show");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                       buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Amount: " + res.getString(1) + "\n");
                    buffer.append("Rate: " + res.getString(2) + "\n");
                    buffer.append("Time: " + res.getString(3) + "\n");
                    buffer.append("Interest: " + res.getString(4) + "\n");
                    buffer.append("Total: " + res.getString(5) + "\n\n");
                }

                //show all data
                showMessage("History", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}