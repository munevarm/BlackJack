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
        Scanner input = new Scanner(System.in);
        DecimalFormat decimalFormat = new DecimalFormat("$#,##0.0");
        //testing card
//        Card card =new  Card(Suit.HEART,FaceValue.KING);
//        System.out.println(card.toString());

        //object to hold all 52 playing cards
//        DeckOfCards playingDeck = new DeckOfCards(52);
        //object to hold the card of dealer
//        DeckOfCards dealerCards = new DeckOfCards();
        //object to hold the cards of player
//        DeckOfCards playerCards = new DeckOfCards();
        //object to hold dealer info
        Dealer dealer = new Dealer();
        //object to hold player info
        System.out.print("Enter player ID(String or Numeric value ) :  ");
        String playerId = input.next();
        System.out.print("Enter initial deposit money (only numeric value ) : ");
        double depostiMoney = input.nextDouble();
        System.out.println("------------------------------------");
        System.out.println("");
        Player player = new Player(playerId, depostiMoney);

        //prints welcome message
        System.out.println("Welcome to BlackJack!");
        System.out.println("-------------------------------");

        //print player's information
        System.out.println("Player's Information: ");
        System.out.println("Player Id is : " + player.getPlayerID());
        System.out.println(player.getPlayerID() + " has " + decimalFormat.format(player.getPlayerMoney()));
        System.out.println("------------------------------------------");
//        System.out.println("card size is : " + deck.getSize());
        DeckOfCards.createFullDeck();
//        deck.printAllCards();
        DeckOfCards.shuffle();
//        DeckOfCards.printAllCards();
//        System.out.println("Total Card in Playing Card deck now : " + DeckOfCards.countTotalCards());
//        DeckOfCards.calculateCardValues(DeckOfCards.getCards());

        //loop to repeat the game round
        //code to start to deal the cards
        boolean nextGame = true;
        boolean gameResult = false;
        boolean playerBust = false;
        boolean dealerBust = false;
        int numberOfDealingCycles = 1;
        //deal card for first cycle round

        while (nextGame) {
            System.out.println("--------------------------------------------");
            System.out.println("Dealing...");
//            System.out.println("---------------------------------------------");
            //Checks the player has sufficient fund or not
            if (player.getPlayerMoney() <= 0) {
                System.out.println("You dont have sufficient funds to play game.");
                System.out.println("Do you want to load fund in your account ? (Y/N) ? ");
                char loadFund = input.next().charAt(0);
                if (loadFund == 'Y' || loadFund == 'y') {
                    System.out.println("Please, enter the amount you want to load : ");
                    double loadAmount = input.nextDouble();
                    player.loadFunds(loadAmount);
                    System.out.println("Now, player has " + decimalFormat.format(player.getPlayerMoney()));
                } else {
                    break;
                }
            } else {
                System.out.print("How much would you want to bet(only numeric value) ?  ");
                double betMoney = input.nextDouble();
                System.out.println("");
                while (betMoney > player.getPlayerMoney()) {
                    System.out.println("You canot bet more money than you have at present.");
                    System.out.println("You have only " + decimalFormat.format(player.getPlayerMoney()));
                    System.out.println("Please, enter the valid bet amount again. ");
                    betMoney = input.nextDouble();
                }
                player.setPlayerBetMoney(betMoney);
            }
            

//            System.out.println("Dealer's turn: ");
            //Code to deal card to dealer
//            dealer.addCard(playingDeck.getTopCard());
            dealer.play();
            //to test remaining cards
//            System.out.println("-------------------");
//            DeckOfCards.printAllCards();
//            System.out.println("Total Card in Playing Card deck now : " + DeckOfCards.countTotalCards());
//            playingDeck.removeCard(0);
            //deal card to player

            //code to deal card to player
//            System.out.println("Player's turn : ");
            player.play();
            numberOfDealingCycles++;
            //to test remaining cards
//            System.out.println("-------------------");
//            DeckOfCards.printAllCards();
//            System.out.println("Total Card in Playing Card deck now : " + DeckOfCards.countTotalCards());
//            player.addCard(playingDeck.getTopCard());
//            playingDeck.removeCard(0);
            //deal second cycle of cards to each
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
//            System.out.println("-------------------");
//            playingDeck.printAllCards();

            //loop to repeat after dealing 2, 2 card to each- dealer and player
            boolean nextDealingCycle = true;
            //code to check player gets 21 or not
            if (player.getTotalCardValue() == 21) {
                System.out.println("Congratulation! Player wins!");
                double currentMoney = player.getPlayerMoney() + player.getPlayerBetMoney();
                player.setPlayerMoney(currentMoney);
                nextDealingCycle = false;
                gameResult = false;
            }
            while (nextDealingCycle) {
                System.out.println("Options to select: ");
                System.out.println("\t1.Hit\n\t2.Stand");
                System.out.print("Please, select an option : ");
                int nextDeal = input.nextInt();
                switch (nextDeal) {
                    case 1:

                        player.play();
                        //print players cards at hand
                        System.out.println("Player's Cards at hand after dealing of Round " + (numberOfDealingCycles) + ": ");
                        player.printCardsAtHand();
                        System.out.println("\tTotal card value at player's hand is : " + player.getTotalCardValue());
                        numberOfDealingCycles++;
                        //code to check player bust or not after 3rd and more cards
                        if (player.getTotalCardValue() > 21) {
                            System.out.println("Player Bust!");
                            System.out.println("Dealer Wins!");
                            double currentMoney = player.getPlayerMoney() - player.getPlayerBetMoney();
                            player.setPlayerMoney(currentMoney);
                            System.out.println("Now, player has " + decimalFormat.format(player.getPlayerMoney()));
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
                System.out.println("\tTotal card value at dealer's hand is : " + dealer.getTotalCardValue());

                if (dealer.getTotalCardValue() == 21) {
                    System.out.println("Dealer wins!");
                    double playerMoney = player.getPlayerBetMoney() - player.getPlayerBetMoney();
                    player.setPlayerMoney(playerMoney);
                    gameResult = true;
                } else if (dealer.getTotalCardValue() > player.getTotalCardValue()) {
                    System.out.println("Dealer wins!");
                    double playerMoney = player.getPlayerBetMoney() - player.getPlayerBetMoney();
                    player.setPlayerMoney(playerMoney);
                    System.out.println("Now, Player has " + decimalFormat.format(player.getPlayerMoney()));
                    gameResult = true;
                } else {
                    //Code to check the game result and deal next card to the dealer or not
                    nextDealingCycle = true;
                    while ((dealer.getTotalCardValue() < 17 || dealer.getTotalCardValue() < player.getTotalCardValue()) && nextDealingCycle == true) {
                        //if (dealer.getTotalCardValue() < player.getTotalCardValue()) {
                        dealer.play();
                        System.out.println("Dealer's Card information  : ");
                        dealer.printCardsAtHandWithFirstHidden(false);
                        System.out.println("\tTotal card value at dealer's hand is : " + dealer.getTotalCardValue());
                        //}
                        if (dealer.getTotalCardValue() > 21) {
                            System.out.println("Dealer Bust!");
                            double playerMoney = player.getPlayerMoney() + player.getPlayerBetMoney();
                            player.setPlayerMoney(playerMoney);
                            System.out.println("Player Wins!");
                            System.out.println("Now, Player has " + decimalFormat.format(player.getPlayerMoney()));
                            gameResult = true;
                            nextDealingCycle = false;
                            break;
                        } else if (dealer.getTotalCardValue() > player.getTotalCardValue()) {
                            System.out.println("Dealer wins!");
                            double currentMoney = player.getPlayerMoney() - player.getPlayerBetMoney();
                            player.setPlayerMoney(currentMoney);
                            System.out.println("Now, Player has " + decimalFormat.format(player.getPlayerMoney()));
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
                    System.out.println("Player has " + decimalFormat.format(player.getPlayerMoney()) + " after playing game.");

                    nextGame = false;
                }
                gameResult = false;
                //}  
            }

        }

    }
}
