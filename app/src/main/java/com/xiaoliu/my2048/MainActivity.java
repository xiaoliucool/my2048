package com.xiaoliu.my2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //控件
    private TextView scoreTv;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void init(){
        scoreTv = (TextView) findViewById(R.id.score_tv);
        gameView = (GameView) findViewById(R.id.grid_layout);
        gameView.setOnScoreChangeListener(new Score() {
            @Override
            public void callback(int score) {
                scoreTv.setText(""+score);
            }
        });
    }
    public interface Score {
        void callback(int score);
    }
}
