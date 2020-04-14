/*
 * Shankar Ghimire
 * StudentId:991585190
 * Course: ...............
 */
package game;

/**
 *
 * @author Dell
 */
public enum Suit {
    HEART, SPADE, DIAMOND, CLUB;
    public String toString(){
        switch(this){
            case HEART: return "Heart";
            case SPADE: return "Spade";
            case DIAMOND: return"Diamond";
            case CLUB: return "Club";
            default:   return"Unknown";
        }
    }
}
