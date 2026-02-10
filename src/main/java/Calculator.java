import java.util.Scanner;

public class Calculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===== Simple Calculator =====");
        System.out.println("Enter two numbers and an operation (+, -, *, /)");
        System.out.println();
        
        try {
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();
            
            System.out.print("Enter operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);
            
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();
            
            double result = calculate(num1, num2, operator);
            System.out.println();
            System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input!");
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Performs basic arithmetic operations
     * @param num1 First number
     * @param num2 Second number
     * @param operator Operator (+, -, *, /)
     * @return Result of the operation
     */
    public static double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return add(num1, num2);
            case '-':
                return subtract(num1, num2);
            case '*':
                return multiply(num1, num2);
            case '/':
                return divide(num1, num2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    public static double add(double a, double b) {
        return a + b;
    }
    
    public static double subtract(double a, double b) {
        return a - b;
    }
    
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero!");
        }
        return a / b;
    }
}
