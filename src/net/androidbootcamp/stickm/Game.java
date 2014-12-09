//*********************************************************************
// Game.java 								Group 1
// CSIT 266									Project
// This class is for the over all Game data and stats
// The player objects are created here and all methods and data is 
// static so it can be accessed with needing a object created.
//*********************************************************************

package net.androidbootcamp.stickm;

import net.androidbootcamp.stickm.PlayerStats;

public class Game extends PlayerStats
{
	protected static PlayerStats player1;
	protected static PlayerStats player2;
	private static int currentPlayer = 0; // 1= player 1 , 2= player 2, 0 = no player
	private static int winner = 3; // 1= player 1 , 2= player 2, 0 = draw, 3= start of game/no winner
	private static int round = 1; // number of current round
	private static boolean roundCompleted = false; // this is used to determine which screen to load from the splash screen.
	private static boolean charactersChosen = false; // this is used to determine if a game already exists. 
	

	// creates the players if no player object exists
	public static void MakePlayers()
	{
		if (player1 == null)
		{
			player1 = new PlayerStats("Player 1");

		}
		if (player2 == null)
		{
			player2 = new PlayerStats("Player 2");
		}
		
	}
	
	public static void setRoundCompleted(boolean bool)
	{
		roundCompleted = bool;
	}
	
	public static boolean getRoundCompleted()
	{
		return roundCompleted;
	}
	
	public static void setCharactersChosen(boolean bool)
	{
		charactersChosen = bool;
	}
	
	public static boolean getCharactersChosenValue()
	{
		return charactersChosen;
	}
	
	
	// Sets up the next round of the game
	public static void nextRound()
	{
		increaseRound();
		roundCompleted = false;
		currentPlayer = 1;
		player1.clearLocation();
		player2.clearLocation();
	}
	
	// allows only 0,1,2 to be set as the currentPlayer
	public static void setCurrentPlayer(int player)
	{
		if (player >= 0 && player <=2)
		{
			currentPlayer = player;
		}
		else 
		{
			System.out.println("error: tried to set current player variable to somthing other than 0,1,2");
		}
	}
	
	// return a boolean truen if there is a winner or a draw
	public static boolean isThereWinner()
	{
		if (winner == 1 || winner == 2 || winner == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// returns the winner
	public static int getWinner()
	{
		return winner;
	}
	
	//returns the winner as a string
	public static String getWinnerString()
	{
		switch (winner)
		{
			case 0:
			{
				return "Tie";
			}
			
			case 1:
			{
				return "Player 1";
			}
			
			case 2:
			{
				return "Player 2";
			}
			default:
			{
				return "No winner";
			}
		}
	}
	
	// calculates the damage dealt to each player 
	public static void calculateDamage()
	{
		player1.calulateDamge(player2.attkLocation, player2.attkMultiplier);
		player2.calulateDamge(player1.attkLocation, player1.attkMultiplier);
		
		// if both players died, then winner is 0 (a draw)
		if (player1.health == 0 && player2.health == 0)
		{
			winner = 0;
		}
		else
		{
			// winner set to player 2 if player 1 health is 0
			if (player1.health == 0)
			{
				winner = 2;
			}
			else
			{
				// winner set to player 1 if player 2 health is 0
				if (player2.health == 0)
				{
					winner = 1;
				}
			}
		}
	}
	
	// resets the sets for each player
	public static void resetGame()
	{
		player1.newGame();
		player2.newGame();
		currentPlayer = 0;
		winner = 3;
		round = 1;
		roundCompleted = false;
		charactersChosen = false;
	}
	
	// returns the current player active
	public static int getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	// increases the number of rounds by 1
	public static void increaseRound()
	{
		if (roundCompleted == true)
		{
			round++;
		}
	}
	
	// returns the round
	public static String getRound()
	{
		return Integer.toString(round);
	}
}
