/* @author Sam O'Floinn
 * 
 * @param This class constitutes the structure of each card that appears in a deck.
 * Here, the card's type and value is initialised.
 * 
 * Jack, Queen and King cards have a value of 10.
 * An Ace can be either 1 or 11.
 * Cards 2-10 are valued as such.
 * The Joker is not used in ordinary Blackjack.
 *
 * With making the card object, there are three tasks:
 * */

public class Card {
  
  private String name;
  private String type;
  private int value;
  
  public Card(String cardInput, String cardType) {
    name = cardInput;
    type = cardType;
  }
  
  public static void main (String[] args) {
    Card testCard = new Card("Jack", "Hearts");
    System.out.println("I have got the " + testCard.getName() + " of " + testCard.getType() + "!");
    System.out.println("This has a score of " + testCard.getValue() + "!");
  }
  
  /*@param The following methods consist mostly of getters and a single setter.
   * I have not created any setters for the name and type, since these are things which are never supposed to be changed after initialisation.
   * The value, however, can vary depending on the game, and so is given a setter.*/
  public String getName()
  {
    return name;
  }
 
  public String getType()
  {
    return type;
  }
  
  public int getValue()
  {
    return value;
  }
  
  public void setValue(int cardValue)
  {
    value = cardValue;
  }
  
  public void printCard()
  {
    System.out.println("The " +  name + " of " + type + " with value " + value);
  }
  
  /*@param I have left this here, much like many of my commented lines, to show you some of my patterns
   * in testing the code. */
  public void test(Card testCard)
  {
    System.out.println("I have got the " + testCard.getName() + " of " + testCard.getType() + "!");
    System.out.println("This has a score of " + testCard.getValue() + "!");
  }
  
}