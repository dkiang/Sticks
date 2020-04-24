//Douglas Kiang
//Provides text input for a human Sticks player.
//*edits by Kai

import java.util.Scanner;

public class HumanSticks extends SimpleSticks{
  
  public String toString(){
    return "Human";
  }
  
  public int play(int pile){ // number represents number of sticks left
    Scanner in = new Scanner(System.in);    
    System.out.println("There are " + pile + " sticks");
    System.out.println("You can take between 1 and 3 sticks.");                     
    System.out.println("How many sticks would you like to take? ");
    return in.nextInt();
    }
}
