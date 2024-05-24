package com.example.krestnull3.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krestnull3.R;
import com.example.krestnull3.db.Data;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Data_RecyclerViewAdapter extends RecyclerView.Adapter<Data_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Data> dataArrayList;

    public Data_RecyclerViewAdapter(Context context , ArrayList<Data> dataArrayList){
        this.context=context;
        this.dataArrayList=dataArrayList;
    }
    @NonNull
    @Override
    public Data_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row , parent , false);
        return new Data_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Data_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView_result.setText(dataArrayList.get(position).getResult());
        holder.textView_name_firstplayer.setText(dataArrayList.get(position).getFirstname());
        holder.textView_name_secondplayer.setText(dataArrayList.get(position).getSecondname());

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name_firstplayer;
        TextView textView_name_secondplayer;
        TextView textView_result;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name_firstplayer=itemView.findViewById(R.id.textView_name_firstplayer);
            textView_name_secondplayer=itemView.findViewById(R.id.textView_name_secondplayer);
            textView_result=itemView.findViewById(R.id.textView_result);
        }
    }
}
