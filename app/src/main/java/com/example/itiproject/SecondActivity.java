package com.example.itiproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itiproject.database.entityNote;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SecondActivity extends AppCompatActivity {
EditText edtTitel,edtBody;
Button btnSave,btnClose;
Intent backIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edtTitel=findViewById(R.id.edt_headline);
        edtBody=findViewById(R.id.edt_content);
        btnSave=findViewById(R.id.btn_save);
        btnClose=findViewById(R.id.btn_cancel);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        ViewModledata viewModel;
        viewModel=new ViewModelProvider(this).get(ViewModledata.class) ;
        Thread thread = new Thread(){
            public void run(){
                entityNote note=new entityNote(edtTitel.getText().toString(),edtBody.getText().toString(),"22/2/2022","9:30" );
                viewModel.setNote(note);
            }
        };
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTitel.getText().toString()==""){
                    Toast.makeText(getApplicationContext(),"pleas add Title",Toast.LENGTH_LONG).show();


                }else{
                    thread.start();
                    backIntent=new Intent(SecondActivity.this,MainActivity.class);
                    startActivity(backIntent);

                }

            }
        });



    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getCurrentDateAsString(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(date);
    }
}