package harsh.myappcompany.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
    String winner = "";
    boolean gameActive=true;
    int activePlayer=0; // 0 : yellowPlayer , 1 : redPlayer
    int[] gameState={2,2,2,2,2,2,2,2,2}; //2 :  means empty i.e. no player is there , 1 : means player red is there , 0 :  means player yellow is there
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropInRed(View view)
    {
        ImageView counter=(ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive)
        {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0)
            {
                MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.booms1);
                mediaPlayer.start();
                counter.setImageResource(R.drawable.yellowtoken);
                activePlayer = 1;
            }
            else
            {
                MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.booms2);
                mediaPlayer.start();
                counter.setImageResource(R.drawable.redtoken);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(360).setDuration(300);
            for (int[] winningPosition : winningPositions)
            {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                {
                    //SomeOne has one
                    gameActive=false;
                    if (activePlayer == 1)
                    {
                        winner = "Yellow"; //because we change the player for next move so the player won will be opposite
                    } else
                    {
                        winner = "Red";
                    }
                    Button button1=(Button) findViewById(R.id.button1);
                    TextView textView1=(TextView) findViewById(R.id.textView1);
                    textView1.setText(winner +" has won");
                    button1.setVisibility(View.VISIBLE);
                    textView1.setVisibility(View.VISIBLE);
                }
                if(gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2 && gameState[3] != 2 && gameState[4] != 2 && gameState[5] != 2 && gameState[6] != 2 && gameState[7] != 2 && gameState[8] != 2
                        && gameState[winningPosition[0]] != gameState[winningPosition[1]] && gameState[winningPosition[1]] != gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                {
                    winner="Nobody";
                    Button button1=(Button) findViewById(R.id.button1);
                    TextView textView1=(TextView) findViewById(R.id.textView1);
                    textView1.setText(winner +" has won");
                    button1.setVisibility(View.VISIBLE);
                    textView1.setVisibility(View.VISIBLE);
                }
            }
        }
        else
        {
            if(gameActive)
            {
                MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.groans6);
                mediaPlayer.start();
                Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, winner + " has won! No more move left", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void playAgain(View view)
    {
        Button button1=(Button) findViewById(R.id.button1);
        TextView textView1=(TextView) findViewById(R.id.textView1);
        androidx.gridlayout.widget.GridLayout gridLayout1 = findViewById(R.id.gridLayout1);
        button1.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);
        for(int i = 0; i < gridLayout1.getChildCount(); i++)
        {
            ImageView gridLayoutChild = (ImageView) gridLayout1.getChildAt(i);
            gridLayoutChild.setImageDrawable(null);
        }
        winner = "";
        gameActive=true;
        activePlayer=0; // 0 : yellowPlayer , 1 : redPlayer
/*        for(int i=0;i<gameState.length;i++) //because we cannot reinitialize array
        {
            gameState[i]=0;
        }*/
        Arrays.fill(gameState,2); //same what the above loop is doing
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.zangs1);
        mediaPlayer.start();
    }
}