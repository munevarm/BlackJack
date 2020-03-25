/*
 * Shankar Ghimire
 * StudentId:991585190
 * Course: ...............
 */
package game;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dell
 */
public class DeckOfCards   {
    
    //The group of cards, stored in an ArrayList
    private static  ArrayList <Card> cards = new ArrayList<>();
    private static Card aCard;
    private static int size=52;//the size of the grouping
    public DeckOfCards(){
        cards = new ArrayList<>();
    }
    public DeckOfCards(int givenSize) {
//        super(givenSize);
        this.size = givenSize;
        cards = new ArrayList<>();        
    }
    //method to count total cards in playing card deck and return it
    //it counts all cards at the time of creation of full cards or after card
    //is dealed to 
    public static  int countTotalCards(){
        return cards.size();
    }
    
    //this method deals the top card to the player or delaer on demand
    //and also updates the playing deck by remove the dealed card immediately
    public static  Card dealTopCard(){
        Card topCard = DeckOfCards.cards.get(0);
        DeckOfCards.cards.remove(0);
        return topCard;
    }
//    public  Card getTopCard(){
//        
//        return this.cards.get(0);
//    }
    public static ArrayList<Card> getCards() {
        return cards;
    }
    
    //deal a card
    public static Card dealCard(ArrayList<Card>currentCard){
        
        return DeckOfCards.cards.get(0);       
    }
    
    /**
     * A method that will get the group of cards as an ArrayList
     * @return the group of cards.
     */
//    public ArrayList<Card> showCards()
//    {
//        return cards;
//    }   
    public static void shuffle()
    {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public static int getSize() {
        return size;
    }

    /**
     * @param givenSize the max size for the group of cards
     */
    public static void setSize(int givenSize) {
        size = givenSize;
    }
    
    //Adds 52 individual playing card into the deck
    public static  void createFullDeck(){
        //Loop through the four suits of the card
        for(Suit cardSuit : Suit.values()){
            //loops through the face values of the card i.e. 2, 3, 4, ....ACE
            for(FaceValue cardFaceValue : FaceValue.values()){
                aCard = new game.Card(cardSuit,cardFaceValue);
                DeckOfCards.cards.add(aCard);
            }
        }
    }
    
    //method to print all cards
    //for testing purpose only
    //should be disable/uncommented after testing
    public static void printAllCards(){
        //Loops through all 52 cards
        for(Card aCard : DeckOfCards.cards){
            System.out.println(aCard.toString());
        }
    }
    
    //method to remove a card from the deck
    public static  void removeCard(int i){
        cards.remove(i);
    }
    
    //method to add a card to deck
    public static void addCard(Card aCard){
        
        DeckOfCards.cards.add(aCard);
    }
    //method to get a card at a time
//    public Card getCard(int i){
//        return this.cards.get(i);
//    }
     
    //calculates the total value of the cards in each group of cards
    public static int calculateCardValues(ArrayList<Card>cardList){
        int totalValue = 0;
        int aces = 0;
        
        //for every card in the group/deck
        for(Card aCard : cardList){
            switch(aCard.getFaceValue()){
                case TWO:
                    totalValue += 2;
                    break;
                case THREE:
                    totalValue += 3;
                    break;
                 case FOUR:
                    totalValue += 4;
                    break;
                case FIVE:
                    totalValue += 5;
                    break;  
                    case SIX:
                    totalValue += 6;
                    break;
                case SEVEN:
                    totalValue += 7;
                    break;
                    case EIGHT:
                    totalValue += 8;
                    break;
                case NINE:
                    totalValue += 9;
                    break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING:
                    totalValue += 10;
                    break;
                case ACE:
                    aces++;
                    break;
                default:
                    break;
            }
        }   
            //Code to determine face value of ace 
            //if alreay card's  total face value is >=11, then ace face value is 1
            //if alreay card's total face value is <=10, then ace face value is 11
            for(int i = 0; i < aces; i++){
                //if()
                if(totalValue >= 11){
                    totalValue += 1;
                }
                else{
                    totalValue =+11;
                }               
            }
   
        return totalValue;
    }
    
    
    
}
