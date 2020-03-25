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
    //Scanner object to take input from the user during runtime
    private static Scanner input = new Scanner(System.in);

    //DecimalFormat object to format value in currency format
    private static DecimalFormat decimalFormat = new DecimalFormat("$#,##0.0");

    //object to hold dealer info
    private static Dealer dealer = new Dealer();
    
    private static Player player;

    //variable that determines to repeat next game or not
    //if set to true, then next game will begin otherwise game ends
    static boolean nextGame = true;

    //variable to know the status of current game whether it is over or going on
    //true denotes the game is over and ready for new game to start
    //false denotes the game is still going on       
    static boolean gameResult = false;

    //variable that hold player bust or not in the game
    static boolean playerBust = false;

    //variable that holds dealer bust or not in the game 
    static boolean dealerBust = false;
    
    //variable that holds value if player fold hands/surrenders game or not
    static boolean  isFoldHand = false;
    
    //variable that holds how many times the card has been dealed to player
    static int numberOfDealingCycles = 1;
    //variable that hold values whether further card deal is required or not
    //if set ture, further card deal is required otherwise not
    static boolean nextDealingCycle = true;
    private static void initializeClassVariables(){
        nextGame= true;
        gameResult = false;
        playerBust = false;
        dealerBust = false;
        isFoldHand = false;
        numberOfDealingCycles = 1;
        nextDealingCycle = true;
    }
    //method to initialize Player object
    private static void addPlayer(){
       //Codes to input player id and initial money to deposit
        System.out.print("Enter player ID :  ");
        String playerId = input.next();
        System.out.print("Enter initial deposit money : $ ");
        //method calls to input valid value for input amount
        double depostiMoney = inputAmount();
        System.out.println("------------------------------------");
        System.out.println("");
        //Player object to hold player information
        player = new Player(playerId, depostiMoney); 
    }
    
    //method to print Player's information
    private static void printPlayerInformation() {
        //print player's information
        System.out.println("Player's Information: ");
        System.out.println("Player Id is : " + player.getPlayerID());
        System.out.println(player.getPlayerID() + " has "
                + decimalFormat.format(player.getPlayerMoney()));
        System.out.println("------------------------------------------");
    }
    //method to input amount for player
    private static double inputAmount() {
        double inputVal = 0;
        boolean isInputValid = true;
        do {
            try {
                inputVal = input.nextDouble();
                isInputValid = true;
                //System.out.println("");
            }//end of try block
            catch (Exception ex) {
                isInputValid = false;
                System.out.print("Invalid value! Pleae, enter only numeric value again. ");
                input.nextLine();
            }
        } while (!isInputValid);
        return inputVal;
    }
    
    //method that ask the player to input bet amount 
    private static void inputBetAmount() {
        //loop that ask every player for bet amount as well as the bet amount
        //is sufficient according to player's balance in his/her wallert or not
        //for (Player player : playerList) {
            //loop that makes sure that only valid numerical value is entered
            //for bet amount
            double betAmount = 0;
            //betAmount = inputAmount();
            //String inputValue;
            boolean isInputValid = false;
            do {
                System.out.print("Hey, " + player.getPlayerID() + " Input bet amount : $ ");
                betAmount = inputAmount(); 
                isInputValid = true;
                //isInputValid = true;
                //if the input amount is valid, check for the amount is sufficient or not
                if ( betAmount > player.getPlayerMoney()) {
                    isInputValid = false;
                    //method calls to input the amount to load in the players wallet
                    inputAmountForLoadingInWallet(player, betAmount);                   
                }
            } while (!isInputValid); 
            //calls setter method to assign bet amount
            player.setPlayerBetMoney(betAmount);
        //}//end of advanced for loop
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
                System.out.println("Input the amount to load in you wallet : ");
                double newLoadAmount = inputAmount();
                //method to load funds in player's wallet                               
                player.loadFunds(newLoadAmount);
                System.out.println("Now, " + player.getPlayerID() + " has " + decimalFormat.format( player.getPlayerMoney()));
            } else {
                //when the player refuses to add fund, player gets 
                //removed from the playerList
                String removedPlayerId = player.getPlayerID();
                //playerList.remove(player);
                //System.out.println(removedPlayerId + ", you have been removed from the player list as you "
                 //       + "you dont have sufficient fund and you refused to load fund in you wallet.!");
                 System.out.println("Hey, " + player.getPlayerID() + ", you don't have sufficient fund to play the game.");
                System.out.println("Game is over!!!");
            }
        }        
    }
    
    //method to deal initial cards 
    private static void dealInitialCards() {
        //Code to deal card to dealer
        dealer.play();
        //to test remaining cards
//            DeckOfCards.printAllCards();
        //code to deal card to player
//            System.out.println("Player's turn : ");
        player.play();
        //to test remaining cards
//            DeckOfCards.printAllCards();
        dealer.play();
        player.play();

        //print dealer's cards at hand
        System.out.println("Dealer's Card information after initial dealing Cycles  : ");
        dealer.printCardsAtHandWithFirstHidden(true);

        //print players cards at hand
        System.out.println("Player's Cards at hand after initial dealing Cycles  : ");
        player.printCardsAtHand();
        System.out.println("\tTotal card value at player's hand is : " + player.getTotalCardValue());
        //numberOfDealingCycles++;   
    }

    //method to deal other cards to player, after dealing initial rounds of cards
    private static void dealOtherCardsToPlayer() {
        //loop that ask for the 3rd or more round of card cycles
        while (nextDealingCycle) {
            System.out.println("Options to select: ");
            System.out.println("\t1.Hit\n\t2.Stand");
            System.out.print("Please, select an option : ");
            int nextDeal = input.nextInt();
            switch (nextDeal) {               
                case 1:
                    //option to hit cards
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
                        //double currentMoney = player.getPlayerMoney() - player.getPlayerBetMoney();
                        //player.setPlayerMoney(currentMoney);
                        double playerLoseAmount = (-1) * player.getPlayerBetMoney();
                        player.updatePlayerMoney(playerLoseAmount);
                        System.out.println("Now, player has "
                                + decimalFormat.format(player.getPlayerMoney()));
                        playerBust = true;
                        nextDealingCycle = false;
                        gameResult = true;
                        break;
                    } else {
                        nextDealingCycle = true;
                    }
                    break;
                case 2:
                    //option to Stay
                    System.out.println("Player wants no more cards!");
                    System.out.println("Player is in Stand position");
                    System.out.println("");
                    //System.out.println("----------------------------------");
                    //System.out.println("Now, Dealer's turn to deal cards: ");
                    nextDealingCycle = false;
                    playerBust = false;
                    break;
                default:
                    System.out.println("Invalid response!");
                    break;
            }//end of switch case offering options to player
        }//end of loop that deals card to the player till his/her demand
        
        //if statment that checs if the player has already bust or not
        isFoldHand = false;
        if(!playerBust){
            //code to print cards of dealer's hand with hiding first card
            System.out.println("Dealer's Card information  : ");
            dealer.printCardsAtHandWithFirstHidden(true);
            //System.out.println("\tTotal card value at dealer's hand is : "
            // + dealer.getTotalCardValue());

            //method calls to offer player to fold hand/surrender game
            isFoldHand = foldHand();
        }
    }//end of the method
 
    //method to deal other cards to dealer, after showing initial cards 
    private static void dealOtherCardsToDealer(){
       
        //this if statement runs when the player does not fold hand/surrender game
        //if(!isFoldHand && !playerBust ){          
            //Code to reveal dealr's all cards including first card
            System.out.println("Dealer's Card information  : ");
            dealer.printCardsAtHandWithFirstHidden(false);
            System.out.println("\tTotal card value at dealer's hand is : "
                    + dealer.getTotalCardValue());
            System.out.println("----------------------------------");
            //System.out.println("Now, Dealer's turn to deal cards: ");
            //System.out.println("");
            
            //this if else if block compares the card value after players all cards are dealt and dealers two initials cards            
            if (dealer.getTotalCardValue() == 21) {
                System.out.println("Dealer wins!");
                //double playerMoney = player.getPlayerBetMoney() - player.getPlayerBetMoney();
                //player.setPlayerMoney(playerMoney);
                double playerLoseAmount = -1 * player.getPlayerBetMoney();
                player.updatePlayerMoney(playerLoseAmount);
                gameResult = true;
            } else if (dealer.getTotalCardValue() > player.getTotalCardValue()) {
                System.out.println("Dealer wins!");
                //double playerMoney = player.getPlayerBetMoney() - player.getPlayerBetMoney();
                //player.setPlayerMoney(playerMoney);
                double playerLoseAmount = -1 * player.getPlayerBetMoney();
                player.updatePlayerMoney(playerLoseAmount);
                System.out.println("Now, Player has "
                        + decimalFormat.format(player.getPlayerMoney()));
                gameResult = true;
            } else {
                // Loop to check the game result and deal next card
                //to the dealer or not
                System.out.println("Now, Dealer's turn to deal cards: ");
                System.out.println("");
                nextDealingCycle = true;
                while ((dealer.getTotalCardValue() < 17
                        || dealer.getTotalCardValue() < player.getTotalCardValue())
                        && nextDealingCycle == true) {
                    dealer.play();
                    System.out.println("Dealer's Card information  : ");
                    dealer.printCardsAtHandWithFirstHidden(false);
                    System.out.println("\tTotal card value at dealer's "
                            + "hand is : " + dealer.getTotalCardValue());

                    if (dealer.getTotalCardValue() > 21) {
                        System.out.println("Dealer Bust!");
//                        double playerMoney = player.getPlayerMoney() + player.getPlayerBetMoney();
//                        player.setPlayerMoney(playerMoney);
                        double playerWinAmount = player.getPlayerBetMoney();
                        player.updatePlayerMoney(playerWinAmount);
                        System.out.println("Player Wins!");
                        System.out.println("Now, Player has "
                                + decimalFormat.format(player.getPlayerMoney()));
                        gameResult = true;
                        nextDealingCycle = false;
                        break;
                    } else if (dealer.getTotalCardValue() > player.getTotalCardValue()) {
                        System.out.println("Dealer wins!");
                        //double currentMoney = player.getPlayerMoney() - player.getPlayerBetMoney();
                        //player.setPlayerMoney(currentMoney);
                        double playerLoseAmount = -1 * player.getPlayerBetMoney();
                        player.updatePlayerMoney(playerLoseAmount);
                        System.out.println("Now, Player has "
                                + decimalFormat.format(player.getPlayerMoney()));
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
                }//end of while loop used to deal further cards to dealer
            }//end of if statement that check the card value of player's hand and delaer's hand for deciding game result
        //}//end of if block that check playerd does not fold hand and player does not bust 
    }

    //method to check player has black jack or not after two initia cards
    private static void checkForBlackJack(){
        if (player.getTotalCardValue() == 21) {
            System.out.println("Congratulation! " + player.getPlayerID() + ", you have BlackJack!\n"  +
                    "You win 3:2 amount.");
            //if player has black jack, he/she recieves 3:2 ratio of bet amount
            //double currentMoney = player.getPlayerMoney()+ player.getPlayerBetMoney() * 1.5;
            //player.setPlayerMoney(currentMoney);
            double playerWinMoney = player.getPlayerMoney()+ player.getPlayerBetMoney() * 1.5;
            player.updatePlayerMoney(playerWinMoney);
            nextDealingCycle = false;//indicates next deal is not required
            gameResult = true;//indicates that game is over. ready for new game
        }    
    }
    
    //method to confirm player want to fold hand (surrender game) or not
    private static boolean foldHand(){
        boolean isResponseValid = false;
        char choice = 'Y';
        System.out.print("Hey, " + player.getPlayerID() + ", do you want to fold hand/surrenger game, please ?  (Y/N)  : ");
        do{
           
            choice = input.next().toUpperCase().charAt(0);
            if(choice == 'Y' || choice == 'N'){
                isResponseValid = true;
            }
        }while(!isResponseValid);
        if(choice == 'Y'){
            //player loses only half of his/her bet amount
            //code to update player's wallet
            gameResult = true;
            double playerLoseAmount = (-1) * player.getPlayerBetMoney()/2;
            player.updatePlayerMoney(playerLoseAmount);
            System.out.println("Hey, " + player.getPlayerID() + ", you have now " +
                decimalFormat.format(  player.getPlayerMoney()) + " in your wallet.");
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String[] args) {
        //method call to initialize all class variables
        initializeClassVariables();
        //method call to add player in game
        addPlayer();
        
        //prints welcome message
        System.out.println("Welcome to BlackJack!");
        System.out.println("-------------------------------");

        //method call to print player information
        printPlayerInformation();
        
        //Static method calls to create full deck of cards
        DeckOfCards.createFullDeck();
        
        //static method calls to shuffle the cards in the deck
        DeckOfCards.shuffle();
          
        //loop to begin the game and also to repeat the game round
        while (nextGame) {
            System.out.println("--------------------------------------------");
            System.out.println("Beginning New Game!");
            System.out.println("Dealing...");

            //method call to ask player to input bet amount
            inputBetAmount();
            
            //method call to deal initial cards to player and dealer
            dealInitialCards();
       
            //code to check player gets BlackJack i.e.  21 or not
           checkForBlackJack();
           
           //method calls to deal third and other rounds of cards to player
           dealOtherCardsToPlayer();
           
           //this if statement checks if the player does not fold hand/surrender game & does not bust
           if(!isFoldHand && !playerBust )
           {
                //method calls to deal other rounds of cards to dealer           
                dealOtherCardsToDealer();  
           }
           
            //Code to ask to repeat next round of Game or not
            if (gameResult) {
                System.out.println("----------------------------------");
                System.out.print("Hey, " + player.getPlayerID() + ", do you want to play next round of game,please?  (Y/N)  :  ");
                char next = input.next().charAt(0);
               
                System.out.println("");
                if (next == 'Y' || next == 'y') {
                    DeckOfCards.createFullDeck();
                    DeckOfCards.shuffle();
                    player.clearPlayerCardsDeck();
                    dealer.clearPlayerCardsDeck();
                    nextGame = true;
                    numberOfDealingCycles = 0;
                    initializeClassVariables();
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
