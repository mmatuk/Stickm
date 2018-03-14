//*********************************************************************
// PlayerAttack.java 						Group 1
// CSIT 266							Project
// this class is for the screen that allows the player to pick
// there attack location, defense location, and the stance type.
// When ever the player pick a location, the button changes color
// and a method is called to set the players location.
// results of the round are determined when both players have picked 
// all the locations needed to advance. 
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class PlayerAttack extends ActionBarActivity implements OnClickListener{

	// Creates the buttons for the attack, deffense and stance and ok
	private Button attkL, attkM, attkH, // buttons for attack location
		defL, defM, defH, //buttons for defense location
		stnD, stnN, stnA, // button for stance location
		btnAttkOK, // button for ok
		btnHelp, btnCloseHelp; // button for instructions
	// Creates the text views 
	private TextView currentPlayer, currentPlayerHealth;
	
	// creates the view for current players character
	private ImageView imageCurrentChoice;
	private final int btnSelectColor = R.drawable.btn_pressed; // sets the button to red when pressed

	private ProgressBar health;
	
	private RelativeLayout instructions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_attack);
		
		// sets up the interface the first time the screen is loaded
		if (savedInstanceState == null)
		{
			setUpButtons();
			setClickListeners();
			setUpViews();
			setCurrentPlayerText();
		}
		
		clearAttkBtn();
		clearDefBtn();			
		clearStnBtn();	
	}
	
	// Creates the buttons for the attack screen 
	private void setUpButtons()
	{
		attkL = (Button)findViewById(R.id.relativeLayoutCharacterScreenHelpScreen);
		attkM = (Button)findViewById(R.id.btnAttkMid);
		attkH = (Button)findViewById(R.id.btnAttkHigh);
		defL = (Button)findViewById(R.id.btnDefLow);
		defM = (Button)findViewById(R.id.btnDefMid);
		defH = (Button)findViewById(R.id.btnDefHigh);
		stnD =(Button)findViewById(R.id.btnStnDef);
		stnN = (Button)findViewById(R.id.btnStnN);
		stnA = (Button)findViewById(R.id.btnStnAgg);
		btnAttkOK = (Button)findViewById(R.id.btnAttkOK);
		btnHelp = (Button)findViewById(R.id.btnAttkHelp);
		btnCloseHelp = (Button)findViewById(R.id.btnAttkCloseHelp);

	}
	
	// sets up the text and picture views
	private void setUpViews()
	{
		currentPlayer = (TextView)findViewById(R.id.textViewCurrentPlayer);
		imageCurrentChoice = (ImageView)findViewById(R.id.imageViewCurrentPlayer);
		health =(ProgressBar)findViewById(R.id.progressBarHealthBar);
		currentPlayerHealth = (TextView)findViewById(R.id.textViewHealthAttkScreen);
		instructions = (RelativeLayout)findViewById(R.id.relativeLayoutAttkScreen);

	}
	
	// sets up listeners for each button
	private void setClickListeners()
	{
		attkL.setOnClickListener(this);
		attkM.setOnClickListener(this);
		attkH.setOnClickListener(this);
		defL.setOnClickListener(this);
		defM.setOnClickListener(this);
		defH.setOnClickListener(this);
		stnD.setOnClickListener(this);
		stnN.setOnClickListener(this);
		stnA.setOnClickListener(this);
		btnAttkOK.setOnClickListener(this);
		btnHelp.setOnClickListener(this);
		btnCloseHelp.setOnClickListener(this);


	}
	
	// clears any selection for each set of buttons
	private void clearAttkBtn()
	{
		attkL.setBackgroundResource(R.drawable.btn);
		attkM.setBackgroundResource(R.drawable.btn);
		attkH.setBackgroundResource(R.drawable.btn);

	}
	
	private void clearDefBtn()
	{
		defL.setBackgroundResource(R.drawable.btn);
		defM.setBackgroundResource(R.drawable.btn);
		defH.setBackgroundResource(R.drawable.btn);
	}
	
	private void clearStnBtn()
	{
		stnD.setBackgroundResource(R.drawable.btn);
		stnN.setBackgroundResource(R.drawable.btn);
		stnA.setBackgroundResource(R.drawable.btn);
	}
	
	// sets the text and picture to which ever player is picking there attk
	private void setCurrentPlayerText()
	{
		if (Game.getCurrentPlayer() == 1)
		{
			currentPlayer.setText("Player 1 Attack");
			imageCurrentChoice.setBackgroundResource(Game.player1.displayChara());
			health.setProgress(Game.player1.health);
			currentPlayerHealth.setText("Health: " + Game.player1.getHealth());

		}
		else 
		{
			currentPlayer.setText("Player 2 Attack");
			imageCurrentChoice.setBackgroundResource(Game.player2.displayChara());
			health.setProgress(Game.player2.health);
			currentPlayerHealth.setText("Health: " + Game.player2.getHealth());

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_attack, menu);
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
			case R.id.relativeLayoutCharacterScreenHelpScreen:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setAttk(0);
				}
				else
				{
					Game.player2.setAttk(0);
				}
				clearAttkBtn();
				attkL.setBackgroundResource(btnSelectColor);
				break;
			}
			
			case R.id.btnAttkMid:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setAttk(1);
				}
				else
				{
					Game.player2.setAttk(1);
				}
				clearAttkBtn();
				attkM.setBackgroundResource(btnSelectColor);
				break;
			}
				
			case R.id.btnAttkHigh:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setAttk(2);
				}
				else
				{
					Game.player2.setAttk(2);
				}
				clearAttkBtn();
				attkH.setBackgroundResource(btnSelectColor);
				break;
			}
			
			case R.id.btnDefLow:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setDef(0);
				}
				else
				{
					Game.player2.setDef(0);
				}
				clearDefBtn();
				defL.setBackgroundResource(btnSelectColor);
				break;
			}
			
			case R.id.btnDefMid:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setDef(1);
				}
				else
				{
					Game.player2.setDef(1);
				}
				clearDefBtn();
				defM.setBackgroundResource(btnSelectColor);
				break;
			}
			
			case R.id.btnDefHigh:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setDef(2);
				}
				else
				{
					Game.player2.setDef(2);
				}
				clearDefBtn();
				defH.setBackgroundResource(btnSelectColor);
				break;
			}
			
			case R.id.btnStnDef:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setStance(0);
				}
				else
				{
					Game.player2.setStance(0);
				}
				clearStnBtn();
				stnD.setBackgroundResource(btnSelectColor);
				break;
			}
			
			case R.id.btnStnN:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setStance(1);
				}
				else
				{
					Game.player2.setStance(1);
				}
				clearStnBtn();
				stnN.setBackgroundResource(btnSelectColor);
				break;
			}
			
			case R.id.btnStnAgg:
			{
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setStance(2);
				}
				else
				{
					Game.player2.setStance(2);
				}
				clearStnBtn();
				stnA.setBackgroundResource(btnSelectColor);
				break;
			}
			
			case R.id.btnAttkOK:
			{
				if (Game.getCurrentPlayer() == 2)
				{
					if (Game.player2.hasChosenAll())
					{
						Game.calculateDamage();
						Game.setRoundCompleted(true);
						startActivity(new Intent(PlayerAttack.this, Results.class));
						finish();
					}
				}
				else
				{
					if (Game.player1.hasChosenAll())
					{
						Game.setCurrentPlayer(2);
						clearAttkBtn();
						clearDefBtn();
						clearStnBtn();
						setCurrentPlayerText();
					}
				}
				break;
			}
			
			case R.id.btnAttkHelp:
			{
				instructions.setVisibility(View.VISIBLE);
				break;
			}
			
			case R.id.btnAttkCloseHelp:
			{
				instructions.setVisibility(View.INVISIBLE);
				break;
			}
			default:
			{
				break;
			}
		}
	}
}
