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
public class Main {
    //Scanner object to take input from the user during runtime
    private static Scanner input = new Scanner(System.in);

    //DecimalFormat object to format value in currency format
    private static DecimalFormat decimalFormat = new DecimalFormat("$#,##0.0");

    //object to hold dealer info
    private static Dealer dealer = new Dealer();
    
    //object to hold player info
    private static Player player = new Player("");

    //ArrayList object to hold Player object
    ArrayList<Player> playerList = new ArrayList<>();
    
    //variable that determines to repeat next game or not
    //if set to true, then next game will begin otherwise game ends
    static boolean nextGame = true;

    //variable to know the status of current game whether it is over or going on
    //true denotes the game is over and ready for new game to start
    //false denotes the game is still going on       
    static boolean gameResult = false;

//    //variable that hold player bust or not in the game
//    static boolean playerBust = false;
//
//    //variable that holds dealer bust or not in the game 
//    static boolean dealerBust = false;
    
    //variable that holds value if player fold hands/surrenders game or not
    //static boolean  isFoldHand = false;
    
    //variable that holds how many times the card has been dealed to player
    static int numberOfDealingCycles = 1;
    //variable that hold values whether further card deal is required or not
    //if set ture, further card deal is required otherwise not
    static boolean nextDealingCycle = true;
    private static void initializeClassVariables(){
        nextGame= true;
        gameResult = false;
//        playerBust = false;
//        dealerBust = false;
//        isFoldHand = false;
        numberOfDealingCycles = 1;
        nextDealingCycle = true;
        player.setPlayerBust(false);
        player.setPlayerFoldHand(false);
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
    
    //method that is used to load amount in player's wallet
    private static void loadFundInDepositBox(Player currentPlayer) {
        System.out.print("Input the amount to load in you wallet : $ ");
        double newLoadAmount = inputAmount();
        //method to load funds in player's wallet                               
        currentPlayer.loadFunds(newLoadAmount);
        System.out.println("Now, " + currentPlayer.getPlayerID() + " has "
                + decimalFormat.format( currentPlayer.getPlayerMoney()));
        
    }
    
    //method to deal initial cards 
    private static void dealInitialCardsToPlayer(Player objPlayer) {
        //method call to deal card to dealer
        objPlayer.play();  
    }
    
     //method to deal initial cards 
    private static void dealInitialCardsToDeaer(Dealer objDealer) {
        //method call to deal card to dealer
        objDealer.play();   
    }
    
    //method to print current hand of player
//    private static void printCurrentPlayerHand(Player objPlayer){
//         //print players cards at hand
//        //System.out.println("Player's Cards at hand after initial dealing Cycles  : ");
//        player.printCurrentHand();
//        System.out.println("\tTotal card value at player's hand is : " + objPlayer.getTotalCardValue());
//        //numberOfDealingCycles++;  
//    }
    
    //method to print current hand of player
//    private static void printCurrentDealerHand(Dealer objDealer){
//         //print players cards at hand
//         //print dealer's cards at hand
//        //System.out.println("Dealer's Card information after initial dealing Cycles  : ");
//        objDealer.printCurrentHand(true);
//    }
    /*
    /*
    //testing method by dealing same card to player to test split hand feature
    private static void dealInitialCards() {
        //Code to deal card to dealer
        dealer.play();
        //to test remaining cards
//            DeckOfCards.printAllCards();
        //code to deal card to player
//            System.out.println("Player's turn : ");
        //player.play();
        Card firstCard = new Card(Suit.CLUB, FaceValue.TWO);
        player.addCard(firstCard);
        //to test remaining cards
//            DeckOfCards.printAllCards();
        dealer.play();
        
        //player.play();
        Card secondCard = new Card(Suit.DIAMOND, FaceValue.TWO);
        player.addCard(secondCard);
        //print dealer's cards at hand
        System.out.println("Dealer's Card information after initial dealing Cycles  : ");
        dealer.printCardsAtHandWithFirstHidden(true);

        //print players cards at hand
        System.out.println("Player's Cards at hand after initial dealing Cycles  : ");
        player.printCardsAtHand();
        System.out.println("\tTotal card value at player's hand is : " + player.getTotalCardValue());
        //numberOfDealingCycles++;   
    }
    */
    
    //method to deal other cards to player, after dealing initial rounds of cards
    private static void dealOtherCardsToPlayer(Player objPlayer) {
        //loop that ask for the 3rd or more round of card cycles
        while (nextDealingCycle) {
            System.out.println("Options to select: ");
            System.out.println("\t1.Hit\n\t2.Stand");
            System.out.print("Please, select an option : ");
            char nextDeal = input.next().charAt(0);
            //loop to validate input choice is valid or not
            while(nextDeal != '1' && nextDeal != '2'){
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Invalid, Choice!!!!!!!!");
                System.out.print("Please, input either 1 or 2 : ");
                nextDeal = input.next().charAt(0);
            }
            switch (nextDeal) {               
                case '1':
                    //option to hit cards
                    objPlayer.play();
                    //print players cards at hand
                    System.out.println("Player's Cards at hand after dealing of"
                            + " Round " + (numberOfDealingCycles) + ": ");
                    objPlayer.printCurrentHand();
                    System.out.println("\tTotal card value at player's"
                            + " hand is : " + objPlayer.getTotalCardValue());
                    numberOfDealingCycles++;
                    //code to check player bust or not after 3rd and more cards
                    if (objPlayer.getTotalCardValue() > 21) {
                        System.out.println("Player Bust!");
                        System.out.println("Dealer Wins!");
                        //double currentMoney = player.getPlayerMoney() - player.getPlayerBetMoney();
                        //player.setPlayerMoney(currentMoney);
                        double playerLoseAmount = (-1) * objPlayer.getPlayerBetMoney();
                        objPlayer.updatePlayerMoney(playerLoseAmount);
                        System.out.println("Now, player has "
                                + decimalFormat.format(objPlayer.getPlayerMoney()));
                        //playerBust = true;
                        objPlayer.setPlayerBust(true);
                        nextDealingCycle = false;
                        gameResult = true;
                        break;
                    } else {
                        nextDealingCycle = true;
                    }
                    break;
                case '2':
                    //option to Stay
                    System.out.println("Player wants no more cards!");
                    System.out.println("Player is in Stand position");
                    System.out.println("");
                    //System.out.println("----------------------------------");
                    //System.out.println("Now, Dealer's turn to deal cards: ");
                    nextDealingCycle = false;
                    //playerBust = false;
                    objPlayer.setPlayerBust(false);
                    break;
                default:
                    System.out.println("Invalid response!");
                    break;
            }//end of switch case offering options to player
        }//end of loop that deals card to the player till his/her demand
        
        //if statment that checs if the player has already bust or not
        //isFoldHand = false;
        //if(!playerBust){
        if(!objPlayer.isPlayerBust()){
            //code to print cards of dealer's hand with hiding first card
            System.out.println("Dealer's Card information  : ");
            dealer.printCurrentHand(true);
            //System.out.println("\tTotal card value at dealer's hand is : "
            // + dealer.getTotalCardValue());

            //method calls to offer player to fold hand/surrender game
            //isFoldHand = foldHand();
            foldHand(objPlayer);
        }
    }//end of the method
    
    //method to deal other cards to dealer, after showing initial cards 
    private static void dealOtherCardsToDealer(){
       
        //this if statement runs when the player does not fold hand/surrender game
        //if(!isFoldHand && !playerBust ){          
            //Code to reveal dealr's all cards including first card
            System.out.println("Dealer's Card information  : ");
            dealer.printCurrentHand(false);
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
                    dealer.printCurrentHand(false);
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
                        System.out.println("----------------------------");
                        System.out.println("He, " + player.getPlayerID() + ", you have " +
                             decimalFormat.format(player.getPlayerMoney()) + " in your wallet.");
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
    private static boolean checkForBlackJack(Player objPlayer){
        if (objPlayer.getTotalCardValue() == 21) {
            return true;            
        }  
        else{
            return false;
        }
    }
    
    //method to confirm player want to fold hand (surrender game) or not
    private static void foldHand(Player objPlayer){
        boolean isResponseValid = false;
        char choice = 'Y';
        System.out.print("Hey, " + objPlayer.getPlayerID() + ", do you want to fold hand/surrenger game, please ?  (Y/N)  : ");
        do{           
            choice = input.next().toUpperCase().charAt(0);
            if(choice == 'Y' || choice == 'N'){
                isResponseValid = true;
            }
        }while(!isResponseValid);
        if(choice == 'Y'){
            //player loses only half of his/her bet amount
            //code to update player's wallet
            objPlayer.setPlayerFoldHand(true);
            gameResult = true;
            //when player folds hand/surrender game, he/she loses only half of the bet amount
            double playerLoseAmount = (-1) *  ( objPlayer.getPlayerBetMoney()/2);
            objPlayer.updatePlayerMoney(playerLoseAmount);
            System.out.println("Hey, " + objPlayer.getPlayerID() + ", you have now " +
                decimalFormat.format(  objPlayer.getPlayerMoney()) + " in your wallet.");
            //return true;
        }
        else{
            //return false;
            objPlayer.setPlayerFoldHand(false);
        }
    }
    
    //method to check player can choose splitHand() option or not 
    private static boolean checkForSplitHand(Player objPlayer){
        Card firstCard = (Card)objPlayer.getPlayerCards().get(0);
        Card secondCard = (Card)objPlayer.getPlayerCards().get(1);
        //System.out.println("first card face value is : " + firstCard.getFaceValue());
        //System.out.println("second card face value is : " + secondCard.getFaceValue());
        if(firstCard.getFaceValue() == secondCard.getFaceValue()){
            return true;
        }
        else{
            return false;
        }
    }
    
    //method to check the game result
    private static GameResult checkGameResult(Player currentPlayer, Dealer currentDealer){
        GameResult currentPlayerResult;
         if (currentDealer.getTotalCardValue() == currentPlayer.getTotalCardValue()) {
             currentPlayerResult = GameResult.PUSH;             
         }
         else if(currentDealer.getTotalCardValue() > currentPlayer.getTotalCardValue()){
            currentPlayerResult = GameResult.LOSE;
         }
         else if(currentDealer.getTotalCardValue() < currentPlayer.getTotalCardValue()){
            currentPlayerResult = GameResult.WIN;
         }
         else{
             currentPlayerResult = GameResult.UNKNOWN;
         }    
        return currentPlayerResult;
        
    }
    //method to to split hand 
    private static void splitHand(){
        
    }
    public static void main(String[] args) {
        //method call to initialize all class variables
        initializeClassVariables();
        
        //code to prompt playerID and  create player object
        System.out.print("Enter player ID :  ");
        String playerId = input.next();        
        //addPlayer(playerID);
        player = new Player(playerId);
        
        //code to ask to amount for initial eposit
        System.out.print("Hey, " + player.getPlayerID() + ", input the deposit amount to start BlackJack : $ ");
        double depositAmount = inputAmount();
        player.setPlayerMoney(depositAmount);
        
        //code to print player's information
        System.out.println("----------------------------------------");
        System.out.println("PlayerID : " + player.getPlayerID());
        System.out.println("Initial Deposit : " + decimalFormat.format(player.getPlayerMoney()));
        
        //prints welcome message
        System.out.println("");
        System.out.println("****************************************");
        System.out.println("Welcome to BlackJack!");
        System.out.println("-------------------------------");

        //method call to print player information
        //printPlayerInformation();
        
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
            boolean askForBetAmount;
            boolean continueGame = true;
            do{
                System.out.println("----------------------------------------------");
                System.out.print("Hey, " + player.getPlayerID() +", input the bet amount for the game : $ ");
                double betAmount = inputAmount();
                if(player.isBetAmountValid(betAmount)){
                    player.setPlayerBetMoney(betAmount); 
                     askForBetAmount = false;
                }
                else{
                     System.out.println("-----------------------------------------------");
                    System.out.println("Hey, " + player.getPlayerID() + ", you cannot bet " + 
                         decimalFormat.format(betAmount) + " !" );
                    System.out.println("You have only " + decimalFormat.format(player.getPlayerMoney()));
                    System.out.println("-----------------------------------------------");
                    System.out.println(player.getPlayerID() + ", do you want to load fund in your deposit box ?");
                    System.out.print("Press Y to load and any other keys to cancel (Y|N) : ");
                    char playerResponse = input.next().charAt(0);
                    if(playerResponse == 'Y' || playerResponse =='y'){
                        loadFundInDepositBox(player);
                        askForBetAmount = true;
                    }else{
                        askForBetAmount = false;
                        continueGame = false;
                    }        
                }  
            }while(askForBetAmount);
            if(!continueGame){
                System.out.println("");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Hey, " + player.getPlayerID() + ", you did not load fund in your deposit box.");
                System.out.println("Game is over!!!");
                break;
            }

            
            //method call to deal first card to dealer
            //print current hand information
            System.out.println("");
            System.out.println("Dealing cards to the dealer : ");
            dealInitialCardsToDeaer(dealer);
            System.out.println("Dealer's current hand after dealing first card : ");            
            dealer.printCurrentHand(true);//argument 'true' enables to hide the first card of the dealer
            
            //method call to deal first cards to player
            //print current hand information
            dealInitialCardsToPlayer(player);
            System.out.println("Player's current hand after dealing first card : ");
            player.printCurrentHand();
            
            //method call to deal second card to dealer
            //print current hand information
            dealInitialCardsToDeaer(dealer);
            System.out.println("Dealer's current hand after dealing second card : ");
            dealer.printCurrentHand(true);//argument 'true' enables to hide the first card of the dealer
            
            //method call to deal second cards to player
            //print current hand information
            dealInitialCardsToPlayer(player);
            System.out.println("Player's current hand after dealing second card : ");
            player.printCurrentHand();
                        
            //code to check player gets BlackJack i.e.  21 or not
           if(checkForBlackJack(player)){
                System.out.println("Congratulation! " + player.getPlayerID() + ", you have BlackJack!\n"  +
                    "You win 3:2 amount.");
                //if player has black jack, he/she recieves 3:2 ratio of bet amount
                //double currentMoney = player.getPlayerMoney()+ player.getPlayerBetMoney() * 1.5;
                //player.setPlayerMoney(currentMoney);
                double playerWinMoney = player.getPlayerMoney()+ player.getPlayerBetMoney() * 1.5;
                player.updatePlayerMoney(playerWinMoney);
                nextDealingCycle = false;//indicates next deal is not required
                gameResult = true;//indicates that game is over. ready for new game
           }else{
               //if not blackjack, the game will continue to deal other cards
               gameResult = false;
               //method calls to check for split hand or not
                boolean ressultForSplit = checkForSplitHand(player);
                if(ressultForSplit){
                    System.out.println("eligible for split");

                }
                else{
                    System.out.println("not eligible for split");
                }
                //method calls to deal third and other rounds of cards to player
                dealOtherCardsToPlayer(player);


                //this if statement checks if the player does not fold hand/surrender game & does not bust
                //if(!isFoldHand && !playerBust )
                if(!player.isPlayerFoldHand() && !player.isPlayerBust() )
                {
                     //method calls to deal other rounds of cards to dealer           
                     dealOtherCardsToDealer();  
                }
           }//end of the if-block that checks player got black Jack or not
           
                     
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
