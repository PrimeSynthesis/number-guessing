import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        try {
            System.out.println("Welcome to the number guessing game!");
            System.out.println("Do you want to (1) guess the number or (2) have the computer guess? Enter a number:");

            int choice = keyboard.nextInt();
            keyboard.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    humanGuess(keyboard);
                    break;
                case 2:
                    computerGuess(keyboard);
                    break;
                default:
                    System.err.println("Invalid input.");
                    break;
            }
        } finally {
            keyboard.close();
        }
    }

    public static void humanGuess(Scanner keyboard) {
        Random rand = new Random();
        int attempts = 0;
        int numberToGuess = rand.nextInt(100) + 1;
        boolean isGuessed = false;

        System.out.println("The Computer has chosen a number between 1 and 100.\nTry and guess it:");

        while (!isGuessed) {
            attempts++;
            int number = keyboard.nextInt();

            if (number > numberToGuess) {
                System.out.println("Your guess is too high.");
            } else if (number < numberToGuess) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Good job! The number was: " + numberToGuess);
                System.out.println("You guessed the number in " + attempts + " attempts.");
                isGuessed = true;
            }
        }
    }

    public static void computerGuess(Scanner keyboard) {
        Random random = new Random();
        System.out.println("Think of a number between 1 and 100.");
        System.out.println("Are you ready? Yes or No");

        if (!keyboard.nextLine().equalsIgnoreCase("Yes")) {
            System.exit(0);
        }

        int attempts = 0;
        int lowerBound = 1;
        int upperBound = 100;
        int comGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        boolean isGuessed = false;

        while (!isGuessed) {
            attempts++;
            System.out.println("The Computer guessed: " + comGuess);
            System.out.println("Is it (1) High, (2) Low, or (3) Correct?");
            int response = keyboard.nextInt();

            switch (response) {
                case 1:
                    upperBound = comGuess - 1;
                    break;
                case 2:
                    lowerBound = comGuess + 1;
                    break;
                case 3:
                    System.out.println("The Computer got the number in " + attempts + " attempts.");
                    isGuessed = true;
                    break;
                default:
                    System.out.println("Please enter a valid response.");
                    break;
            }

            if (upperBound < lowerBound) {
                System.out.println("Invalid range! Resetting bounds.");
                upperBound = 100;
                lowerBound = 1;
            }

            if (!isGuessed) {
                comGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            }
        }
    }
}
