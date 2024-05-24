package com.example.krestnull3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.krestnull3.db.Data;
import com.example.krestnull3.recycler.Data_RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.LinkedList;

public class DataHistory extends AppCompatActivity {
    String datehistory = "";
    String[] arrnames;

    Button btnBack;
    private LinkedList<Data> dataLinkedList = new LinkedList<>();
    private ArrayList<Data> dataArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_history);

        btnBack=findViewById(R.id.button6);
        String strdata = getIntent().getStringExtra("DataHistory");
        arrnames = getIntent().getStringArrayExtra("arrname");
        //Log.d("data" , strdata);
        dataLinkedList = new LinkedList<>();
        str_to_data(strdata);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        Data_RecyclerViewAdapter adapter = new Data_RecyclerViewAdapter(this , dataArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






    }


    public void Back(View view){
        Intent intent = new Intent(this , GameMainScreen.class);
        intent.putExtra("PlayersNames" , arrnames);
        startActivity(intent);
    }
    public void str_to_data(String str){
        String[] strarrs = str.split("\n");
        for (int i = 0;i < strarrs.length;i++){
            String[] strdataarr = strarrs[i].split(" ");
            Data dataTemp = new Data(strdataarr[0] , strdataarr[1] , strdataarr[2] + " " + "win");
            Log.d("data" , dataTemp.firstname + " " + dataTemp.secondname + " " + dataTemp.result);
            dataLinkedList.add(dataTemp);
            dataArrayList.add(dataTemp);
        }
    }

}