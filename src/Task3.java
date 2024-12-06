import java.math.BigInteger;

public class Task3 {
    public static void main(String[] args) {
        BigInteger factorial = factorial(100);

        // Convert the BigInteger result to a string and calculate the sum of its digits
        String factorialStr = factorial.toString();
        int sumOfDigits = 0;

        // Loop through each character in the string, convert to int, and sum the digits
        for (char c : factorialStr.toCharArray()) {
            sumOfDigits += c - '0'; // Convert char to int and add to sum
        }
        System.out.println("Sum of the digits of 100! is: " + sumOfDigits);
    }

    // Method to calculate factorial using BigInteger (to handle large numbers)
    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
