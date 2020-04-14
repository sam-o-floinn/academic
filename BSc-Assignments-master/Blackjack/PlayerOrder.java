/**
 * @author Sam O'Floinn
 * 
 * @param This class contains a list of the amount of people playing, 
 * and helps the card game class  move to the next game.
 * This code could have been written inside the game, but I thought to make it its own class for two reasons:
 * 
 * 1) Maintainability. It would make the initial program easier to read.
 * 2) Flexibility. In a real working environment, it may be that my classes are used to create other card games,
 *    and so I thought to provide the code here for any card game class to use. This also avoids repetition.
 */

import java.util.ArrayList;
public class PlayerOrder
{
  private int index = 0;
  private ArrayList<Hand> players = new ArrayList<Hand> ();
  
  /**
   * @param Adds a new player to the group of people playing.
   */
  public void addPlayer(Hand player)
  {
    players.add(player);
  }
  
  /**
   * @param 
   * Gets the next player without moving the index forward.
   * If the index is at the end of the list, it gets the first player in the list.
   * */
  public Hand getNextPlayer()
  {
    if ( (index + 1) >= players.size() )
    {
      return players.get(index);
    }
    return players.get(index + 1);
  }
  
  /**
   * @param
   * Moves the index forward. 
   * If at the end of the index, returns to the beginning. 
   */
  public Hand nextPlayer()
  {
    if (index >= players.size())
    {
      index = 0;
    }
    return players.get(index++);
  }
  
}