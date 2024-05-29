package com.example.krestnull3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.krestnull3.db.DBHelper;
import com.example.krestnull3.db.DBHelper2;
import com.example.krestnull3.db.Data;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameMainScreen extends AppCompatActivity  {
    private KrestandNollTable krestandNollTable;
    private Button MenuButton;
    private Button RestartButton;
    private Button HistoryButton;
    private TextView textView;
    String [] arrnames={"Player1 ,Player2"};
    private DBHelper2 dbHelper;
    public String fintext = "";
    MediaPlayer mediaPlayer ;
    //int[] arr =new int[3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_screen);
        krestandNollTable= findViewById(R.id.krestandNollTable);


        MenuButton=findViewById(R.id.button4);
        RestartButton=findViewById(R.id.button3);
        textView=findViewById(R.id.textView5);
        if (krestandNollTable.game.curentplayer==1) {
            textView.setText("Krest is going");
        }else{
            textView.setText("Noll is going");
        }
        arrnames = getIntent().getStringArrayExtra("PlayersNames");
        dbHelper=new DBHelper2(this);
        HistoryButton=findViewById(R.id.button5);
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.music);
        mediaPlayer.start();



        HistoryButton.setVisibility(View.GONE);
        RestartButton.setVisibility(View.GONE);

        krestandNollTable.setUpGame(textView , arrnames ,MenuButton , RestartButton , HistoryButton);

    }






    public void RestartButton(View view){
        dbHelper.AddOne(new Data(arrnames[0] , arrnames[1] , textView.getText().toString()));
        LinkedList<Data> list = dbHelper.GetAll();
        
        String text = "";
        for (Data data : list) {
            text+=data.firstname+ " " + data.secondname+ " " + data.result + "\n";
        }
        Log.d("data" , text);

        krestandNollTable.resetTable();
        krestandNollTable.invalidate();
    }

    public void ReturntoMenu(View view){
        mediaPlayer.stop();
        Intent intent=new Intent(this  , MainActivity.class);
        startActivity(intent);
    }

    public void toHistory(View view){
        mediaPlayer.stop();
        dbHelper.AddOne(new Data(arrnames[0] , arrnames[1] , textView.getText().toString()));

        Intent intent =  new Intent(this , DataHistory.class);
        LinkedList<Data> list = dbHelper.GetAll();
        String text = "";
        for (Data data : list) {
            text+=data.firstname+ " " + data.secondname+ " " + data.result + " " + "\n";
        }
        //Log.d("data" , text);



        intent.putExtra("DataHistory" ,text);
        intent.putExtra("arrname" , arrnames);
        startActivity(intent);

    }


}
/*
public ArrayList<Integer> OXpar = new ArrayList<>();
    public ArrayList<Integer> OYpar = new ArrayList<>();
    public ArrayList<Integer> XXpar = new ArrayList<>();
    public ArrayList<Integer> XYpar = new ArrayList<>();
    public int kolx = 0;
    public int kolo = 0;
 */



