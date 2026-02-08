import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueCalculation = true;

            System.out.println("===== Simple Calculator =====");

            while (continueCalculation) {
                try {
                    // Get input from user
                    System.out.print("\nEnter first number: ");
                    double num1 = scanner.nextDouble();

                    System.out.print("Select operation (+, -, *, /): ");
                    char operator = scanner.next().charAt(0);

                    System.out.print("Enter second number: ");
                    double num2 = scanner.nextDouble();

                    double result = calculate(num1, num2, operator);
                    System.out.printf("\nResult: %.2f %c %.2f = %.2f%n", num1, operator, num2, result);

                    System.out.print("\nContinue? (yes/no): ");
                    String response = scanner.next().toLowerCase();
                    continueCalculation = response.equals("yes") || response.equals("y");

                } catch (Exception e) {
                    System.out.println("Error: Invalid input! Please enter valid data.");
                    scanner.nextLine(); // Clear the invalid input
                    continueCalculation = true;
                }
            }

            System.out.println("\nThank you for using the calculator!");
        }
    }

    public static double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero!");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
