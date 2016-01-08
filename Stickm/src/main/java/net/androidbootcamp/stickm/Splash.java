//*********************************************************************
// Splash.java 								Group 1
// CSIT 266									Project
// When the game first starts, this screen is displayed with a button 
// that tells the user to press anywhere on the screen. This class will
// take the user to the correct screen depending on if there is game 
// data for the game. 
//*********************************************************************

package net.androidbootcamp.stickm;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Splash extends ActionBarActivity {
	
	//public final static String PAR_KEY = "net.androidbootcamp.stickm.par";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Button start = (Button) findViewById(R.id.btnStart);
        Game.MakePlayers();
        

		
        start.setOnClickListener(new OnClickListener() {
		
		
			@Override
			public void onClick(View v) {
				        
				if (Game.getRoundCompleted() == true)
				{
					startActivity(new Intent(Splash.this, Results.class));
				}
				else
				{
					if (Game.getCharactersChosenValue() == true)
					{
						startActivity(new Intent(Splash.this, PlayerAttack.class));
					}
					else
					{
						// sets current player to 1 for the player select screen
						Game.setCurrentPlayer(1);
						startActivity(new Intent(Splash.this, PlayerSelect.class));
					}
				}
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /*
    public void bundlePlayerData(PlayerStats p1, PlayerStats p2,Class screen)
    {

    	PlayerStats[] players = new PlayerStats[] {p1, p2};
		
		Intent pIntent = new Intent(Splash.this, screen);
		Bundle pBundle = new Bundle();

		pBundle.putParcelable(PAR_KEY, player1);
		pIntent.putExtras(pBundle);
		
		startActivity(pIntent);
    }
    */
}
