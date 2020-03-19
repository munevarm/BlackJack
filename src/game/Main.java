/*
 * Shankar Ghimire
 * StudentId:991585190
 * Course: ...............
 */
package game;

import static game.Main.decimalFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Main {
        //Scanner object to take input from the user during runtime
        static Scanner input = new Scanner(System.in);

        //DecimalFormat object to format value in currency format
        static DecimalFormat decimalFormat = new DecimalFormat("$#,##0.0");

        //object to hold dealer info
        static Dealer dealer = new Dealer();

        //variable to count the number of dealing
        static int roundOfDealing = 0;

        //code to create player object
        static ArrayList<Player> playerList = new ArrayList<>();
        //variable to count number of players 
        //Max number of player to play at a time is 3
        static final int MAXPLAYER = 2;
        static int countPlayer = 0;
    
    //method to add player for the game
    private static void addPlayer(){
        char nextPlayer = 'Y';
        //loop used to add player object into ArrayList object of player
        do {
            //Codes to input player id and initial money to deposit
            System.out.print("Enter player ID(String or Numeric value ) :  ");
            String playerId = input.next();
            System.out.print("Enter initial deposit money (only numeric value ) : ");
            double depostiMoney = inputAmount();
            System.out.println("------------------------------------");
            System.out.println("");
            //Player object to hold player information
            Player player = new Player(playerId, depostiMoney);
            playerList.add(player);
            countPlayer++;
            if (countPlayer >= MAXPLAYER) {
                System.out.println("You can not add more than " + MAXPLAYER + "!");
                break;
            }
            System.out.println("Do you want to add next player ? \n"
                    + "Press 'Y' to add or any other keys to start play : ");
            nextPlayer = input.next().charAt(0);

        } while (nextPlayer == 'Y' || nextPlayer == 'y');

    }
    //method to input amount for player
    private static double inputAmount() {
        double inputVal = 0;
        boolean isInputValid = true;
        do {
            try {
                inputVal = input.nextDouble();
                System.out.println("");
            }//end of try block
            catch (Exception ex) {
                isInputValid = false;
                System.out.print("Invalid value! Pleae, enter only numeric value again. ");
                input.nextLine();
            }
        } while (!isInputValid);
        return inputVal;
    }
    //method to print player's information before game start
    private static void printPlayerInformation(){
       //print player's information
        System.out.println("Player's Information: ");
        for (Player player : playerList) {
            System.out.println("Player Id is : " + player.getPlayerID());
            System.out.println(player.getPlayerID() + " has "
                    + decimalFormat.format(player.getPlayerMoney()));
            System.out.println("------------------------------------------");
        } 
    }
    
    //method that ask the player to input bet amount 
    private static void inputBetAmount() {
        //loop that ask every player for bet amount as well as the bet amount
        //is sufficient according to player's balance in his/her wallert or not
        for (Player player : playerList) {
            //loop that makes sure that only valid numerical value is entered
            //for bet amount
            double betAmount = 0;
            //betAmount = inputAmount();
            //String inputValue;
            boolean isInputValid = false;
            do {
                betAmount = inputAmount(); 
                isInputValid = true;
                //isInputValid = true;
                //if the input amount is valid, check for the amount is sufficient or not
                if ( betAmount > player.getPlayerMoney()) {
                    //method calls to input the amount to load in the players wallet
                    inputAmountForLoadingInWallet(player, betAmount);
                    isInputValid = true;
                }
            } while (!isInputValid); 
            //calls setter method to assign bet amount
            player.setPlayerBetMoney(betAmount);
        }//end of advanced for loop
    }//end of method 
    
    //method that is used to load amount in player's wallet
    private static void inputAmountForLoadingInWallet(Player player, double betAmount) {
        //code to check the player has sufficient fund to bet or not
        //if not, then ask the user to load the fund in the wallet
        if (betAmount > player.getPlayerMoney()) {
            System.out.println("Hey, " + player.getPlayerID() + ", you don't have sufficent fund to bet "
                    + decimalFormat.format(betAmount));

            System.out.println("You have only " + decimalFormat.format(player.getPlayerMoney()));

            //System.out.println("Player ID: " + player.getPlayerID());
            System.out.println("\tDo you want to load fund "
                    + "in your wallet? \n"
                    + "Press 'Y' to load fund or any other key to cancel.");
            Character loadFund = input.next().charAt(0);
            if (loadFund == 'Y' || loadFund == 'y') {
                //calls method inputAmount() to input valid value
                double newLoadAmount = inputAmount();
                //method to load funds in player's wallet                               
                player.loadFunds(newLoadAmount);
            } else {
                //when the player refuses to add fund, player gets 
                //removed from the playerList
                String removedPlayerId = player.getPlayerID();
                playerList.remove(player);
                System.out.println(removedPlayerId + ", you have been removed from the player list as you "
                        + "you dont have sufficient fund and you refused to load fund in you wallet.!");
            }
        }        
    }
    public static void main(String[] args) {
        
        //method calls to add player in game
        addPlayer();
            
        //prints welcome message
        System.out.println("********************");
        System.out.println("Welcome to BlackJack!");
        System.out.println("-------------------------------");

        //method calls that print player's information
        printPlayerInformation();
   
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
            
            //method calls that is used to input bet amount 
            inputBetAmount();
                       
            //Code to deal card to dealer
//            dealer.play();
//            //to test remaining cards
////            System.out.println("-------------------");
////            DeckOfCards.printAllCards();
////            System.out.println("Total Card in Playing Card deck now : " +
////                  DeckOfCards.countTotalCards());
////            playingDeck.removeCard(0);
//
//            //code to deal card to player
////            System.out.println("Player's turn : ");
//            for(Player player: playerList){
//                player.play();
//            }
//            
//            numberOfDealingCycles++;//counts how many times card has been dealed
//            //to test remaining cards
////            System.out.println("-------------------");
////            DeckOfCards.printAllCards();
////            System.out.println("Total Card in Playing Card deck now : " + DeckOfCards.countTotalCards());
////            player.addCard(playingDeck.getTopCard());
////            playingDeck.removeCard(0);
//            //deal second cycle of cards to each: dealer and player
//            dealer.play();
//            player.play();
//
//            //print dealer's cards at hand
//            System.out.println("Dealer's Card information after initial dealing Cycles  : ");
//            dealer.printCardsAtHandWithFirstHidden(true);
//
//            //print players cards at hand
//            System.out.println("Player's Cards at hand after initial dealing Cycles  : ");
//            player.printCardsAtHand();
//            System.out.println("\tTotal card value at player's hand is : " + player.getTotalCardValue());
//            numberOfDealingCycles++;
//
//            //loop to repeat after dealing 2, 2 card to each- dealer and player
//            //variable that hold values whether further card deal is required or not
//            //if set ture, further card deal is required otherwise not
//            boolean nextDealingCycle = true;
//
//            //code to check player gets 21 or not
//            if (player.getTotalCardValue() == 21) {
//                System.out.println("Congratulation! Player wins!");
//                double currentMoney = player.getPlayerMoney()
//                        + player.getPlayerBetMoney();
//                player.setPlayerMoney(currentMoney);
//
//                nextDealingCycle = false;//indicates next deal is not required
//                gameResult = true;//indicates that game is over. ready for new game
//            }
//            //loop that ask for the 3rd or more round of card cycles
//            while (nextDealingCycle) {
//                System.out.println("Options to select: ");
//                System.out.println("\t1.Hit\n\t2.Stand");
//                System.out.print("Please, select an option : ");
//                int nextDeal = input.nextInt();
//                switch (nextDeal) {
//                    case 1:
//
//                        player.play();
//                        //print players cards at hand
//                        System.out.println("Player's Cards at hand after dealing of"
//                                + " Round " + (numberOfDealingCycles) + ": ");
//                        player.printCardsAtHand();
//                        System.out.println("\tTotal card value at player's"
//                                + " hand is : " + player.getTotalCardValue());
//                        numberOfDealingCycles++;
//                        //code to check player bust or not after 3rd and more cards
//                        if (player.getTotalCardValue() > 21) {
//                            System.out.println("Player Bust!");
//                            System.out.println("Dealer Wins!");
//                            double currentMoney
//                                    = player.getPlayerMoney() - player.getPlayerBetMoney();
//                            player.setPlayerMoney(currentMoney);
//                            System.out.println("Now, player has "
//                                    + decimalFormat.format(player.getPlayerMoney()));
//                            playerBust = true;
//                            nextDealingCycle = false;
//                            gameResult = true;
//                            break;
//                        } else {
//                            nextDealingCycle = true;
//                        }
//                        break;
//                    case 2:
//                        System.out.println("Player wants no morre cards!");
//                        System.out.println("Player is in Stand position");
//                        System.out.println("");
//                        System.out.println("----------------------------------");
//                        System.out.println("Now, Dealer's turn to deal cards: ");
//                        nextDealingCycle = false;
//                        playerBust = false;
//                        break;
//                    default:
//                        System.out.println("Invalid response!");
//                        break;
//                }
//            }
//
//            if (!playerBust) {
//                //Code to reveal dealr's first(all) cards
//                System.out.println("Dealer's Card information  : ");
//                dealer.printCardsAtHandWithFirstHidden(false);
//                System.out.println("\tTotal card value at dealer's hand is : "
//                        + dealer.getTotalCardValue());
//
//                if (dealer.getTotalCardValue() == 21) {
//                    System.out.println("Dealer wins!");
//                    double playerMoney
//                            = player.getPlayerBetMoney() - player.getPlayerBetMoney();
//                    player.setPlayerMoney(playerMoney);
//                    gameResult = true;
//                } else if (dealer.getTotalCardValue() > player.getTotalCardValue()) {
//                    System.out.println("Dealer wins!");
//                    double playerMoney
//                            = player.getPlayerBetMoney() - player.getPlayerBetMoney();
//                    player.setPlayerMoney(playerMoney);
//                    System.out.println("Now, Player has "
//                            + decimalFormat.format(player.getPlayerMoney()));
//                    gameResult = true;
//                } else {
//                    //Code to check the game result and deal next card
//                    //to the dealer or not
//                    nextDealingCycle = true;
//                    while ((dealer.getTotalCardValue() < 17
//                            || dealer.getTotalCardValue() < player.getTotalCardValue())
//                            && nextDealingCycle == true) {
//                        dealer.play();
//                        System.out.println("Dealer's Card information  : ");
//                        dealer.printCardsAtHandWithFirstHidden(false);
//                        System.out.println("\tTotal card value at dealer's "
//                                + "hand is : " + dealer.getTotalCardValue());
//
//                        if (dealer.getTotalCardValue() > 21) {
//                            System.out.println("Dealer Bust!");
//                            double playerMoney
//                                    = player.getPlayerMoney() + player.getPlayerBetMoney();
//                            player.setPlayerMoney(playerMoney);
//                            System.out.println("Player Wins!");
//                            System.out.println("Now, Player has "
//                                    + decimalFormat.format(player.getPlayerMoney()));
//                            gameResult = true;
//                            nextDealingCycle = false;
//                            break;
//                        } else if (dealer.getTotalCardValue() > player.getTotalCardValue()) {
//                            System.out.println("Dealer wins!");
//                            double currentMoney
//                                    = player.getPlayerMoney() - player.getPlayerBetMoney();
//                            player.setPlayerMoney(currentMoney);
//                            System.out.println("Now, Player has "
//                                    + decimalFormat.format(player.getPlayerMoney()));
//                            nextDealingCycle = false;
//                            gameResult = true;
//                            break;
//                        } else if (dealer.getTotalCardValue() == player.getTotalCardValue()) {
//                            System.out.println("Game push!");
//                            gameResult = true;
//                            nextDealingCycle = false;
//                            break;
//                        } else {
//                            nextDealingCycle = true;
//                        }
//
//                    }
//                }
//            }
//            //Code to ask to repeat next round of Game or not
//            if (gameResult) {
//                System.out.println("----------------------------------");
//                System.out.print("Do you want to play next round of game (Y/N) ?  :  ");
//                char next = input.next().charAt(0);
//
//                System.out.println("");
//                if (next == 'Y' || next == 'y') {
//                    DeckOfCards.createFullDeck();
//                    DeckOfCards.shuffle();
//                    player.clearPlayerCardsDeck();
//                    dealer.clearPlayerCardsDeck();
//                    nextGame = true;
//                    numberOfDealingCycles = 0;
//                } else {
//                    System.out.println("Hey, " + player.getPlayerID() + ", you have "
//                            + decimalFormat.format(player.getPlayerMoney())
//                            + " after playing game.");
//
//                    nextGame = false;
//                }
//                gameResult = false;
//                //}  
//            }

        }//end of while loop

    }//end of main method
}
