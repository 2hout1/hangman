/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

/**
 *
 * @author Tina ZHOU
 */
import java.util.*;
import java.io.*;

public class TwoPlayerHangman implements Hangmanprocess {
//public class TwoPlayerHangman {
  private static int MAX_LENGTH = 8;
  private static int MAX_INCORRECT_GUESSES = 10;
  private static int word_length;
  private static int numberOfTries;
  private static String wordToGuess;
  private static boolean wordIsGuessed;
  private static String playerOneName;
  private static char choice;
  public void process() {
    
    do{
    Scanner scan = new Scanner (System.in);
    char guessedCharacter;

    System.out.println("Hello! Welcome to the multiplayer section of Hangman, where you learn new words with your friends!");
    System.out.println("This game is only suitable for two players (Player One and Player Two), and Player One goes first.");
    System.out.println("Player One, Please enter your name.");
    playerOneName = scan.nextLine();
    System.out.println("Welcome, " + playerOneName + "! Please enter the word you wish to be guessed.");

    wordToGuess = scan.next().toLowerCase(); // Player One inputs the word
    word_length = wordToGuess.length(); // The game retrieves the length of the word and puts each letter into an array
    
    System.out.println("Please enter the number of guesses you will allow Player 2 to have. (minimum of " + word_length + " guesses!)");
    boolean validInput = false;
    while (!validInput){
    try{    
        numberOfTries = scan.nextInt();
        while (numberOfTries == 0 || numberOfTries < word_length || numberOfTries > 15) {
        System.out.println("Sorry! You must give a minimum of " + word_length + " guesses, and a maximum of 15 guesses");
        scan.nextLine();
        numberOfTries = scan.nextInt();   
    }
        validInput=true;
    }catch (InputMismatchException e){
           System.out.println("InputMismatch, please enter the number: ");
            scan.nextLine();
        }
    }
     // Player One inputs the number of guesses allowed.


    System.out.print('\u000C');

    // This part is for Player 2.

    int numberGuessedFalse = 0; // Number of incorrect letter guesses
    int numberGuessedTrue = 0; // Number of correct letter guesses
    char[] revealedLetters = new char[word_length];// convert to char array

    for (int rli = 0; rli<revealedLetters.length; rli++) //revealed letter index
      revealedLetters[rli] = '*';

    System.out.println("Player Two, guess your letters! (" + numberOfTries + " wrong guesses, then you lose!)");
    boolean wordIsGuessed = false;
    //String originalWord = new String(revealedLetters); // converts the char array revealedLetters to String type to make use of .contains method

    // Main code starts here


    
    while((new String(revealedLetters)).contains("*") && numberGuessedFalse<numberOfTries){      // while x stills contains _

      // fill logic
      char userInput = '\u0000';
      try {
      System.out.println("(Guess) Enter a letter in word ");
      Scanner input = new Scanner(System.in);
      userInput = input.nextLine().charAt(0);
      int matchedCharAt = wordToGuess.indexOf(userInput);
      if (matchedCharAt != -1) {
        revealedLetters[matchedCharAt] = userInput;
        while (matchedCharAt != -1) {
          matchedCharAt = wordToGuess.indexOf(userInput, matchedCharAt+1);
          if (matchedCharAt != -1) {
            revealedLetters[matchedCharAt] = userInput;
          }
        }
        System.out.print(" > ");
        System.out.println(new String(revealedLetters));
      }else{
        numberGuessedFalse++;
        System.out.println("Oops!" + numberGuessedFalse + " of " + numberOfTries + " wrong guesses: " + new String(revealedLetters));
      }
      } catch (StringIndexOutOfBoundsException str) {
      System.out.println(userInput + " is not in the word");
    }
    }

    if (!new String(revealedLetters).contains("*")){
      System.out.println("Congratulations! You got: " + wordToGuess);
    }else{
      System.out.println("Better luck next time. The word was: " + wordToGuess);
    }
    
    
    System.out.println("Would you like to play again? (Y/N)");

      Scanner s = new Scanner (System.in);
      choice = s.nextLine().charAt(0);
      while (!(choice == 'N' || choice == 'Y')) {
      System.out.println("Sorry! Please enter either 'N' or 'Y'.");
      choice = s.nextLine().charAt(0); //charAt picks up the first element, so even if the user enters eg: 'cat', the computer will pick up 'C'
    }
      choice = Character.toUpperCase(choice); //user might input small y, so computer must convert it to become upper case so it can compare to loop

    } while(choice == 'Y');

    System.out.println("Thank you for playing. See you next time!");

  }
}
