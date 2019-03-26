package com.example.tp2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.tp2.R;

import java.util.RandomAccess;

import static android.content.ContentValues.TAG;


public class Allumettes extends View {
    private Drawable allumette;


    private int NB_LIGNES = 2;
    private int PADDING = 25;

    private float RATIO_MIN = 0.05f;
    private float RATIO_MAX = 0.15f;

    private float hauteurAllumette;
    private float largeurAllumette;

    private int maxAllumettes = 21;
    private int selectedAllumettes;
    private int currentAllumettes;


    public Allumettes(Context c, AttributeSet attrs) {
        super(c, attrs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            allumette = c.getDrawable(R.drawable.allumettes);
        } else {
            allumette = c.getResources().getDrawable(R.drawable.allumettes);
        }
        currentAllumettes = maxAllumettes;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculTailleAllumette();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint MainBorder = new Paint();
        MainBorder.setAntiAlias(true);
        MainBorder.setStyle(Paint.Style.STROKE);
        MainBorder.setColor(Color.DKGRAY);
        MainBorder.setStrokeWidth(PADDING/2);

        Paint selectedBorder = new Paint();
        selectedBorder.setStyle(Paint.Style.STROKE);
        selectedBorder.setStrokeWidth(10);
        selectedBorder.setColor(Color.GREEN);

        DashPathEffect effet = new DashPathEffect(new float[]{30, 10}, 0);
        Paint deletedBorder = new Paint(MainBorder);
        deletedBorder.setPathEffect(effet);


        for(int i = 0; i< maxAllumettes;i++) {
            int lx, ly, dx, dy;


            if(i < maxAllumettes / NB_LIGNES) {
                lx = PADDING + ((i*((int)largeurAllumette*2)));
                ly = PADDING;
            } else {
                 lx = PADDING + ((i-(maxAllumettes/NB_LIGNES))*((int)largeurAllumette*2));
                 ly = ((int)hauteurAllumette) + (PADDING*2);
            }
            dx = lx + ((int)largeurAllumette);
            dy = ly + ((int)hauteurAllumette);
            if(i < currentAllumettes) {
                allumette.setBounds(lx, ly, dx, dy);
                allumette.draw(canvas);
            } else {
                canvas.drawRect(lx, ly, dx, dy, deletedBorder);
            }

            if(selectedAllumettes!=0) {
                if (i >= currentAllumettes - selectedAllumettes && i < currentAllumettes) {
                    canvas.drawRect(lx, ly, dx, dy, selectedBorder);
                }
            }

        }

        canvas.drawRect(10,10, getMeasuredWidth() - 10, getMeasuredHeight() - 10,   MainBorder);

    }

    private void calculTailleAllumette(){
       hauteurAllumette = (getMeasuredHeight() - 25*3) / 2;
       largeurAllumette = (getMeasuredWidth() - 25*2) / (((maxAllumettes/NB_LIGNES)+1)*2);

       if(largeurAllumette/hauteurAllumette < RATIO_MIN){
           hauteurAllumette = largeurAllumette / RATIO_MIN;
       }
       else if(largeurAllumette/hauteurAllumette > RATIO_MAX) {
           hauteurAllumette = largeurAllumette / RATIO_MAX;
       }
    }

    public int getSelectedAllumettes() {
        return selectedAllumettes;
    }

    public void setSelectedAllumettes(int selectedAllumette) {
        this.selectedAllumettes = selectedAllumette;
        invalidate();
    }

    public int getCurrentAllumettes() {
        return currentAllumettes;
    }

    public void setCurrentAllumettes(int currentAllumettes) {
        this.currentAllumettes = currentAllumettes;
        invalidate();
    }
}
