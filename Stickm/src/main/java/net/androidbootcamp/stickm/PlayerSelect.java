//*********************************************************************
// PlayerSelect.java 						Group 1
// CSIT 266									Project
// This class is for the player select screen.
// Each player will be able to pick one of six different characters.
// When a character is picked, the button changes color and a method
// is called to set the players choice. When both players have chosen 
// a character, then the screen will advance. 
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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlayerSelect extends ActionBarActivity implements OnClickListener {
	
	// Create all the buttons for the page buttons
	private Button chara1, chara2, chara3, chara4, chara5, chara6,
		btnP1, btnHelp, btnOK, btnCloseHelp;
	// Creates the text views 
	private TextView currentPlayer;
	// creates the view view for charater choosen
	private ImageView imageCurrentChoice;
	
	private RelativeLayout instructions;

	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_select);
		
		
		
		// Set up interface for only the first time the screen is loaded
		if (savedInstanceState == null)
		{
			setUpButtons();
			setUpViews();
			setClickListeners();
		}
		
		// set current player to player 1 anytime this screen is loaded
		Game.setCurrentPlayer(1);
		
		// displays the current players character and name
		displayCharacterChoice();
		setCurrentPlayerView();
	}
	
	// Sets each button variable to the actual button
	private void setUpButtons()
	{
		chara1 = (Button)findViewById(R.id.btnCharacter1);
		chara2 = (Button)findViewById(R.id.btnCharacter2);
		chara3 = (Button)findViewById(R.id.btnCharacter3);
		chara4 = (Button)findViewById(R.id.btnCharacter4);
		chara5 = (Button)findViewById(R.id.btnCharacter5);
		chara6 = (Button)findViewById(R.id.btnCharacter6);
		btnHelp =(Button)findViewById(R.id.btnCharacterScreenHelp);
		btnP1 = (Button)findViewById(R.id.btnBackP1);
		btnOK = (Button)findViewById(R.id.btnOK);
		btnCloseHelp = (Button)findViewById(R.id.btnCaracterScreenCloseHelp);


	}
	
	// creates listeners for each button 
	private void setClickListeners()
	{
		chara1.setOnClickListener(this);
		chara2.setOnClickListener(this);
		chara3.setOnClickListener(this);
		chara4.setOnClickListener(this);
		chara5.setOnClickListener(this);
		chara6.setOnClickListener(this);
		btnHelp.setOnClickListener(this);
		btnP1.setOnClickListener(this);
		btnOK.setOnClickListener(this);
		btnCloseHelp.setOnClickListener(this);

	}
	
	// clears the selection of the buttons
	private void clearButtons()
	{
		chara1.setBackgroundResource(R.drawable.btn);
		chara2.setBackgroundResource(R.drawable.btn);
		chara3.setBackgroundResource(R.drawable.btn);
		chara4.setBackgroundResource(R.drawable.btn);
		chara5.setBackgroundResource(R.drawable.btn);
		chara6.setBackgroundResource(R.drawable.btn);

	}
	
	// Set in initial text and image views
	private void setUpViews()
	{
		currentPlayer = (TextView)findViewById(R.id.txtViewCurrentPlayer);
		imageCurrentChoice = (ImageView)findViewById(R.id.CurrentCharacter);
		instructions = (RelativeLayout)findViewById(R.id.relativeLayoutCharacterScreenHelpScreen);

	}
	
	// displays what character the play choose
	private void displayCharacterChoice()
	{
		if (Game.getCurrentPlayer() == 1)
		{
			if (Game.player1.getCharaChoice() != 0)
			{
				imageCurrentChoice.setBackgroundResource(Game.player1.displayChara());
				displayCharacterButton(Game.player1.getCharaChoice());
			}
			else
			{
				imageCurrentChoice.setBackgroundResource(0);
			}
		}
		else
		{
			if (Game.player2.getCharaChoice() != 0)
			{
				imageCurrentChoice.setBackgroundResource(Game.player2.displayChara());
				displayCharacterButton(Game.player2.getCharaChoice());
			}
			else
			{
				imageCurrentChoice.setBackgroundResource(0);
			}

		}
	}
	
	// changes the button to represent the character choosen if the player already has chosen a character
	private void displayCharacterButton(int playerChara)
	{
		switch (playerChara)
		{
			case 1:
			{
				chara1.setBackgroundResource(R.drawable.btn_pressed);
				break;
			}
			
			case 2:
			{
				chara2.setBackgroundResource(R.drawable.btn_pressed);
				break;
			}
			
			case 3:
			{
				chara3.setBackgroundResource(R.drawable.btn_pressed);
				break;
			}
			
			case 4:
			{
				chara4.setBackgroundResource(R.drawable.btn_pressed);
				break;
			}
			
			case 5:
			{
				chara5.setBackgroundResource(R.drawable.btn_pressed);
				break;
			}
			
			case 6:
			{
				chara6.setBackgroundResource(R.drawable.btn_pressed);
				break;
			}
			
			default:
			{
				break;
			}
		}
	}
	
	// Sets the text for which player is picking their character
	private void setCurrentPlayerView()
	{
		if (Game.getCurrentPlayer() == 1)
		{
			currentPlayer.setText(Game.player1.name + " Select Character");
			btnP1.setVisibility(View.INVISIBLE);
		}		
		else
		{
			currentPlayer.setText(Game.player2.name + " Select Character");
			btnP1.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_select, menu);
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
	
	// This is what happens when a button is clicked
	public void onClick(View v)
	{
		// set the characters choice
		switch (v.getId())
		{
			case R.id.btnBackP1:
			{
				Game.setCurrentPlayer(1);
				setCurrentPlayerView();
				displayCharacterChoice();

				break;
			}
			
			case R.id.btnCharacterScreenHelp:
			{
				instructions.setVisibility(View.VISIBLE);
				break;
			}
			
			case R.id.btnCaracterScreenCloseHelp:
			{
				instructions.setVisibility(View.INVISIBLE);
				break;
			}
			
			default:
			{
				break;
			}
			
			case R.id.btnCharacter1:
			{
				clearButtons();
				chara1.setBackgroundResource(R.drawable.btn_pressed);
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setCharaChoice(1);
					displayCharacterChoice();
				}
				else
				{
					Game.player2.setCharaChoice(1);
					displayCharacterChoice();
				}
				break;

			}
			case R.id.btnCharacter2:
			{
				clearButtons();
				chara2.setBackgroundResource(R.drawable.btn_pressed);
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setCharaChoice(2);
					displayCharacterChoice();
				}
				else
				{
					Game.player2.setCharaChoice(2);
					displayCharacterChoice();
				}
				break;

			}
			
			case R.id.btnCharacter3:
			{
				clearButtons();
				chara3.setBackgroundResource(R.drawable.btn_pressed);
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setCharaChoice(3);
					displayCharacterChoice();
				}
				else
				{
					Game.player2.setCharaChoice(3);
					displayCharacterChoice();
				}
				break;

			}
			
			case R.id.btnCharacter4:
			{
				clearButtons();
				chara4.setBackgroundResource(R.drawable.btn_pressed);
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setCharaChoice(4);
					displayCharacterChoice();
				}
				else
				{
					Game.player2.setCharaChoice(4);
					displayCharacterChoice();
				}
				break;

			}
			
			case R.id.btnCharacter5:
			{
				clearButtons();
				chara5.setBackgroundResource(R.drawable.btn_pressed);
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setCharaChoice(5);
					displayCharacterChoice();
				}
				else
				{
					Game.player2.setCharaChoice(5);
					displayCharacterChoice();
				}
				break;

			}
			
			case R.id.btnCharacter6:
			{
				clearButtons();
				chara6.setBackgroundResource(R.drawable.btn_pressed);
				if (Game.getCurrentPlayer() == 1)
				{
					Game.player1.setCharaChoice(6);
					displayCharacterChoice();
				}
				else
				{
					Game.player2.setCharaChoice(6);
					displayCharacterChoice();
				}
				break;
			}
			
			case R.id.btnOK:
			{
			
				// if player 2 pushes ok, then game moves to next screen "Player 1 Attack"
				if (Game.getCurrentPlayer() == 2)
				{
					// makes sure the player choose a charater before continuing
					if (Game.player2.getCharaChoice() != 0)
					{
						Game.setCurrentPlayer(1);
						Game.setCharactersChosen(true);
						startActivity(new Intent(PlayerSelect.this, PlayerAttack.class));
						finish();
					}
				}
				// This is for when player 1 pushes ok
				else
				{
					// makes sure the player choose a charater before continuing
					if (Game.player1.getCharaChoice() != 0)
					{
						Game.setCurrentPlayer(2);
						setCurrentPlayerView();
						clearButtons();
						displayCharacterChoice();
					}
				}
				break;
			}
		}
	}
}
