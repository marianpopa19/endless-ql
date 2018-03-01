package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)

public class LessThanExpressionTest {
    LessThanExpression expressionTest;
    @Property
    public void evaluateInt(int left, int right) {
        IntegerConstant leftToTest = new IntegerConstant(left);
        IntegerConstant rightToTest = new IntegerConstant(right);

        expressionTest = new LessThanExpression(leftToTest, rightToTest);

        assertEquals((left < right), expressionTest.evaluate().getValue() );
    }
    @Property
    public void evaluateDOUBLE(double left, double right) {
        DecimalConstant leftToTest = new DecimalConstant(left);
        DecimalConstant rightToTest = new DecimalConstant(right);

        expressionTest = new LessThanExpression(leftToTest, rightToTest);

        assertEquals((left < right), expressionTest.evaluate().getValue() );
    }
}