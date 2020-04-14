/**
 * @param This class was made just for testing the card, deck and hand classes 
 * before work on the BlackJack class began.
 * It does, however, use the blackjack way to assign values to cards.
 * 
 * While irrelevant to the overall application, I have decided to keep this here to show my work.
 */


import java.util.Scanner;

public class TestCardDrawing {
  
  Scanner scan = new Scanner(System.in);
  
  public static void main (String[] args) 
  {
    TestCardDrawing testBegins = new TestCardDrawing();
    Hand hand = new Hand("Bob");
    Deck testDeck = new Deck();
    
    for (int i = 0; i < 5; i++)
    {
      System.out.println(testDeck.size() );
      Card drawnCard = testDeck.drawCard();
      testBegins.assignValue(drawnCard);
      hand.add(drawnCard);
      System.out.println("This hand's current value is " + hand.getSum() );
    }
    hand.printHand();
    System.out.println("This hand has a total value of " + hand.getSum() );
    System.out.println("");
    hand.emptyHand();
    System.out.println(hand.size() );
    System.out.println("End!");
  }
  
  public void  assignValue(Card card)
  {
    if (card.getName() == "Jack" || card.getName() == "Queen" || card.getName() == "King")
    {
      card.setValue(10);
    }
    else if (card.getName() == "Ace")
    {
      int aceValue = assignAceValue();
      card.setValue(aceValue);
    }
    else
    {
      card.setValue(Integer.parseInt(card.getName() ) );
    }
    card.printCard();
  }
  
  public int assignAceValue()
  {
    System.out.println("You drew an Ace. Please give it a value 1 or 11." );
    int input = scan.nextInt();
    if (input == 1 || input == 11)
    {
      return input; 
    }
    else
    {
      System.out.println("That is not a valid option. Please try again.");
      return assignAceValue();
    }
  }
}