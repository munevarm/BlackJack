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
public enum GameResult {
    WIN, LOSE,PUSH,UNKNOWN;
    public String toString(){
        switch(this){
            case WIN: return "Win";
            case LOSE: return "Lose";
            case PUSH: return "Push";
            case UNKNOWN: return "Unknown";
            default : return "Unknown";
        }
    }
}
