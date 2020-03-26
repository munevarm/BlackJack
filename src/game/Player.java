/*
 * Shankar Ghimire
 * StudentId:991585190
 * Course: ...............
 */
package game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Player extends project.Player {

    private double playerMoney;
    private double playerBetMoney;
    private ArrayList<Card> playerCards;
    private int totalCardValue;
    private boolean playerBust;
    private boolean playerFoldHand;
    private GameResult gameResult;
    private Scanner input = new Scanner(System.in);
    DecimalFormat decimalFormat = new DecimalFormat("$#,##0.0");
    
    private enum status {
        PLAYING, WITHDRAW
    }
    private status playerStatus;
//    public Player(){
//        
//    }
    public Player(String playerID) {
        super(playerID);
        //this.playerMoney = playerMoney;
        playerCards = new ArrayList<>();
    }
    
    public Player(Player player){
       super(player.getPlayerID());
    }
    @Override
    public void play() {
        Card aCard  = DeckOfCards.dealTopCard();
        addCard(aCard);
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
    public void printCurrentHand() {
        //Loops through all cards at hand
        for (Card aCard : playerCards) {
            System.out.println("\t" + aCard.toString());
        }
        System.out.println("\tTotal card value at player's hand is : " + getTotalCardValue());
    }


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
    public void loadFunds(double loadAmount){
        this.playerMoney += loadAmount;
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

    /**
     * @return the playerBust
     */
    public boolean isPlayerBust() {
        return playerBust;
    }

    /**
     * @param playerBust the playerBust to set
     */
    public void setPlayerBust(boolean playerBust) {
        this.playerBust = playerBust;
    }

    /**
     * @return the playerFoldHand
     */
    public boolean isPlayerFoldHand() {
        return playerFoldHand;
    }

    /**
     * @param playerFoldHand the playerFoldHand to set
     */
    public void setPlayerFoldHand(boolean playerFoldHand) {
        this.playerFoldHand = playerFoldHand;
    }
    
     //method that ask the player to input bet amount 
    public boolean isBetAmountValid(double betAmount) {
                if (  this.getPlayerMoney() >= betAmount ) {
                    return true;                  
                }
                else{
                    return false;
                }
    }//end of method 

    /**
     * @return the gameResult
     */
    public GameResult getGameResult() {
        return gameResult;
    }

    /**
     * @param gameResult the gameResult to set
     */
    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }
        
}//end of the class
