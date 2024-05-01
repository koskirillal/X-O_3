package com.example.krestnull3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NickNamesSetUp extends AppCompatActivity {
    private EditText player1;
    private EditText player2;
    private String player1name;
    private String player2name;
    private String[] arrNames = new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_names_set_up);
        player1=findViewById(R.id.editTextText);
        player2=findViewById(R.id.editTextText2);
    }
    public void NameButtonsClicktoMainGameDisplay(View view){
        player1name=player1.getText().toString();
        player2name=player2.getText().toString();
        arrNames[0]=player1name;
        arrNames[1]=player2name;
        Intent intent = new Intent(this , GameMainScreen.class );
        intent.putExtra("PlayersNames" , arrNames);
        startActivity(intent);

    }


}