/*
 * Shankar Ghimire
 * StudentId:991585190
 * Course: ...............
 */
package game;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Main {

    public static void main(String[] args) {
        //Scanner object to take input from the user during runtime
        Scanner input = new Scanner(System.in);
        
        //DecimalFormat object to format value in currency format
        DecimalFormat decimalFormat = new DecimalFormat("$#,##0.0");
        
        //object to hold dealer info
        Dealer dealer = new Dealer();
        
        //Codes to input player id and initial money to deposit
        System.out.print("Enter player ID(String or Numeric value ) :  ");
        String playerId = input.next();
        System.out.print("Enter initial deposit money (only numeric value ) : ");
        double depostiMoney = input.nextDouble();
        System.out.println("------------------------------------");
        System.out.println("");
        //Player object to hold player information
        Player player = new Player(playerId, depostiMoney);

        //prints welcome message
        System.out.println("Welcome to BlackJack!");
        System.out.println("-------------------------------");

        //print player's information
        System.out.println("Player's Information: ");
        System.out.println("Player Id is : " + player.getPlayerID());
        System.out.println(player.getPlayerID() + " has " + 
                decimalFormat.format(player.getPlayerMoney()));
        System.out.println("------------------------------------------");
        
        //Static method calls to create full deck of cards
        DeckOfCards.createFullDeck();
        
        //static method calls to shuffle the cards in the deck
        DeckOfCards.shuffle();
        
        
        //variable that determines to repeat next game or not
        //if set to true, then next game will begin otherwise game ends
        boolean nextGame = true;
        
        //variable to know the status of current game whether it is over or going on
        //true denotes the game is over and ready for new game to start
        //false denotes the game is still going on       
        boolean gameResult = false;
        
        //variable that hold player bust or not in the game
        boolean playerBust = false;
        
        //variable that holds dealer bust or not in the game 
        boolean dealerBust = false;
        
        //variable that holds how many times the card has been dealed to player
        int numberOfDealingCycles = 1;
        
        //loop to begin the game and also to repeat the game round
        while (nextGame) {
            System.out.println("--------------------------------------------");
            System.out.println("Beginning New Game!");
            System.out.println("Dealing...");

            //code to Check the player has sufficient fund or not
            if (player.getPlayerMoney() <= 0) {
                System.out.println("You dont have sufficient funds to play game.");
                System.out.println("Do you want to load fund "
                        + "in your account ? (Y/N) ? ");
                char loadFund = input.next().charAt(0);
                if (loadFund == 'Y' || loadFund == 'y') {
                    System.out.println("Please, enter the amount "
                            + "you want to load : ");
                    double loadAmount = input.nextDouble();
                    player.loadFunds(loadAmount);
                    System.out.println("Now, player has " + 
                            decimalFormat.format(player.getPlayerMoney()));
                } else {
                    break;
                }
            } else {
                System.out.print("How much would you want to bet"
                        + "(only numeric value) ?  ");
                double betMoney = input.nextDouble();
                System.out.println("");
                while (betMoney > player.getPlayerMoney()) {
                    System.out.println("You canot bet more money than"
                            + " you have at present.");
                    System.out.println("You have only " +
                            decimalFormat.format(player.getPlayerMoney()));
                    System.out.println("Please, enter the valid bet amount again. ");
                    betMoney = input.nextDouble();
                }
                player.setPlayerBetMoney(betMoney);
            }
            
            //Code to deal card to dealer
            dealer.play();
            //to test remaining cards
//            System.out.println("-------------------");
//            DeckOfCards.printAllCards();
//            System.out.println("Total Card in Playing Card deck now : " +
//                  DeckOfCards.countTotalCards());
//            playingDeck.removeCard(0);
           

            //code to deal card to player
//            System.out.println("Player's turn : ");
            player.play();
            numberOfDealingCycles++;//counts how many times card has been dealed
            //to test remaining cards
//            System.out.println("-------------------");
//            DeckOfCards.printAllCards();
//            System.out.println("Total Card in Playing Card deck now : " + DeckOfCards.countTotalCards());
//            player.addCard(playingDeck.getTopCard());
//            playingDeck.removeCard(0);
            //deal second cycle of cards to each: dealer and player
            dealer.play();
            player.play();

            //print dealer's cards at hand
            System.out.println("Dealer's Card information after initial dealing Cycles  : ");
            dealer.printCardsAtHandWithFirstHidden(true);

            //print players cards at hand
            System.out.println("Player's Cards at hand after initial dealing Cycles  : ");
            player.printCardsAtHand();
            System.out.println("\tTotal card value at player's hand is : " + player.getTotalCardValue());
            numberOfDealingCycles++;

            //loop to repeat after dealing 2, 2 card to each- dealer and player
            //variable that hold values whether further card deal is required or not
            //if set ture, further card deal is required otherwise not
            boolean nextDealingCycle = true;
            
            //code to check player gets 21 or not
            if (player.getTotalCardValue() == 21) {
                System.out.println("Congratulation! Player wins!");
                double currentMoney = player.getPlayerMoney() +
                        player.getPlayerBetMoney();
                player.setPlayerMoney(currentMoney);
                
                nextDealingCycle = false;//indicates next deal is not required
                gameResult = true;//indicates that game is over. ready for new game
            }
            //loop that ask for the 3rd or more round of card cycles
            while (nextDealingCycle) {
                System.out.println("Options to select: ");
                System.out.println("\t1.Hit\n\t2.Stand");
                System.out.print("Please, select an option : ");
                int nextDeal = input.nextInt();
                switch (nextDeal) {
                    case 1:

                        player.play();
                        //print players cards at hand
                        System.out.println("Player's Cards at hand after dealing of"
                                + " Round " + (numberOfDealingCycles) + ": ");
                        player.printCardsAtHand();
                        System.out.println("\tTotal card value at player's"
                                + " hand is : " + player.getTotalCardValue());
                        numberOfDealingCycles++;
                        //code to check player bust or not after 3rd and more cards
                        if (player.getTotalCardValue() > 21) {
                            System.out.println("Player Bust!");
                            System.out.println("Dealer Wins!");
                            double currentMoney = 
                                player.getPlayerMoney() - player.getPlayerBetMoney();
                            player.setPlayerMoney(currentMoney);
                            System.out.println("Now, player has " +
                                    decimalFormat.format(player.getPlayerMoney()));
                            playerBust = true;
                            nextDealingCycle = false;
                            gameResult = true;
                            break;
                        } else {
                            nextDealingCycle = true;
                        }
                        break;
                    case 2:
                        System.out.println("Player wants no morre cards!");
                        System.out.println("Player is in Stand position");
                        System.out.println("");
                        System.out.println("----------------------------------");
                        System.out.println("Now, Dealer's turn to deal cards: ");
                        nextDealingCycle = false;
                        playerBust = false;
                        break;
                    default:
                        System.out.println("Invalid response!");
                        break;
                }
            }

            if (!playerBust) {
                //Code to reveal dealr's first(all) cards
                System.out.println("Dealer's Card information  : ");
                dealer.printCardsAtHandWithFirstHidden(false);
                System.out.println("\tTotal card value at dealer's hand is : "
                        + dealer.getTotalCardValue());

                if (dealer.getTotalCardValue() == 21) {
                    System.out.println("Dealer wins!");
                    double playerMoney = 
                            player.getPlayerBetMoney() - player.getPlayerBetMoney();
                    player.setPlayerMoney(playerMoney);
                    gameResult = true;
                } else if (dealer.getTotalCardValue() > player.getTotalCardValue()) {
                    System.out.println("Dealer wins!");
                    double playerMoney =
                            player.getPlayerBetMoney() - player.getPlayerBetMoney();
                    player.setPlayerMoney(playerMoney);
                    System.out.println("Now, Player has " +
                            decimalFormat.format(player.getPlayerMoney()));
                    gameResult = true;
                } else {
                    //Code to check the game result and deal next card
                    //to the dealer or not
                    nextDealingCycle = true;
                    while ((dealer.getTotalCardValue() < 17 ||
                            dealer.getTotalCardValue() < player.getTotalCardValue())
                            && nextDealingCycle == true) {
                        dealer.play();
                        System.out.println("Dealer's Card information  : ");
                        dealer.printCardsAtHandWithFirstHidden(false);
                        System.out.println("\tTotal card value at dealer's "
                                + "hand is : " + dealer.getTotalCardValue());

                        if (dealer.getTotalCardValue() > 21) {
                            System.out.println("Dealer Bust!");
                            double playerMoney =
                              player.getPlayerMoney() + player.getPlayerBetMoney();
                            player.setPlayerMoney(playerMoney);
                            System.out.println("Player Wins!");
                            System.out.println("Now, Player has " +
                                    decimalFormat.format(player.getPlayerMoney()));
                            gameResult = true;
                            nextDealingCycle = false;
                            break;
                        } else if (dealer.getTotalCardValue() > player.getTotalCardValue()) {
                            System.out.println("Dealer wins!");
                            double currentMoney = 
                                    player.getPlayerMoney() - player.getPlayerBetMoney();
                            player.setPlayerMoney(currentMoney);
                            System.out.println("Now, Player has " +
                                    decimalFormat.format(player.getPlayerMoney()));
                            nextDealingCycle = false;
                            gameResult = true;
                            break;
                        } else if (dealer.getTotalCardValue() == player.getTotalCardValue()) {
                            System.out.println("Game push!");
                            gameResult = true;
                            nextDealingCycle = false;
                            break;
                        } else {
                            nextDealingCycle = true;
                        }

                    }
                }
            }
            //Code to ask to repeat next round of Game or not
            if (gameResult) {
                System.out.println("----------------------------------");
                System.out.print("Do you want to play next round of game (Y/N) ?  :  ");
                char next = input.next().charAt(0);
               
                System.out.println("");
                if (next == 'Y' || next == 'y') {
                    DeckOfCards.createFullDeck();
                    DeckOfCards.shuffle();
                    player.clearPlayerCardsDeck();
                    dealer.clearPlayerCardsDeck();
                    nextGame = true;
                    numberOfDealingCycles = 0;
                } else {
                    System.out.println("Hey, " + player.getPlayerID() +", you have " + 
                            decimalFormat.format(player.getPlayerMoney()) + 
                            " after playing game.");

                    nextGame = false;
                }
                gameResult = false;
                //}  
            }

        }

    }
}
