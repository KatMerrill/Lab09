package com.example.lab09;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite sprite1, sprite2, sprite3;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sprite1 = new Sprite();
        sprite1.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sprite2));
        sprite2 = new Sprite(100,500,350,750);
        sprite2.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sprite3));
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sprite1.update(canvas);
        sprite1.draw(canvas);

        sprite2.update(canvas);
        sprite2.draw(canvas);

        // collision checks
        int dir = sprite1.intersects_horiz(sprite2);
        if(dir != 0) {
            sprite1.setdX(Math.abs(sprite1.getdX()) * dir);
        }
        dir = sprite2.intersects_horiz(sprite1);
        if(dir != 0) {
            sprite2.setdX(Math.abs(sprite2.getdX()) * dir);
        }
        dir = sprite1.intersects_vert(sprite2);
        if(dir != 0) {
            sprite1.setdY(Math.abs(sprite1.getdY()) * dir);
        }
        dir = sprite2.intersects_vert(sprite1);
        if(dir != 0) {
            sprite2.setdY(Math.abs(sprite2.getdY()) * dir);
        }

        invalidate();
    }
}
