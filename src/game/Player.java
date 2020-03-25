/*
 * Shankar Ghimire
 * StudentId:991585190
 * Course: ...............
 */
package game;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Player extends project.Player {

    private double playerMoney;
    private double playerBetMoney;
    private ArrayList<Card> playerCards;
    private int totalCardValue;
    DecimalFormat decimalFormat = new DecimalFormat("$#,##0.0");
    private enum status {
        PLAYING, WITHDRAW
    }
    private status playerStatus;

    public Player(String playerID, double playerMoney) {
        super(playerID);
        this.playerMoney = playerMoney;
        playerCards = new ArrayList<>();
    }

    @Override
    public void play() {
//        playerStatus = status.PLAYING;
//        DeckOfCards deck = new DeckOfCards();
//        this.addCard(DeckOfCards.dealTopCard());
//        if(playerMoney <= 0){
//            throw new Exception("You dont have sufficient funds to play.");
//        }
//        System.out.println("You have " + decimalFormat.format(getPlayerMoney()) + 
//                ", how much do you want to bet ? ");
//        playerBetMoney = 
        Card aCard  = DeckOfCards.dealTopCard();
        addCard(aCard);
//        deck.removeCard(0);

    }
    
    //private method to add a card to playerCardDeck
    public void addCard(Card currentCard) {
        this.playerCards.add(currentCard);
    }

    public void quit() {
        playerStatus = status.WITHDRAW;
    }

    /**
     * @return the playerMoney
     */
    public double getPlayerMoney() {
        return playerMoney;
    }

    /**
     * @param playerMoney the playerMoney to set
     */
    public void setPlayerMoney(double playerMoney) {
        this.playerMoney = playerMoney;
    }

    /**
     * @return the playerCards
     */
    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    /**
     * @param playerCards the playerCards to set
     */
    public void setPlayerCards(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }

    /**
     * @return the totalCardValue
     */
    public int getTotalCardValue() {
//        DeckOfCards deck = new DeckOfCards();
        totalCardValue = DeckOfCards.calculateCardValues(playerCards);
        return totalCardValue;
    }

    /**
     * @param totalCardValue the totalCardValue to set
     */
    public void setTotalCardValue(int totalCardValue) {
        this.totalCardValue = totalCardValue;
    }

    //method to print players card deck at hand
    public void printCardsAtHand() {
        //Loops through all cards at hand
        for (Card aCard : playerCards) {
            System.out.println("\t" + aCard.toString());
        }

    }

    //method to calculate total cards value
//     public int getTotalCardValue(){
//         int totalValue = 0;
//         DeckOfCards deck = new DeckOfCards();
//         totalValue = deck.calculateCardValues(playerCards);
//         return totalValue;
//     }

    /**
     * @return the playerBetMoney
     */
    public double getPlayerBetMoney() {
        return playerBetMoney;
    }

    /**
     * @param playerBetMoney the playerBetMoney to set
     */
    public void setPlayerBetMoney(double playerBetMoney) {
        this.playerBetMoney = playerBetMoney;
    }
    public void loadFunds(double fund){
        this.playerMoney += fund;
    }
    public void clearPlayerCardsDeck(){
        playerCards.clear();
    }
    
    //method up update player's money after win or losing game
    //if palyer wins, amount will be +ve value
    //if player loses, amount will be -ve value
    public void updatePlayerMoney(double amount){
        this.playerMoney += amount;
    }
    
}
