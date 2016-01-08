//*********************************************************************
// Results.java 								Group 1
// CSIT 266									Project
// This class is for the Results screen and shows how much damage
// each player took during the round. The user can decide to end the 
// game from there or continue the game until a winner is determined.
// The class will display any winner and the current round and will 
// place a red X over the loser of the game. 
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Results extends ActionBarActivity implements OnClickListener{

	private TextView txtP1Attk, txtP2Attk, txtLoserP1X, txtLoserP2X,
		txtP1Health, txtP2Health, txtRound, txtWinner; 
	private ImageView imgPlayer1, imgPlayer2;
	
	private FrameLayout confirm;
	
	private Button btnContinue, btnEndGame, btnConfirmYes, btnConfirmNo;
	
	private ProgressBar healthBarP1, healthBarP2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		// set everthing up the first time
		if (savedInstanceState == null)
		{
			setUpTextViews();
			setText();
			setUpButtons();
			setListeners();
			setUpImageView();
			setPlayerCharacters();
			setUpHealthBar();
		}
		
		displayHealth();
		
		// if winner, then hides continue button
		if (Game.isThereWinner())
		{
			displayWinner();			
		}
	}
	
	// displays a winner 
	private void displayWinner()
	{
		btnContinue.setVisibility(View.INVISIBLE);
		txtWinner.setText("Winner: " + Game.getWinnerString());

		switch (Game.getWinner())
		{
			case 0:
			{
				txtLoserP1X.setVisibility(View.VISIBLE);
				txtLoserP2X.setVisibility(View.VISIBLE);
				break;
			}
			
			case 1:
			{
				txtLoserP2X.setVisibility(View.VISIBLE);
				break;
			}
			
			case 2:
			{
				txtLoserP1X.setVisibility(View.VISIBLE);
				break;
			}
			
			default:
			{
				break;
			}
		}
	}
	
	// creates the health progress bars
	private void setUpHealthBar()
	{
		healthBarP1 = (ProgressBar)findViewById(R.id.progressBarPlayer1ResultsPage);
		healthBarP2 = (ProgressBar)findViewById(R.id.ProgressBarPlayer2ResultsPage);
	}
	
	// set the progress on the pregresss bar to the correct health
	private void displayHealth()
	{
		healthBarP1.setProgress(Game.player1.health);
		healthBarP2.setProgress(Game.player2.health);
	}
	
	private void setUpImageView()
	{
		imgPlayer1 = (ImageView)findViewById(R.id.imgPlayer1);
		imgPlayer2 = (ImageView)findViewById(R.id.imgPlayer2);
	}
	
	private void setPlayerCharacters()
	{
		imgPlayer2.setBackgroundResource(Game.player2.displayChara());
		imgPlayer1.setBackgroundResource(Game.player1.displayChara());
	}
	
	private void setUpTextViews()
	{
		txtP1Attk = (TextView)findViewById(R.id.TextViewPlayer1Attk);
		txtP1Health = (TextView)findViewById(R.id.TextViewPlayer1Health);
		txtLoserP1X = (TextView)findViewById(R.id.txtLoserPlayer1);

		txtP2Attk = (TextView)findViewById(R.id.TextViewPlayer2Attk);
		txtP2Health = (TextView)findViewById(R.id.textViewplayer2Health);
		txtLoserP2X = (TextView)findViewById(R.id.txtLoserPlayer2);

		
		txtRound = (TextView)findViewById(R.id.textViewRound);
		txtWinner = (TextView)findViewById(R.id.textViewRoundWinner);
		
		confirm = (FrameLayout)findViewById(R.id.confirmFrame);


	}
	
	private void setText()
	{
		txtP1Attk.setText("Attack: "+ ((Game.player2.lastRoundDamage > 0) ? "Hit for " : "Blocked for ")
				+ Game.player2.getDamageTakenString() + " Damage");
		txtP1Health.setText("Health: "+ Game.player1.getHealth());


		txtP2Attk.setText("Attack: "+ ((Game.player1.lastRoundDamage > 0) ? "Hit for " : "Blocked for ")
				+ Game.player1.getDamageTakenString() + " Damage");
		txtP2Health.setText("Health: "+ Game.player2.getHealth());
		
		txtRound.setText("Round: " + Game.getRound());

	}
	
	private void setUpButtons()
	{
		btnContinue = (Button)findViewById(R.id.btnNextRound);
		btnEndGame = (Button)findViewById(R.id.btnEndGame);
		btnConfirmYes = (Button)findViewById(R.id.btnConfirmYes);
		btnConfirmNo = (Button)findViewById(R.id.btnConfirmNo); 
	}
	
	private void setListeners()
	{
		btnContinue.setOnClickListener(this);
		btnEndGame.setOnClickListener(this);
		btnConfirmYes.setOnClickListener(this);
		btnConfirmNo.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
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
	
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btnNextRound:
			{
				if (confirm.getVisibility() == View.INVISIBLE)
				{
					Game.nextRound();
					startActivity(new Intent(Results.this, PlayerAttack.class));
					break;
				}
			}
			
			case R.id.btnEndGame:
			{
				if (confirm.getVisibility() == View.INVISIBLE)
				{
					confirm.setVisibility(View.VISIBLE);
					break;
				}
			}
			
			case R.id.btnConfirmYes:
			{
				Game.resetGame();
				startActivity(new Intent(Results.this, Splash.class));
				break;
			}
			
			case R.id.btnConfirmNo:
			{
				confirm.setVisibility(View.INVISIBLE);
			}
			
		}
	}
}
