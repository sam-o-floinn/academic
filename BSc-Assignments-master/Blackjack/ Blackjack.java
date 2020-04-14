/**
 * @author Sam O'Floinn
 * 
 * @param The primary class behind the program. 
 * 
 * --Note: Run this with an argument to provide a name (e.g. java BlackJack Trish)--
 * 
 * This class represents the card game Blackjack, between the player and the computer.
 * It has one primary method, play(), whilst drawing much support from private methods
 * and the other classes.
 * 
 * There are two additions to the requested program that I sought to make myself:
 * 
 * 1) An option for the user to view his own hand.
 * 2) A 'replay() ' method, for ease of use.
 * 
 */

import java.util.Scanner;
import java.util.Random;

public class BlackJack
{
  private Deck deck;
  private Scanner scan = new Scanner(System.in);
  Random rand = new Random();
  
  private Hand user;
  private Hand opponent;
  private PlayerOrder order;
  private Hand theOtherPlayer;
  
  Card chosenPlayerCard;  //this reference is to the card in the opponent's hand which the player has chosen to look at.
  Card chosenDealerCard;
  
  /**
   * @param args Supply one argument for the name of the player.
   */
  public static void main(String[] args)
  {
    if ( args.length != 1 ) {
      System.out.println( "Incorrect number of arguments: Please supply a name of the player" );
      System.exit(1);
    }
    String name = args[0];
    BlackJack begin = new BlackJack( name );
    begin.play();
  }
  
  public BlackJack(String name)
  {
    deck = new Deck();
    user = new Hand(name);
    opponent = new Hand("CPU");
    order = new PlayerOrder();
    
    order.addPlayer(user);
    order.addPlayer(opponent);
    
   scan.useDelimiter("\n");
  }
  
  
  /**
   * @param The primary method, and the only public one within this class. 
   * This runs the game, using all the other existing methods, plus the other classes I've created.
   *  
   * There is a loop to ensure that both players draw two cards to begin with, as specified. 
   *
   * The three stop conditions for the game are:
   * 1) when either player's hand sum exceeds 21
   * 2) when they have both chosen to wait.
   * 3) Both of their hands have five cards. 
   */
  public void play()
  {
    Hand whoseTurn;
    System.out.println("Take two cards each to start.");
    
    
    for (int i = 0; i < 4; i++)
    {
      whoseTurn = order.nextPlayer();
      whoseTurn.add(drawBlackJackCard(whoseTurn) );
      
      /*user.add(drawBlackJackCard(user) );
      opponent.add(drawBlackJackCard(opponent) );*/
    }
    System.out.println("Hands are initialised. Let the game begin!");
    System.out.println("");
    
    while 
      (!(user.getSum() > 21 || opponent.getSum() > 21 
             || (user.hasStuck() && opponent.hasStuck() ) 
             || (user.size() >= 5 && opponent.size() >= 5) ) )
    {
      System.out.println(user.getName() + "'s current sum is " + user.getSum() + ".");
      System.out.println("");
      whoseTurn = order.nextPlayer();
      if (!(whoseTurn.getName().equals("CPU")) )
      {
        doPlayerAction(whoseTurn);
      }
      else
      {
        doCPUAction(whoseTurn);
      }
    }
    determineWinner();
    replay();
  }
  
  /**
   * @param
   * The actions are split into two methods;
   * one for the player, and one for the computer.
   * The player method relies on input from the user to do one of four actions.
   * */
  
  private void doPlayerAction(Hand player)
  {
    if (!player.hasStuck() )
    {
      System.out.println("Turn: " + player.getName() );
      if (!(player.getName().equals("CPU") ) )
      {
        System.out.println(player.getName() + " has four possible actions: 'draw', 'stick,' 'see card' from the opponent's hand, or 'show my hand'.");
        System.out.print("What would you like to do? ");
        String action = scan.nextLine();
        
        if (action.equals("draw" ))
        {
          player.add(drawBlackJackCard(player) );
        }
        else if (action.equals("stick") )
        {
          player.stick();
        }
        else if (action.equals("see card") )
        { 
          seeOpponentCard(player, chosenPlayerCard);
          doPlayerAction(player);
        }
        else if (action.equals("show my hand") )
        {
          player.printHand();
          doPlayerAction(player);
        }
        else //i.e. an invalid answer is given.
        {
          System.out.println("Sorry, answer unrecognised. Please try again.");
          doPlayerAction(player);
        }
      }
      else  //the computer's turn
      {
        doCPUAction(player);  //player.add(drawBlackJackCard(player) );
      }
    }
    else
    {
      System.out.println(player.getName() + " is waiting.");
    }
  }
  
  /**
   * @param "doCPUAction()" represents the dealer's "AI".
   * I confess, I am no AI expert, nor have I much experience in this regard,
   * so I found designing this method to be a learning experience
   * I based it on chance, not unlike some human actions, whilst also applying
   * some logical conditions.
   * 
   * The CPU will stick either at random, when the user has stuck with a smaller value,
   * or when it has a sum of 20 or 21.
   * */
  private void doCPUAction(Hand dealer)
  {
    if (!dealer.hasStuck() )
    {
      int randNum = rand.nextInt(7);
      if (randNum == 7 && chosenDealerCard != null)
      {
        seeOpponentCard(dealer, chosenDealerCard);
        doCPUAction(dealer);
      }
      
      else if (randNum > 5 || ( (dealer.getSum() > user.getSum() ) && user.hasStuck()) || dealer.getSum() > 19 )
      {
        System.out.println(dealer.getName() + " has decided to stick.");
        dealer.stick();
      }
      else
      {
        dealer.add(drawBlackJackCard(dealer) );
      }
    }
    else 
    {
      System.out.println(dealer.getName() + " is waiting.");
    }
  }
    
      
  /**
   * @param
   * Win conditions are if:
     * 1) One player is not bust, but the other is. (Return not-bust hand.)
     * 2) One player's hand value sum is greater than the other's. (Return greatest-hand.)
     * Draw conditions are if both players have an equal sum.
     * */
  private Hand determineWinner()
  {
    Hand winningHand;
    System.out.println("Game ended! But who is the winner?");
    System.out.println("");
    
    System.out.println(user.getName() + ": " + user.getSum() );
    System.out.println(opponent.getName() + ": " + opponent.getSum() );
    
    if (user.getSum() > 21)
    {
      System.out.println(user.getName() + "'s hand has gone bust! " + opponent.getName() + " is the winner!");
      opponent.wins();
      winningHand = opponent;
    }
    else if (opponent.getSum() > 21)
    {
      System.out.println("The CPU's hand has gone bust! " + user.getName() + " is the winner!");
      user.wins();
      winningHand = user;
    }
    else if (user.getSum() > opponent.getSum() )
    {
      System.out.println(user.getName() + " wins!");
      user.wins();
      winningHand = user;
    }
    else if (user.getSum() < opponent.getSum() )
    {
      System.out.println("Dealer wins!");
      opponent.wins();
      winningHand = opponent;
    }
    else //they are equal
    {
      System.out.println("Draw!");
      return null;
    }
    System.out.println("The winning hand was: ");
    winningHand.printHand();
    return winningHand;
  }
  
  /**@param 
   * A bonus feature for the user, allowing them to play again with the same deck.*/
  private void replay()
  {
    System.out.print("Would you like to play again? Say 'no' if not. ");
    String choice = scan.nextLine();
    if (!(choice.equals("no") ))
    {
      user.emptyHand();
      opponent.emptyHand();
      chosenPlayerCard = null;
      chosenDealerCard = null;
      if (deck.size() < 10)
      {
        System.out.println("Deck is low on cards. Refilling the deck now!");
        deck = new Deck();
      }
      if (user.hasStuck() )
      {
        user.stick();  //This command works, since stick() inverts the player's 'stick' state.
      }
      if (opponent.hasStuck() )
      {
        opponent.stick();
      }
      play();
    }
  }
  
  /**
   * @param Draws a card from the deck and assigns it a Blackjack value.
   * Numbered cards have values equal to their number.
   * Jacks, Queens and Kings have value 10.
   * Aces can be either 1 or 11.
   * */
  private Card drawBlackJackCard(Hand player)
  {
    Card card = deck.drawCard();
    if (card.getName() == "Jack" || card.getName() == "Queen" || card.getName() == "King")
    {
      card.setValue(10);
    }
    else if (card.getName() == "Ace")
    {
      int aceValue = assignAceValue(player);
      card.setValue(aceValue);
    }
    else  //i.e. the card is a 2-10 card.
    {
      card.setValue(Integer.parseInt(card.getName() ) );
    }
    
    if (!(player.getName().equals("CPU") ) )
    {
      System.out.print(player.getName() + " has drawn ");
      card.printCard();
    }
    else 
    {
      System.out.println(player.getName() + " has drawn a card.");
    }
    return card;
  }
  
  /*@param
   * A special method for when an ace is drawn.
   * If the player draws an ace, he may choose whether it represents 1 or 11.
   */
  private int assignAceValue(Hand player)
  {
    if (!(player.getName() ).equals("CPU" ))  //i.e. if the hand is not that of the computer,
    {
      System.out.println("You drew an Ace. Please give it a value 1 or 11. Please only type numbers." );
      String input = scan.nextLine();
      if (input.equals("1") || input.equals("11") )
      {
        return Integer.parseInt(input);
      }
      else
      {
        System.out.println("That is not a valid option. Please try again.");
        return assignAceValue(player);
      }
    }
    else //( (player.getName() ).equals("CPU") )
    {
      if (player.getSum() < 11) 
      {
        return 11;
      }
      return 1;
    }
  }
  
  /**@param 
   * This section manages the required condition that the user may see one of the opponent's cards.
   * I considered leaving this as bare code within the action methods, but I began to feel this would defy
   * the object-oriented principle I had built the rest of my program around. 
   */
  private void seeOpponentCard(Hand player, Card chosenCard)
  {
      if (chosenCard == null)
      {
        theOtherPlayer = order.getNextPlayer();
        chosenCard = theOtherPlayer.showOneCard();
      }
        System.out.print(player.getName() + " knows that " + theOtherPlayer.getName() + " has ");
        chosenCard.printCard();
  }
}
