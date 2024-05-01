package com.example.krestnull3;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Logic {
    public int curentplayer =1;
    private int[][] maintable;
    private String[] arrnames = {"Player1" , "Player2"};
    private TextView textwinner;
    private Button menuButton;
    private Button restartButton;
    public Logic(){
        maintable=new int[3][3];
        for (int i =0;i < 3;i++){
            for(int j =0;j < 3;j++){
                maintable[i][j]=0;
            }
        }
    }


    public int[][] getMaintable() {
        return maintable;
    }
    public boolean upDateMainTable(int row , int column){
        if(maintable[row -1][column - 1] == 0){
            maintable[row -1][column - 1] = curentplayer;
            if (curentplayer==1){
                textwinner.setText("Noll is going");
            }else{
                textwinner.setText("Krest is going");
            }
            return true;
        }else{
            return false;
        }
    }
    public void setNickNames(String[] nickNames){
        this.arrnames=nickNames;
    }

    public void reset(){
        for (int i =0;i < 3;i++){
            for(int j =0;j < 3;j++){
                maintable[i][j]=0;
            }
        }
        maintable[1][1]=0;
    }
    public int getCurentplayer() {
        return curentplayer;
    }

    public void setCurentplayer(int curentplayer) {
        this.curentplayer = curentplayer;
    }
    public void setRestartButton(Button restartButton) {
        this.restartButton = restartButton;
    }

    public void setTextwinner(TextView textwinner) {
        this.textwinner = textwinner;
    }
    public  boolean isPat(){
        int kol = 0;
        for (int i =0;i < 3;i++){
            for (int j = 0;j < 3;j++){
                if (maintable[i][j] != 0){
                    kol++;
                }
            }
        }
        return (kol==9);
    }

    public boolean isGameOver(){
        boolean isgameend = false;
        for (int i =0;i < 3;i++){
            if (maintable[i][0] == maintable[i][1] && maintable[i][0] == maintable[i][2] && maintable[i][0]!= 0){
                isgameend=true;
            }
            if (maintable[0][i] == maintable[1][i] && maintable[0][i] == maintable[2][i] && maintable[0][i] != 0){
                isgameend=true;
            }

        }
        if (maintable[1][1] == maintable[0][0] && maintable[1][1] == maintable[2][2] && maintable[1][1] != 0){
            isgameend=true;
        }
        if (maintable[0][2] == maintable[1][1] && maintable[2][0] == maintable[1][1] && maintable[1][1] != 0){
            isgameend=true;
        }
        if (isgameend==true){
            restartButton.setVisibility(View.VISIBLE);
            textwinner.setText(arrnames[curentplayer-1] + " win");
            return true;
        }else if (isPat()){
            restartButton.setVisibility(View.VISIBLE);
            textwinner.setText("Pat");
            return true;
        }else{
            return false;
        }
    }


    public void setMenuButton(Button menuButton) {
        this.menuButton = menuButton;
    }
}
