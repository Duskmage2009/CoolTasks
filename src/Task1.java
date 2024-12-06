import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of N: ");
        int N = scanner.nextInt();

        if (N < 0) {
            System.out.println("N must be a non-negative integer.");
            return;
        }

        System.out.println("Number of valid bracket expressions: " + countValidBracketExpressions(N));
    }

    // Method to calculate the number of valid bracket expressions using Catalan numbers
    private static long countValidBracketExpressions(int N) {
        // Array to store Catalan numbers
        long[] catalan = new long[N + 1];
        catalan[0] = 1; // C(0) = 1

        // Compute Catalan numbers up to C(N)
        for (int i = 1; i <= N; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - 1 - j];
            }
        }

        return catalan[N];
    }
}