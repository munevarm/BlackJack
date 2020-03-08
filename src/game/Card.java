/*
 * Shankar Ghimire
 * StudentId:991585190
 * Course: ...............
 */
package game;

//import project.Card;

/**
 *Card class that implements the project.Card class for extension
 * @author Dell
 */
public class Card extends project.Card{
    //data members
    private Suit suit;
    private FaceValue faceValue;
    //default constructor
    public Card(){
        this.suit = Suit.HEART;
        this.faceValue = FaceValue.ACE;
    }
    //constructor that takes two parameters to construct a card
    public Card(Suit suit, FaceValue faceValue){
        this.suit = suit;
        this.faceValue = faceValue;
    }
    @Override
    public String toString() {
        return this.faceValue.toString()+" of " +  this.suit.toString() ;
    }
    
    public FaceValue getFaceValue(){
        return faceValue;
    }
    
}
