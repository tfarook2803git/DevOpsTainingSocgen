# Simple Calculator

A Java-based command-line calculator with unit tests and GitHub Actions CI/CD pipeline.

## Features

- Basic arithmetic operations: Addition (+), Subtraction (-), Multiplication (*), Division (/)
- Comprehensive unit tests with JUnit
- Error handling for division by zero and invalid operators
- Multi-platform support (Linux, Windows, macOS)
- GitHub Actions pipeline for automated testing and building

## Files Structure

```
src/
  main/java/
    Calculator.java          # Main calculator class
  test/java/
    CalculatorTest.java      # Unit tests
.github/workflows/
  calculator.yml            # GitHub Actions workflow
pom.xml                     # Maven configuration
```

## Build & Run

### Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher

### Local Development

```bash
# Clone the repository
git clone <your-repo>
cd <your-repo>

# Run tests
mvn clean test

# Build the project
mvn clean package

# Run the calculator
java -jar target/system-info.jar

# Or use Maven exec plugin
mvn exec:java -Dexec.mainClass="Calculator"
```

## Usage Examples

### Interactive Mode
```
===== Simple Calculator =====
Enter two numbers and an operation (+, -, *, /)

Enter first number: 10
Enter operator (+, -, *, /): +
Enter second number: 5

Result: 10.0 + 5.0 = 15.0
```

### Programmatic Usage

```java
Calculator calc = new Calculator();

// Addition
double result1 = calc.add(10, 5);          // Returns 15.0

// Subtraction
double result2 = calc.subtract(10, 5);     // Returns 5.0

// Multiplication
double result3 = calc.multiply(10, 5);     // Returns 50.0

// Division
double result4 = calc.divide(10, 5);       // Returns 2.0

// Using the calculate method with operator
double result5 = calc.calculate(10, 5, '+'); // Returns 15.0
```

## Testing

The project includes comprehensive unit tests using JUnit 4:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CalculatorTest

# Generate test report
mvn surefire-report:report
```

### Test Coverage

- Addition tests (positive, negative, zero)
- Subtraction tests (forward, backward, same numbers)
- Multiplication tests (positive, negative, zero)
- Division tests (normal, decimals)
- Division by zero exception handling
- Invalid operator handling

## GitHub Actions Pipeline

The calculator pipeline (`.github/workflows/calculator.yml`) includes:

### Triggers
- Push to main/master branches
- Pull requests to main/master branches
- Manual trigger via workflow_dispatch

### Build Matrix
- Operating Systems: Ubuntu, Windows, macOS
- Java Versions: 11, 17

### Pipeline Steps
1. Checkout code
2. Set up Java environment
3. Cache Maven dependencies
4. Run unit tests
5. Build project
6. Verify JAR creation
7. Upload test results
8. Upload JAR artifacts
9. Publish test results

### View Results

After pushing to GitHub:
1. Go to Actions tab
2. Click on the latest "Calculator Pipeline" run
3. View test results and download artifacts

## Artifacts

The pipeline generates:
- **Calculator JAR**: `calculator-jar-<OS>-java<VERSION>`
- **Test Results**: `test-results-<OS>-java<VERSION>`

## Error Handling

The calculator handles:
- Division by zero: Throws `IllegalArgumentException`
- Invalid operators: Throws `IllegalArgumentException`
- Invalid user input: Catches and displays error message

## Performance Considerations

- Uses `java.util.Scanner` for input
- Supports both integer and floating-point numbers
- Efficient mathematical operations with no external dependencies

## Extending the Calculator

To add new operations:

1. Add method to `Calculator.java`:
```java
public static double power(double a, double b) {
    return Math.pow(a, b);
}
```

2. Add case to `calculate()` method:
```java
case '^':
    return power(num1, num2);
```

3. Add test to `CalculatorTest.java`:
```java
@Test
public void testPower() {
    assertEquals(8.0, calculator.calculate(2, 3, '^'), 0.0);
}
```

## License

MIT

## Support

For issues or questions, please open an issue in the GitHub repository.
