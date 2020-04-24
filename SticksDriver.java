import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver for Sticks game. Creates player classes to play Sticks game.
 * 
 * @author Kai
 * @author Mr. Kiang (revised, cleaned up game code, added comments)
 * @see SimpleSticks SimpleSticks
 */
public class SticksDriver{
  
  /**
   * main method
   */
  public static void main(String[] args)
  {
    SimpleSticks player1; // Declares a player1 and player2 object. Because these objects extend SimpleSticks (they are a type of SimpleChip)
    SimpleSticks player2; // we know we are setting aside enough room no matter which student's object it is.
    int initpile = 100; 
    int pile = 100;
    int numgames = 0;
    int p1wins = 0; // For win stats
    int p2wins = 0;
    int p1forfeit = 0; // How many games player forfeits because of illegal plays 
    int p2forfeit = 0;
    int randomMax = 0; // If random pile size chosen, what the max is
    int nextPlay = 0;
    String playerError = " made an invalid play.";
    String p1name = ""; // Created so we can change the name of the player if the same class is chosen for both players
    String p2name = "";
    boolean showText = true; // Whether the moves are displayed on screen
    boolean player1turn = true;
    boolean error = false; // Whether an illegal move was made
    boolean randomPile = false; // Whether a random pile size is chosen each game
    ArrayList<SimpleSticks> players = new ArrayList<SimpleSticks>(); // These are ArrayLists to store all of the potential players
    ArrayList<SimpleSticks> players2 = new ArrayList<SimpleSticks>(); // Make sure the class files are located in the same folder as this driver file.
    
    //*
    //add new players here in the same format
    //*
    players.add(new HumanSticks());
    players2.add(new HumanSticks());
    players.add(new SimpleSticks());
    players2.add(new SimpleSticks());
    players.add(new KiangSticks());
    players2.add(new KiangSticks());
    
    Scanner in = new Scanner(System.in);
    
    //Setting Pile Size
    System.out.println("Pick a pile size (0 = Random)");
    initpile = in.nextInt();
    
    //random pile size
    if (initpile == 0) // If player has chosen to have a pile size that varies
    {
      System.out.println("Random pile size range: 3 to ___?"); // This sets the maximum for the pile and stores it in randomMax
      randomMax = in.nextInt();
      randomPile = true;
    }
    
    //listing players for p1 selection
    System.out.println("Please select first player (player 1 goes first)");    
    for(int i = 0; i < players.size(); i++)
    {
      System.out.print(i + "."+players.get(i)+"\n"); // This iterates through the entire Players arraylist and grabs the name of each Player from each object's toString()
    }
    player1 = players.get(in.nextInt());
    
    //listing players for p2 selection
    System.out.println("Please select second player");    
    for(int i = 0; i < players2.size(); i++)
    {
      System.out.print(i + "." + players2.get(i) + "\n");
    }
    player2 = players2.get(in.nextInt());
    
    //number of games
    System.out.println("How many games?");
    numgames = in.nextInt();
    
    //game text
    System.out.println("Show game text? true/false");
    String response = in.nextLine();
    if (response.equals("T") || response.equals("t") || 
        response.equals("true") || response.equals("True") || 
        response.equals("TRUE") || response.equals("1")) {
          showText = true;
        }
        else showText = false;
    
    p1name = player1.toString();
    p2name = player2.toString();
    if (p1name.equals(p2name))
    {
      p1name = "Good " + p1name;
      p2name = "Evil " + p2name; // If two instances of the same object are played off against each other, one is the evil twin
    }
    //Main Game Loop
    for (int i = 1; i <= numgames; i++) // This repeats the following code for the number of specified games.
    {
      pile = initpile; // Sets pile size to user-specified number, unless...
      if (randomPile) // if user has chosen to make the piles be random,
        pile = (int)(Math.random()*(randomMax-3))+3; // choose a random pile size between 3 (the minimum) and randomMax (the maximum).
      player1turn = true; // At the start of each game player 1 goes first
      System.out.println("\nGame No. " + i + " (" + pile + " Sticks)");
//*** START OF EACH GAME ***
      player1.game(0); // Signal each player that game is starting
      player2.game(0);
      while (pile > 0) 
      {
        if (showText)
          System.out.println("Pile size = " + pile);
        if (player1turn) // If it's player1's turn call player1 move
        {
          
          //System.out.println("P1 play(" + pile + "," + lastMove + ")"); // DEBUG code, uncomment this if you want to see the actual call being made.
          
          nextPlay = player1.play(pile); // This calls the Player object's play() method passing in the pile size.
          if (showText) // If user wants to know...
            System.out.println(p1name + " took " + nextPlay + " sticks."); // Print the move.
        }
        else
        {
          //System.out.println("P2 play(" + pile + ", " + lastMove + ")"); // DEBUG code, uncomment this if you want to see the actual call being made.
          nextPlay = player2.play(pile); // otherwise call player2 move
          if (showText)
            System.out.println(p2name + " took " + nextPlay + " sticks.");
        }
        
        // If any illegal moves occurred...
        if (nextPlay > pile) // If the player takes more than what is in the pile
        {
          pile = 0; // then this game is over!
          error = true; // Flag the game as an error
          if (player1turn) // Print appropriate error message based on whose turn it is
          {
            System.out.println(p1name + " made an illegal play.");
            System.out.println(p2name + " wins!");
            p2wins++;
            p1forfeit++;
          }
          else 
          {
            System.out.println(p2name + " made an illegal play.");
            System.out.println(p1name + " wins!");
            p1wins++;
            p2forfeit++;
          }
        }

        
        
          pile -= nextPlay;
        
        player1turn = !player1turn; // flips turns
      } 
//*** END OF EACH GAME ***
        if (player1turn == false) // If the pile is zero and we have flipped the player1turn flag and it is false, then player 1 made the last move, took the last piece, and loses.
        {
          System.out.println(p2name + " wins!");
          p2wins++;
          player2.game(1); // Signal each player that game has ended
//          player1.game(-1);
        }
        else
        {
          System.out.println(p1name + " wins!");
          p1wins++;
          player1.game(1); // Signal each player that game has ended
//          player2.game(-1);
        }
      }
    //End of main loop (all games completed)
    //Print win record for each player
    
//    String pileSizeText = "" + initpile;
   // if (initpile == 0) pileSizeText = "Random from 3-"+randomMax;
//    System.out.println(numgames);
//    System.out.println(p1name + " won " + p1wins + " times.");
//    System.out.println(p2name + " won " + p2wins + " times.");
    System.out.println("\nPile size:" + initpile + " Games:" + numgames + "\n" + p1name + " won " + p1wins + " times (went first) \n");
    if (p1forfeit > 0) System.out.println(p1name + " forfeited " + p1forfeit + " game(s)."); // If there were any forfeited games due to illegal moves
    if (p2forfeit > 0) System.out.println(p2name + " forfeited " + p2forfeit + " game(s)."); // they are reported here.
    
    /* There is a way to make this print out nicely in a table format but I don't exactly have it correct.
     System.out.format("%10s%5s%5s","Name","Wins","%");
     System.out.println();
     System.out.format("%10s%5d%5f",p1name,p1wins,p1wins/(double)numgames);
     System.out.println();
     System.out.format("%10s%5d%5f",p2name,p2wins,p2wins/(double)numgames);
     */
  }
}