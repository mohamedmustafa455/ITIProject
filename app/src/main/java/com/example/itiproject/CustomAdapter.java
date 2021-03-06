package com.example.itiproject;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itiproject.database.entityNote;
import com.example.itiproject.databinding.ItemTodoBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomAdapter extends ListAdapter<entityNote,CustomAdapter.ViewHolder> {

    ViewModledata viewModel;

    public CustomAdapter(@NonNull DiffUtil.ItemCallback<entityNote> differ,ViewModledata model) {
        super(differ);
        viewModel=model;

    }
        // DiffUtil class
    static class differCallback extends DiffUtil.ItemCallback<entityNote>{

        @Override
        public boolean areItemsTheSame(@NonNull entityNote oldItem, @NonNull entityNote newItem) {
            return oldItem==newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull entityNote oldItem, @NonNull entityNote newItem) {
            return oldItem.getId()==newItem.getId();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTodoBinding view=ItemTodoBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //retrn current item
        entityNote note = getItem(position);

      holder.binding.tvTitleName.setText(note.title);
      holder.binding.tvDate.setText(note.date);


      holder.binding.btnDelet.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              entityNote noteCurrentItem=note;
              viewModel.deletNote(noteCurrentItem);
          }
      });

      holder.binding.tvTitleName.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              //to hold current item
              entityNote noteCurrentItem=getItem(position);

              //send the data that will be modified
              Intent intent=new Intent(v.getContext(),SecondActivity.class);
              intent.putExtra("id",noteCurrentItem.getId());
              intent.putExtra("title",noteCurrentItem.getTitle());
              intent.putExtra("body",noteCurrentItem.getBody());
              v.getContext().startActivity(intent);

          }
              });
    }

    // @Override
//    public int getItemCount() {
//        return 20;
//    }
     //ViewHolder class with view binding
    public class ViewHolder extends RecyclerView.ViewHolder{
       ItemTodoBinding binding;
        public ViewHolder(@NonNull ItemTodoBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
