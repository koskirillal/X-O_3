package com.example.krestnull3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class KrestandNollTable extends View {
    private TextView textwinner;
    private Button menuButton;
    private Button restartButton;

    private final int Tablecolor;
    private final int WinLineColor;
    private final int XColor;
    private final int OColor;
    private boolean isgameend = false;
    private Logic game;
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
        for (int i = 1; i < 3; i++) {
            canvas.drawLine(cellsize * i, 0, cellsize * i, canvas.getWidth(), paint);
        }


        for (int j = 1; j < 3; j++) {
            canvas.drawLine(0, cellsize * j, canvas.getWidth(), cellsize * j, paint);
        }
    }


    public void DrawMarks(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getMaintable()[i][j] != 0) {
                    if (game.getMaintable()[i][j] == 1) {
                        drawX(canvas, i, j);
                    } else {
                        drawO(canvas, i, j);
                    }
                }
            }
        }
    }

    private void drawX(Canvas canvas, int row, int column) {
        paint.setColor(XColor);
        canvas.drawLine((column + 1) * cellsize, row * cellsize, column * cellsize, (row + 1) * cellsize, paint);
        canvas.drawLine((column) * cellsize, row * cellsize, (column + 1) * cellsize, (row + 1) * cellsize, paint);
    }

    private void drawO(Canvas canvas, int row, int column) {
        paint.setColor(OColor);
        canvas.drawOval(column * cellsize, row * cellsize, column * cellsize + cellsize, row * cellsize + cellsize, paint);

    }

    public void resetTable() {
        isgameend = false;
        game.reset();
    }

    public void setUpGame(TextView textwinner, String[] arrnames, Button menu, Button restart) {
        game.setMenuButton(menu);
        game.setTextwinner(textwinner);
        game.setRestartButton(restart);
        game.setNickNames(arrnames);
    }


}
