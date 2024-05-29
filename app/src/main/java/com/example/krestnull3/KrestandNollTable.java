package com.example.krestnull3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class KrestandNollTable extends View {
    private TextView textwinner;
    private Button menuButton;
    private Button restartButton;



    private final int Tablecolor;
    private final int WinLineColor;
    private final int XColor;
    private final int OColor;
    private boolean isgameend = false;
    public Logic game;
    private int cellsize = getWidth() / 3;
    private final Paint paint = new Paint();

    public KrestandNollTable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new Logic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.KrestandNollTable,
                0, 0);
        try {
            Tablecolor = a.getInteger(R.styleable.KrestandNollTable_Tablecolor, 0);
            XColor = a.getInteger(R.styleable.KrestandNollTable_XColor, 0);
            OColor = a.getInteger(R.styleable.KrestandNollTable_OColor, 0);
            WinLineColor = a.getInteger(R.styleable.KrestandNollTable_Tablecolor, 0);
        } finally {
            a.recycle();
        }

    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellsize = dimension / 3;

        setMeasuredDimension(dimension, dimension);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int activ = event.getAction();

        if (activ == MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y / cellsize);
            int column = (int) Math.ceil(x / cellsize);

            if (isgameend == false) {
                if (game.upDateMainTable(row, column)) {
                    invalidate();
                    if (game.isGameOver() == true) {
                        isgameend = true;
                        invalidate();
                    }
                    if (game.getCurentplayer() == 1) {
                        game.setCurentplayer(2);
                    } else {
                        game.setCurentplayer(1);
                    }
                }
            }
            invalidate();
            return true;
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawGameTable(canvas);
        DrawMarks(canvas);

    }

    private void drawGameTable(Canvas canvas) {
        paint.setColor(Tablecolor);
        paint.setStrokeWidth(16);

        canvas.drawLine((float) (cellsize * 1), (float) (cellsize*0.1), cellsize * 1, (float) (canvas.getWidth() - cellsize*0.1), paint);
        canvas.drawLine(cellsize * 2, (float) (cellsize*0.1), cellsize * 2,(float) (canvas.getWidth() - cellsize*0.1), paint);




        canvas.drawLine((float) (cellsize *0.1),  (cellsize * 1), (float) (canvas.getWidth()-cellsize*0.1), cellsize * 1, paint);
        canvas.drawLine((float) (cellsize * 0.1), cellsize * 2, (float) (canvas.getWidth() - cellsize*0.1), cellsize * 2, paint);

    }


    public void DrawMarks(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getMaintable()[i][j] != 0) {
                    if (game.getMaintable()[i][j] == 1) {
                        if (game.kolx < 3) {
                            drawX(canvas, i, j);
                        }else if (game.kolx==3){
                            drawX(canvas,i,j);
                            drawlastX(canvas,game.XXpar.get(0) , game.XYpar.get(0));
                        }
                    } else {
                        drawO(canvas, i, j);
                        if (game.kolo<3){
                            drawO(canvas, i, j);
                        }else if(game.kolo==3){
                            drawO(canvas, i, j);
                            drawlastO(canvas,game.OXpar.get(0) , game.OYpar.get(0));
                        }
                    }
                }
            }
        }
    }

    private void drawX(Canvas canvas, int row, int column) {
        paint.setColor(XColor);
        canvas.drawLine((float) ((column + 1)*cellsize - cellsize*0.1),
                (float) (row * cellsize  + cellsize * 0.1),
                (float) (column * cellsize + cellsize *0.1),
                (float) ((row + 1) * cellsize - cellsize*0.1), paint);
        canvas.drawLine((float) ((column)*cellsize + 0.1*cellsize),
                (float) (row * cellsize + 0.1 * cellsize),
                (float) ((column + 1) * cellsize - cellsize* 0.1),
                (float) ((row + 1) * cellsize - cellsize*0.1), paint);
    }
    private void drawlastX(Canvas canvas, int row, int column) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            paint.setColor(Color.pack(Color.parseColor("#770202")));
        }
        canvas.drawLine((float) ((column + 1)*cellsize - cellsize*0.1),
                (float) (row * cellsize  + cellsize * 0.1),
                (float) (column * cellsize + cellsize *0.1),
                (float) ((row + 1) * cellsize - cellsize*0.1), paint);
        canvas.drawLine((float) ((column)*cellsize + 0.1*cellsize),
                (float) (row * cellsize + 0.1 * cellsize),
                (float) ((column + 1) * cellsize - cellsize* 0.1),
                (float) ((row + 1) * cellsize - cellsize*0.1), paint);
    }

    private void drawO(Canvas canvas, int row, int column) {
        paint.setColor(OColor);
        canvas.drawOval((float) (column * cellsize + cellsize * 0.1), (float) (row * cellsize + cellsize * 0.1),
                (float) (column * cellsize + cellsize  - cellsize * 0.1), (float) (row * cellsize + cellsize - cellsize * 0.1), paint);

    }
    private void drawlastO(Canvas canvas, int row, int column) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            paint.setColor(Color.pack(Color.parseColor("#092791")));
        }
        canvas.drawOval((float) (column * cellsize + cellsize * 0.1), (float) (row * cellsize + cellsize * 0.1),
                (float) (column * cellsize + cellsize  - cellsize * 0.1), (float) (row * cellsize + cellsize - cellsize * 0.1), paint);

    }

    public void resetTable() {
        isgameend = false;





        game.reset();

    }

    public void setUpGame(TextView textwinner, String[] arrnames, Button menu, Button restart , Button HistoryButton) {
        game.setMenuButton(menu);
        game.setTextwinner(textwinner);
        game.setRestartButton(restart);
        game.setNickNames(arrnames);
        game.historyButton=HistoryButton;
    }



}
