/*
 * Shankar Ghimire
 * StudentId:991585190
 * Course: ...............
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Dealer {
    private ArrayList<Card>dealerCards;
    private int totalCardValue;
    //constructor 
    public Dealer(){
        dealerCards = new ArrayList<>();
    }
    public void addCard(Card currentCard){
        this.dealerCards.add(currentCard);
    }
    /**
     * @return the dealerCards
     */
    public ArrayList<Card> getDealerCards() {
        return dealerCards;
    }

    /**
     * @param dealerCards the dealerCards to set
     */
    public void setDealerCards(ArrayList<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }

    /**
     * @return the totalCardValue
     */
    public int getTotalCardValue() {
        totalCardValue = DeckOfCards.calculateCardValues(dealerCards);
        return totalCardValue;

    }

    /**
     * @param totalCardValue the totalCardValue to set
     */
    public void setTotalCardValue(int totalCardValue) {
        this.totalCardValue = totalCardValue;
    }
    public void play(){
//        DeckOfCards deck = new DeckOfCards();
        Card aCard;// = new Card();
//        DeckOfCards deck = new DeckOfCards();
        aCard = DeckOfCards.dealTopCard();
        addCard(aCard);
//        deck.removeCard(0);
    }
    
         public void printCardsAtHandWithFirstHidden(boolean hideFirstCard){
        //Loops through all cards at hand
//        boolean firstCard = hideFirstCard;

        if(hideFirstCard){
           
            Card aCard = new Card();
            for(int i = 0; i < this.dealerCards.size(); i++){
                if(i==0){
                    System.out.println("\t[hidden]");
                }
                else{
                    aCard = dealerCards.get(i);
                    System.out.println("\t" + aCard.toString());
                }
            }
        }
        else{
//            Card aCard = new Card();
            for (Card dealerCard : this.dealerCards) {
                System.out.println("\t" + dealerCard.toString());
            }
        }
        
    }
    public void clearPlayerCardsDeck(){
        dealerCards.clear();
    }
}
