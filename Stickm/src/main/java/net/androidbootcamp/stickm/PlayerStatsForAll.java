//*********************************************************************
// PlayerStatsForAll.java 								Group 1
// CSIT 266									Project
// this class is a parent class to PlayerStats and contains info 
// for the attack damage and multipliers for the players. 
//*********************************************************************
package net.androidbootcamp.stickm;

public abstract class PlayerStatsForAll 
{

	protected final int HIGHDMG = 15;
	protected final int LOWDMG = 10;
	protected final int NODMG = 0;
	
	protected int[][] damageTaken;
	protected int[] multiplier;
	
	// array of all the player character picture id's
	protected int[] characterPictures = new int[] {R.drawable.character001, 
			R.drawable.character002, 	R.drawable.character003, 	R.drawable.character004,
			R.drawable.character005, 	R.drawable.character006};
	
	public PlayerStatsForAll()
	{
		damageTaken = new int[][] {{NODMG, LOWDMG, HIGHDMG},
								   {LOWDMG, NODMG, LOWDMG}, 
								   {HIGHDMG, LOWDMG, NODMG}};
		multiplier = new int[] {-5, 1, 10}; 
		
	}
	
	
}
