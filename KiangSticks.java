import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import javax.print.attribute.*;

/**
 * Based off of basic class template for a Sticks game player
 * 
 * @author Mr. Kiang
 */
public class KiangSticks extends SimpleSticks{
   ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
   int max = 20;
   int[] moves = new int[20];

   public KiangSticks() {
      // Load buckets with learned data from text file
//      for (int i = 0; i < 20; i++) {
//         ArrayList<Integer> a = new ArrayList<Integer>();
//         a.add(1);
//         a.add(2);
//         a.add(3);
//         buckets.add(a);
//         }
      load();
   }

  /**
   * class toString(returns a string when class is called as a string)
   * 
   * @return name of this class
   */
  public String toString(){
    return "Mr. Kiang";
  }
  
  /**
   * returns a random move for Sticks game.
   * 
   * @returns valid number of sticks to take from pile
   */
  public int play(int pile) {
   if (pile > 20) return 3;
   if (pile == 1) return 1;
   int index = pile - 1;
   // Gets the ArrayList of possible moves at the same index as the pile size
   // Selects a random integer from that ArrayList
   // Copies that number into moves at the same index
   ArrayList<Integer> bucket = buckets.get(index);
   moves[index] = bucket.get((int)(Math.random()*bucket.size()));
   return moves[index];
  }

   public void game(int result) {
      if (result == 0) {
         // reset moves array
         for (int i = 0; i < max; i++)
         {
            moves[i] = 0;
         }
      }
      else if (result == 1) {
         // copy moves back into buckets' arraylists
         for (int i = 0; i < max; i++)
         {
            if (moves[i] > 0)
            {
               int j = 0;
               int n = buckets.get(i).size();
               while (moves[i] > buckets.get(i).get(j) && j < n)
               {
                  j++;
               }
               buckets.get(i).add(j,moves[i]);
            }
         }
         save();
      }
   }
   
   private void load()
   {
      try {
         Scanner file = new Scanner(new BufferedReader(new FileReader("moves.txt")));
         int row = 0;
         while(file.hasNextLine()) {
            ArrayList<Integer> a = new ArrayList<Integer>();
            String temp[] = file.nextLine().trim().split(",");
            for (int i = 0; i < temp.length; i++) {
               a.add(Integer.parseInt(temp[i]));
            }
            buckets.add(a);
         }
         System.out.println(buckets);
      }
      catch (FileNotFoundException f){}

   }
   
   public void save()
   {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("moves.txt")))) {
         for (ArrayList<Integer> a : buckets) {
            for (int i = 0, n = a.size(); i < n - 1; i++) {
               writer.write(a.get(i) + ",");
            }
            writer.write(a.get(a.size()-1) + "\n");
         }
         writer.close();
      }
      catch (IOException ex) {
         // Handle me
      } 
   }
}
    