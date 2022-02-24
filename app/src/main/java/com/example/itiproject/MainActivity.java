package com.example.itiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.itiproject.database.entityNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fat;
    CustomAdapter adapter;
    RecyclerView recyclerView;
    Intent outIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fat=findViewById(R.id.fat);
        fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               outIntent=new Intent(MainActivity.this,SecondActivity.class);
               startActivity(outIntent);
            }
        });
        recyclerView=findViewById(R.id.rv_todo);

        //creat object from view model
        ViewModledata viewModel;
        viewModel=new ViewModelProvider(MainActivity.this).get(ViewModledata.class);

        adapter=new CustomAdapter(new CustomAdapter.differCallback() ,viewModel);
        recyclerView.setAdapter(adapter);

                //the adapter observe for method getAllNote because this live data
                viewModel.getAllNote().observe(this,list->{
                    adapter.submitList(list);
                });




    }
}
