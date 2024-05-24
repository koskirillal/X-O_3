package com.example.krestnull3;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Logic {
    public int curentplayer =1;
    public ArrayList<Integer> OXpar = new ArrayList<>();
    public ArrayList<Integer> OYpar = new ArrayList<>();
    public ArrayList<Integer> XXpar = new ArrayList<>();
    public ArrayList<Integer> XYpar = new ArrayList<>();
    public int kolx = 0;
    public int kolo = 0;



    private int[][] maintable;
    public String[] arrnames = {"Player1" , "Player2"};
    private TextView textwinner;
    private Button menuButton;
    public Button restartButton;
    public Button historyButton;
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
                XXpar.add(row - 1);
                XYpar.add(column - 1);
                kolx++;
                if (kolx == 4){
                    kolx--;
                    maintable[XXpar.get(0)][XYpar.get(0)]=0;
                    XXpar.remove(0);
                    XYpar.remove(0);

                }

                textwinner.setText("Noll is going");
            }else{
                OXpar.add(row -1);
                OYpar.add(column-1);
                kolo++;
                textwinner.setText("Krest is going");
                if (kolo == 4){
                    kolo--;
                    maintable[OXpar.get(0)][OYpar.get(0)]=0;
                    OXpar.remove(0);
                    OYpar.remove(0);
                }
            }
            return true;
        }else{
            return false;
        }
    }
    public void setNickNames(String[] nickNames){

        this.arrnames=nickNames;
        if (arrnames[0].equals("")){
            arrnames[0] = "Player1";
            arrnames[1]="Player2";
        }
    }

    public void reset(){
        for (int i =0;i < 3;i++){
            for(int j =0;j < 3;j++){
                maintable[i][j]=0;
            }
        }
        maintable[1][1]=0;
        OYpar.clear();
        OXpar.clear();
        XXpar.clear();
        XYpar.clear();
        kolx=0;
        kolo=0;
        historyButton.setVisibility(View.GONE);
        restartButton.setVisibility(View.GONE);
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
            historyButton.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.VISIBLE);
            textwinner.setText(arrnames[curentplayer-1] + " win");
            return true;
        }else if (isPat()){
            historyButton.setVisibility(View.VISIBLE);
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
