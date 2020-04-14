/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author afmun
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of checkLength method, of class PasswordValidator.
     */
    @Test
    public void testBetAmountGood() {// Bet is $1, while player has $1 or more in their pocket
        System.out.println("testBetAmountGood");
        Player one = new Player("TestPlayer");
        one.setPlayerMoney(5);//Set amount of money owned by player
        Boolean result = one.isBetAmountValid(1); //Set amount to bet
        Boolean expResult= true;
        System.out.println(result);
        assertEquals(expResult, result);//Checks that user can bet
    }
     @Test
    public void testBetAmountBad() {  // Bet can't be less than $1
        System.out.println("testBetAmountBad");
        Player one = new Player("TestPlayer");
        one.setPlayerMoney(5);
        Boolean result = one.isBetAmountValid(-2);
        Boolean expResult= false;
        System.out.println(result);
        assertEquals(expResult, result);
    }
     @Test
    public void testBetAmountBoundary() { //Bet must be over $1 and not pass amount in pocket
        System.out.println("testBetAmountBoundary");
        Player one = new Player("TestPlayer");
        one.setPlayerMoney(10);
        Boolean result = one.isBetAmountValid(10);
        Boolean expResult= true;
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}
