import java.util.*;
public class NumberGuessGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int totalScore = 0;
        char playAgain;

        do {
            int number = rand.nextInt(100) + 1;
            int guess;
            int attempts = 0;
            int maxAttempts = 5;
            boolean win = false;

            System.out.println("Guess number between 1 to 100");
            System.out.println("You have only " + maxAttempts + " attempts");

            while (attempts < maxAttempts) {

                System.out.print("Enter your guess: ");
                guess = sc.nextInt();
                attempts++;

                if (guess == number) {
                    System.out.println("✅ Correct Guess!");
                    win = true;
                    totalScore += (maxAttempts - attempts + 1) * 10;
                    break;
                }
                else if (guess > number) {
                    System.out.println("📉 Too High!");
                }
                else {
                    System.out.println("📈 Too Low!");
                }
            }

            if (!win) {
                System.out.println("❌ You Lost! Number was: " + number);
            }

            System.out.println("⭐ Total Score: " + totalScore);

            System.out.print("Play Again? (y/n): ");
            playAgain = sc.next().charAt(0);

        } while (playAgain == 'y' || playAgain == 'Y');

        System.out.println("Game Over! Final Score = " + totalScore);
    }
}
