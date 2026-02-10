import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    
    private Calculator calculator = new Calculator();
    
    @Test
    public void testAdd() {
        assertEquals(5.0, calculator.add(2, 3), 0.0);
        assertEquals(0.0, calculator.add(-2, 2), 0.0);
        assertEquals(-5.0, calculator.add(-2, -3), 0.0);
    }
    
    @Test
    public void testSubtract() {
        assertEquals(1.0, calculator.subtract(3, 2), 0.0);
        assertEquals(-1.0, calculator.subtract(2, 3), 0.0);
        assertEquals(0.0, calculator.subtract(5, 5), 0.0);
    }
    
    @Test
    public void testMultiply() {
        assertEquals(6.0, calculator.multiply(2, 3), 0.0);
        assertEquals(-6.0, calculator.multiply(-2, 3), 0.0);
        assertEquals(0.0, calculator.multiply(0, 5), 0.0);
    }
    
    @Test
    public void testDivide() {
        assertEquals(2.0, calculator.divide(6, 3), 0.0);
        assertEquals(4.0, calculator.divide(8, 2), 0.0);
        assertEquals(0.5, calculator.divide(1, 2), 0.0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(5, 0);
    }
    
    @Test
    public void testCalculate() {
        assertEquals(5.0, calculator.calculate(2, 3, '+'), 0.0);
        assertEquals(1.0, calculator.calculate(3, 2, '-'), 0.0);
        assertEquals(6.0, calculator.calculate(2, 3, '*'), 0.0);
        assertEquals(2.0, calculator.calculate(6, 3, '/'), 0.0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOperator() {
        calculator.calculate(5, 3, '%');
    }
}
