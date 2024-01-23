package edu.pasadena.cs.cs03b;

import java.util.Scanner;
import java.util.Random;

/**
 * GuessNumberGame class.
 * This class implements a number guessing game where the user is asked to guess a number within a specified range.
 * The user has a limited number of free guesses and has the option to purchase additional guesses.
 */
public class GuessNumberGame {
    Scanner input = new Scanner(System.in);// Scanner object for capturing user input.
    Random rand = new Random(); // Random object for generating random numbers.
    int size = 0;// Variable to store the size (number of digits) of the number to guess.
    int number = 0;// The number that the user needs to guess.

    int min = 0;// Minimum possible value of the number based on the number of digits.
    int max = 0;// Maximum possible value of the number based on the number of digits.
    
    
    /**
     * Generates a random number within a range based on the specified number of digits.
     * @param size The number of digits in the number to be guessed.
     * @return The generated random number.
     */
    public int Generate_Random_Numbers(int size) {
        min = (int) Math.pow(10, size - 1);// Calculate the minimum number with 'size' digits.
        max = (int) Math.pow(10, size) - 1;// Calculate the minimum number with 'size' digits.
        System.out.println("Guess a magic number between " + min + " and " + max + ".");
        return rand.nextInt(max - min + 1) + min;// Return a random number within the range [min, max].
    }
    /**
     * Constructor for the GuessNumberGame class.
     * Initializes the game by asking the user for the number of digits and generating a random number.
     * Ensures that the number of digits is between 1 and 10.
     */
    public GuessNumberGame() {
        System.out.println(
                "Please specify the number of digits to guess.(Max 10 digits in the range 1000000000 and 2147483646.)");
        do {
            size = input.nextInt();
            if (size > 10 || size < 1) {
                System.out.println("The number of digits is out of range, please enter a number between 1 and 10.");
                System.out.println("Enter the number of digits: ");
                continue;
            } else {
                this.number = Generate_Random_Numbers(size);
                break;
            }
        } while (size > 10 || size < 1);
    }
    /**
     * Handles the logic for users to guess the number.
     * Allows users to enter guesses and provides feedback on whether the guess is too high, too low, or correct.
     * Users can use free guesses or buy additional guesses if they run out.
     */
    public void Guess() {
        int guess = 0;// The user's guess.
        int money = 10;// The money the user has for buying extra guesses.
        int guessCount = 5;// The number of free guesses.
        char answer = ' ';// User's decision to buy a guess or not.
        System.out.println(
                "Each user has 5 free guesses, more than 5 allows the user cost 1 dollar to purchase guesses.");
        System.out.println("You have " + money + " dollars initially.");
        do {
            // If the user has no free guesses left and has money, offer to buy more guesses.
            if (guessCount == 0 && money != 0) {
                System.out.println("You have no more free guesses, each guess will cost you 1 dollar.");
                System.out.println("Do you willing to pay a dollar for a guess? (Enter 'Y' is 'yes', else is quit.)");
                input.nextLine(); // Consume the newline character left by the previous nextInt() call.
                answer = input.nextLine().toUpperCase().charAt(0);
                if (answer == 'Y') {
                    money--;// Deduct a dollar for an additional guess.
                    System.out.println("You purchased a guessing opportunity, you have " + money + " dollar left.");
                } else {
                    System.out.println("Game Over!");
                    break;
                }
            }
            // Ensure the user's guess is within the valid range.
            do {
                System.out.println("Enter your guess: ");
                if (input.hasNextInt()) {
                    guess = input.nextInt();
                    if (guess < min || guess > max) {
                        System.out.println("Your guess is out of range, please enter a number between " + min + " and "
                                + max + ".");
                    }
                } else {
                    System.out.println("Your guess is not a valid number, please enter a number between " + min
                            + " and " + max + ".");
                    input.next();
                    continue;
                }
            } while (guess < min || guess > max);
            // Check if the user's guess is correct, too high, or too low, and give appropriate feedback.
            if (guess == number) {
                System.out.println("Correct!");
                System.out.println("You win! The magic number is: " + number);
                break;
            } else if (guess > number) {
                if (guessCount != 0) {
                    guessCount--;
                    System.out.println("Your guess is too high, you have " + guessCount + " free guesses left");
                } else {
                    System.out.println("Your guess is too high.");
                }
            } else {
                if (guessCount != 0) {
                    guessCount--;
                    System.out.println("Your guess is too low, you have " + guessCount + " free guesses left.");
                } else {
                    System.out.println("Your guess is too low.");
                }
            }
        } while (guessCount != 0 || money != 0);// Continue the game until the user runs out of guesses or money.
        // If the user's last guess is not correct, inform them that they lost and reveal the correct number.
        if (guess != number) {
            System.out.println("You lose! The magic number is: " + number);
        }
    }
    /**
     * Main method to run the game.
     * Creates an instance of GuessNumberGame and starts the guessing process.
     */
    public static void main(String[] args) {
        GuessNumberGame game = new GuessNumberGame();
        game.Guess();
        String strMsg = GuessNumberGame.dummy();
        System.out.println(strMsg);

    }
    /**
     * Dummy method for demonstration or testing purposes.
     * @return A dummy test message.
     */
    public static String dummy() {
        return "This is a Dummy Test";
    }
}