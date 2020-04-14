/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package project;

import game.FaceValue;
import game.Suit;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game.
 * HINT, you might want to subclass this more than once.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 * @author megha,2020
 */
public class GroupOfCards 
{
   
    //The group of cards, stored in an ArrayList
    protected ArrayList <Card> cards;
    protected int size;//the size of the grouping
    public GroupOfCards(){
        cards = new ArrayList<>();
    }
    public GroupOfCards(int givenSize)
    {
        size = givenSize;
        cards = new ArrayList<>();
    }
    
    /**
     * A method that will get the group of cards as an ArrayList
     * @return the group of cards.
     */
    public ArrayList<Card> showCards()
    {
        return cards;
    }
    
    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param givenSize the max size for the group of cards
     */
    public void setSize(int givenSize) {
        size = givenSize;
    }
    
//    //Adds 52 individual playing card into the deck
//    public void createFullDeck(){
//        //Loop through the four suits of the card
//        for(Suit cardSuit : Suit.values()){
//            //loops through the face values of the card i.e. 2, 3, 4, ....ACE
//            for(FaceValue cardFaceValue : FaceValue.values()){
//                game.Card aCard = new game.Card(cardSuit,cardFaceValue);
//                this.cards.add(aCard);
//            }
//        }
//    }
    
    //method to print all cards
    //for testing purpose only
    //should be disable/uncommented after testing
//    public void printAllCards(){
//        //Loops through all 52 cards
//        for(Card aCard : cards){
//            System.out.println(aCard.toString());
//        }
//    }
    
    //method to remove a card from the deck
//    public void removeCard(int i){
//        this.cards.remove(i);
//    }
    
    //method to add a card to deck
//    public void addCard(Card addCard){
//        this.cards.add(addCard);
//    }
    //method to get a card at a time
//    public Card getCard(int i){
//        return this.cards.get(i);
//    }
     
//    //calculates the total value of the cards in each group of cards
//    public int calculateCardValues(ArrayList<Card>cardList){
//        int totalValue = 0;
//        int aces = 0;
//        
//        //for every card in the group/deck
//        for(Card aCard : cardList){
////            switch(aCard.getFaceValue()){
//                
////            }
//            System.out.println(aCard.toString());
//        }
//        Card c = new Card();
//        
//        return totalValue;
//    }
}//end class
