//***********************************************************************
//	PlayerStats.java						Group1
//	CSIT 266								Group Project
//	This class is used to contain all the player stats and variables
// 	used by player 1 and player 2
//***********************************************************************

package net.androidbootcamp.stickm;

public class PlayerStats extends PlayerStatsForAll //implements Parcelable
{
	protected int attkMultiplier = 0;
	protected int defMultiplier = 0;
	protected int health;
	protected int attkLocation = 100; // 0 = low, 1 = mid, 2 = high
	protected int defLocation = 100; // 0 = low, 1 = mid, 2 = high
	protected int stance = 100; // 0 = deff, 1 = neutral, 2 = aggressive 
	protected int stanceAttkMulti = 0;
	protected int stanceDefMulti = 0;
	protected int lastRoundDamage = 0; // shows damge taken last round
	
	private int playerCharacterChoice = 0;
	
	protected String name;
	
	public PlayerStats(String playerName)
	{
		name = playerName;
		health = 100;
	}
	
	public PlayerStats() {
	}
	
	// returns a string the represents the attack location of the player
	public String getAttkLocation()
	{
		switch (attkLocation)
		{
			case 0:
			{
				return "LOW";
			}
			
			case 1:
			{
				return "MIDDLE";
			}
			
			case 2:
			{
				return "HIGH";
			}
			default:
			{
				return "NA";
			}
		}
	}
	
	
	// retruns a string that tells which location the player defended
	public String getDefLocation()
	{
		switch (defLocation)
		{
			case 0:
			{
				return "LOW";
			}
			
			case 1:
			{
				return "MIDDLE";
			}
			
			case 2:
			{
				return "HIGH";
			}
			default:
			{
				return "NA";
			}
		}
	}
	
	// returns a string that represents the players stance location
	public String getStanceLocation()
	{
		switch (stance)
		{
			case 0:
			{
				return "DEFENSIVE";
			}
			
			case 1:
			{
				return "NETURAL";
			}
			
			case 2:
			{
				return "AGGRESSIVE"; // aggressive 
			}
			default:
			{
				return "NA";
			}
		}
	}

	public void setAttk(int location)
	{
		// only allows the attack to be set to 0-2
		if (location >= 0 && location <= 2)
		{
			attkLocation = location;
		}
	}
	
	public void setDef(int location)
	{
		// only allows the def to be set to 0-2
		if (location >= 0 && location <= 2)
		{
			defLocation = location;
		}
	}
	
	public void setStance(int location)
	{
		// only allows the stance to be set to 0-2
		if (location >= 0 && location <= 2)
		{
			stance = location;
			setMulti();
		}
	}
	
	// player character to only a character from 1 -6 
	public void setCharaChoice(int chara)
	{
		if (chara >= 1 && chara <=6)
		{
			playerCharacterChoice = chara;
		}
	}
	
	public int getCharaChoice()
	{
		return playerCharacterChoice;
	}
	
	// returns the id of the charater that the player choose
	public int displayChara()
	{
		// returns basic image when player has not chosen a character yet 
		if (playerCharacterChoice == 0)
		{
			return R.drawable.ic_launcher;
		}
		// returns what character the player chose
		else
		{
			return characterPictures[playerCharacterChoice-1];
		}
	}
	
	public boolean hasChosenAll()
	{
		if (attkLocation < 100 && defLocation < 100 && stance < 100)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	//returns last damage taken
	public String getDamageTakenString()
	{
		return Integer.toString(lastRoundDamage);
	}
	
	// sets the multipliers
	private void setMulti()
	{
		switch (stance)
		{
			case 0:
				attkMultiplier = multiplier[0];
				defMultiplier = multiplier[2];
				break;
				
			case 1:
				attkMultiplier = multiplier[1];
				defMultiplier = multiplier[1];
				break;
				
			case 2:
				attkMultiplier = multiplier[2];
				defMultiplier = multiplier[0];
				break;
				
			default:
				break;
		}
	}
	
	public void calulateDamge(int location, int otherPLayerAttkMulti)
	{
		int damage = (damageTaken[defLocation][location] + otherPLayerAttkMulti) - defMultiplier;
		if (damage >= 0)
		{
			lastRoundDamage = damage;
		}
		else
		{
			lastRoundDamage =0;
		}
		
		// set health to a new health after damage is taken. If damage take is negative
		// then no damage is added to the health because a negative damage would add 
		// health
		health = health - ((damage < 0) ? 0 : damage);
		
		if (health < 0)
		{
			health = 0;
		}
	}
	
	// sets locations back to 100
	public void clearLocation()
	{
		attkLocation = 100;
		defLocation = 100;
		stance = 100;
	}
	
	public String getHealth()
	{
		return Integer.toString(health); 
	}
	
	public void newGame()
	{
		health = 100;
		attkLocation = 100;
		defLocation =100;
		stance = 100;
		attkMultiplier = 0;
		defMultiplier =0;
		playerCharacterChoice = 0;
		lastRoundDamage = 0;
	}
	
	public String toString()
	{
		return Integer.toString(health);
	}
	
	/*
	public static final Parcelable.Creator<PlayerStats> CREATOR = new Creator<PlayerStats>() { 
		  public PlayerStats createFromParcel(Parcel source) { 
		      PlayerStats player = new PlayerStats(); 
		     
		      player.name = source.readString();
		       
		      player.attkMultiplier = source.readInt(); 
		      player.defMultiplier = source.readInt(); 
		      player.health = source.readInt(); 
		      player.attkLocation = source.readInt(); 
		      player.defLocation = source.readInt(); 
		      player.stance = source.readInt(); 
		      player.stanceAttkMulti = source.readInt(); 
		      player.stanceDefMulti = source.readInt(); 
		      player.playerCharater = source.readInt();
		    
		      return player; 
		  } 
		
		  public PlayerStats[] newArray(int size) { 
		      return new PlayerStats[size]; 
		  }
		     }; 
		     
		     public int describeContents() { 
		  return 0; 
		     }
		     public void writeToParcel(Parcel parcel, int flags) { 
		  parcel.writeInt(attkMultiplier); 
		  parcel.writeInt(defMultiplier); 
		  parcel.writeInt(health); 
		  parcel.writeInt(attkLocation); 
		  parcel.writeInt(defLocation); 
		  parcel.writeInt(stance); 
		  parcel.writeInt(stanceAttkMulti); 
		  parcel.writeInt(stanceDefMulti); 
		  parcel.writeInt(playerCharater); 
		  parcel.writeString(name); 

		     }
	 	*/
}