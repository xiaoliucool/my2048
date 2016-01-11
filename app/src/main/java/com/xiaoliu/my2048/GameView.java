package com.xiaoliu.my2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/11.
 */
public class GameView extends GridLayout {
    private Card[][] cardMap = new Card[4][4];
    private List<Point> points = new ArrayList<>();

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        this.setColumnCount(4);
        this.setBackgroundColor(0xffbbada0);
        this.setOnTouchListener(new OnTouchListener() {
            float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = motionEvent.getX() - startX;
                        offsetY = motionEvent.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) { // horizontal move
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else { // portrait move
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth = (Math.min(w, h) - 10) / 4;
        addCards(cardWidth, cardWidth);
        startGame();
    }

    private void startGame() {
        clearNums();
        addRandomNums();
        addRandomNums();
    }

    private void clearNums() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                cardMap[x][y].setNum(0);
            }
        }
    }

    private void addRandomNums() {
        points.clear();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (cardMap[x][y].getNum() <= 0) {
                    points.add(new Point(x, y));
                }
            }
        }
        Point point = points.remove((int) (Math.random() * points.size()));
        cardMap[point.x][point.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }

    private void addCards(int width, int height) {
        Card c = null;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                c = new Card(getContext());
                c.setNum(0);
                cardMap[x][y] = c;
                addView(c, width, height);
            }
        }
    }

    private void swipeDown() {
        System.out.println("down");
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int yy = x-1;yy>= 0;yy--){
                    if (cardMap[yy][y].getNum()>0){
                        if (cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[yy][y].getNum());
                            cardMap[yy][y].setNum(0);
                        }else if (cardMap[x][y].equals(cardMap[yy][y])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[yy][y].setNum(0);
                            break;
                        }else {
                            break;
                        }
                    }
                }
            }
        }
        if (isOver()){
            showOverDialog();
        }else{
            addRandomNums();
        }
    }

    private void swipeUp() {
        System.out.println("up");
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int yy = x+1;yy< 4;yy++){
                    if (cardMap[yy][y].getNum()>0){
                        if (cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[yy][y].getNum());
                            cardMap[yy][y].setNum(0);
                        }else if (cardMap[x][y].equals(cardMap[yy][y])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[yy][y].setNum(0);
                            break;
                        }else {
                            break;
                        }
                    }
                }
            }
        }
        if (isOver()){
            showOverDialog();
        }else{
            addRandomNums();
        }
    }

    private void swipeRight() {
        System.out.println("right");
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int yy = y-1;yy>= 0;yy--){
                    if (cardMap[x][yy].getNum()>0){
                        if (cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x][yy].getNum());
                            cardMap[x][yy].setNum(0);
                        }else if (cardMap[x][y].equals(cardMap[x][yy])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x][yy].setNum(0);
                            break;
                        }else {
                            break;
                        }
                    }
                }
            }
        }
        if (isOver()){
            showOverDialog();
        }else{
            addRandomNums();
        }
    }

    private void swipeLeft() {
        System.out.println("left");
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int yy = y+1;yy< 4;yy++){
                    if (cardMap[x][yy].getNum()>0){
                        if (cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x][yy].getNum());
                            cardMap[x][yy].setNum(0);
                        }else if (cardMap[x][y].equals(cardMap[x][yy])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x][yy].setNum(0);
                            break;
                        }else {
                            break;
                        }
                    }
                }
            }
        }
        if (isOver()){
            showOverDialog();
        }else{
            addRandomNums();
        }
    }
    public void showOverDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("游戏结束了");
        builder.setTitle("提示");
        builder.setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                startGame();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
    public boolean isOver(){
        if (points.size()==0){
            return true;
        }else
            return false;
    }
}
