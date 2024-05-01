package com.example.krestnull3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameMainScreen extends AppCompatActivity {
    private KrestandNollTable krestandNollTable;
    private Button MenuButton;
    private Button RestartButton;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_screen);
        krestandNollTable= findViewById(R.id.krestandNollTable);
        MenuButton=findViewById(R.id.button4);
        RestartButton=findViewById(R.id.button3);
        textView=findViewById(R.id.textView5);
        textView.setText("Krest is going");

        RestartButton.setVisibility(View.GONE);
        String[] arrnames = getIntent().getStringArrayExtra("PlayersNames");
        krestandNollTable.setUpGame(textView , arrnames ,MenuButton , RestartButton);
    }

    public void ReturntoMenu(View view){
        Intent intent=new Intent(this  , MainActivity.class);
        startActivity(intent);
    }
    public void RestartButton(View view){
        krestandNollTable.resetTable();
        krestandNollTable.invalidate();
    }
}