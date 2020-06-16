package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView Score1;
    private TextView Score2;
    private Button reset;
    private boolean player1turn=true;
    private ImageButton[][] imageViews = new ImageButton[3][3];
    final int[][] matrix = {
            { -1, -1, -1 },
            { -1, -1, -1 },
            { -1, -1, -1 }
    };
    private int s1;
    private int s2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Score1=(TextView)findViewById(R.id.player1_score);
        Score2=(TextView)findViewById(R.id.player2_score);
        reset=(Button)findViewById(R.id.reset);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String bid = "b"+(i+1)+(j+1);
                imageViews[i][j] = findViewById(getResources().getIdentifier(bid,"id",getPackageName()));
                imageViews[i][j].setOnClickListener(this);

            }
        }

        Score1=(TextView)findViewById(R.id.player1_score);
        Score2=(TextView)findViewById(R.id.player2_score);
        reset=(Button)findViewById(R.id.reset);

        s1=0;
        s2=0;



    }



    @Override
    public void onClick(View v)
    {
        if(((ImageButton) v).getDrawable()!=null)
        {
            return;
        }

        if(player1turn)
        {
            ((ImageButton)v).setImageResource(R.drawable.ic_exposure_zero_black_24dp);
            player1turn=false;
        }
        else
        {
            ((ImageButton)v).setImageResource(R.drawable.xmark);
            player1turn=true;
        }

        if(SomeoneWins())
        {
            if(player1turn)
            {
                Toast.makeText(this,"Player 2 win",Toast.LENGTH_SHORT).show();
                s1+=1;
            }
            else
            {
                Toast.makeText(this,"Player 1 win",Toast.LENGTH_SHORT).show();
                s2+=1;
            }

            setScore();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    GameReset();
                }
            }, 5000);

        }
        if(gameOver())
        {
            Toast.makeText(this,"Game Over",Toast.LENGTH_SHORT).show();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    GameReset();
                }
            }, 5000);

        }
    }

    private boolean gameOver()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(imageViews[i][j].getDrawable()==null)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void setScore()
    {
    }

    private void GameReset()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                imageViews[i][j].setImageDrawable(null);
                imageViews[i][j].setImageMatrix(null);
            }
        }
    }


    boolean SomeoneWins()
    {
        for(int i=0;i<3;i++)
        {
            if(imageViews[i][0].getDrawable()!=null) {

                if (imageViews[i][0].getImageMatrix().equals(imageViews[i][1].getImageMatrix())) {

                    if (imageViews[i][0].getImageMatrix().equals(imageViews[i][2].getImageMatrix())) {
                        return true;
                    }
                }
            }
        }

        if(imageViews[0][0].getDrawable()!=null && imageViews[0][0].getImageMatrix().equals(imageViews[1][1].getImageMatrix()))
        {

            if(imageViews[0][0].getImageMatrix().equals(imageViews[2][2].getImageMatrix()))
            {
                return true;
            }
        }

        for(int i=0;i<3;i++)
        {
            if(imageViews[0][i].getDrawable()!=null && imageViews[0][i].getImageMatrix().equals(imageViews[1][i].getImageMatrix()))
            {

                if(imageViews[0][i].getImageMatrix().equals(imageViews[2][i].getImageMatrix()))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
