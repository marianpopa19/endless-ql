package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class MultiplicationExpressionTest {
    private MultiplicationExpression expressionTest;
    @Property
    public void evaluateINT(int left, int right) {
        IntegerConstant leftToTest = new IntegerConstant(left);
        IntegerConstant rightToTest = new IntegerConstant(right);

        expressionTest = new MultiplicationExpression(leftToTest,rightToTest);
        assertEquals((left * right), expressionTest.evaluate().getValue());
    }

    @Property
    public void evaluateINT(double left, double right) {
        DecimalConstant leftToTest = new DecimalConstant( left);
        DecimalConstant rightToTest = new DecimalConstant( right);

        expressionTest = new MultiplicationExpression(leftToTest,rightToTest);
        assertEquals((left * right), expressionTest.evaluate().getValue());
    }

}