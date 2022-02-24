package com.example.itiproject;

import android.annotation.SuppressLint;
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
    //List<String> TitleList;
    public CustomAdapter(@NonNull DiffUtil.ItemCallback<entityNote> differ,ViewModledata model) {
        super(differ);
        viewModel=model;
//        this.TitleList = TitleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTodoBinding view=ItemTodoBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(view);
    }

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

    //differ=AsyncListDiffer(this,differCallback);

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//the problem on click not retrev data
       //retrn current item
      holder.binding.tvTitleName.setText(getItem(position).title);
      holder.binding.tvDate.setText(getItem(position).date);
      holder.binding.tvTime.setText(getItem(position).time);

//      Thread thread1=new Thread(){
//          public void run(){
//          }
//      };

      holder.binding.btnDelet.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              entityNote noteCurrentItem=getItem(position);

              viewModel.deletNote(noteCurrentItem);

              //update the recycller view againe


          }
      });

//      Thread thread2=new Thread(){
//          public void run(){
//          }
//      };
      holder.binding.tvTitleName.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              entityNote noteCurrentItem=getItem(position);

              viewModel.updataNote(noteCurrentItem);

          }
              });
    }
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private String getCurrentDateAsString(){
//        LocalDateTime date = LocalDateTime.now();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        return dtf.format(date);
//    }


    // @Override
//    public int getItemCount() {
//        return 20;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       ItemTodoBinding binding;
        public ViewHolder(@NonNull ItemTodoBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
