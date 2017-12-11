/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.Scanner;
public class Hangman {
  //private Player p1;
  //private Player p2;
  private static Hangmanprocess h;
  
  public static void main(String[]args) {
   
    System.out.println("Enter choice (1 for Single player and 2 for Double player)");
    Scanner sc=new Scanner(System.in);
    int choice=sc.nextInt();

    while (choice<0 || choice>3) {
      System.out.println("Pls select either 1 or 2");
      choice=sc.nextInt();
    }

    if (choice == 1)
      h = new SinglePlayerHangman();
    else
      h = new TwoPlayerHangman();

    h.process();
  }

}
