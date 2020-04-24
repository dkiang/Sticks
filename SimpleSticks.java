/**
 * basic class template for a Sticks game player
 * 
 * @author Kai
 */
public class SimpleSticks{
  
  /**
   * class toString(returns a string when class is called as a string)
   * 
   * @return name of this class
   */
  public String toString(){
    return "SimpleSticks";
  }
  
  /**
   * returns a random move for Sticks game.
   * 
   * @returns valid number of sticks to take from pile
   */
  public int play(int pile){
   if (pile > 2) {
    return (int)(Math.random()*3)+1;
   }
   else return (int)(Math.random()*pile)+1;
  }
   /**
   * called at start or end of game.
   * 
   * result = 0 for game start
   * result = 1 if game was won
   * result = -1 if game was lost
   * 
   */
   public void game(int result) {
   }
}
    