package QL.QLVisitor;

import QL.ParseObjectsQL.Expressions.BinaryExpressions.*;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.*;
import QL.ParseObjectsQL.Expressions.ConstantExpression;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NegationExpression;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NotExpression;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpressionVisitor extends QLBaseVisitor<Expression> {
    private ExpressionTable expressionTable;

    public ExpressionVisitor(ExpressionTable exprTable){
        this.expressionTable = exprTable;
    }

    @Override
    public Expression visitBinaryExpr(QLParser.BinaryExprContext ctx) {
        int line = ctx.getStart().getLine();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);
        String operator = ctx.operator().getText();

        if (operator.charAt(0) == '+') {
            return new AdditionExpression(left, right, line);
        }
        else if (operator.charAt(0) == '-') {
            return new SubtractExpression(left, right, line);
        }
        else if (operator.charAt(0) == '*') {
            return new MultiplicationExpression(left, right, line);
        }
        else if (operator.charAt(0) == '/') {
            return new DivisionExpression(left, right, line);
        }

        else if (operator.charAt(0) == '>') {
            if (operator.charAt(1) == '=') {
                return new GreaterOrEqualExpression(left, right, line);
            }
            else {
                return new GreaterThanExpression(left, right, line);
            }
        }

        else if (operator.charAt(0) == '<') {
            if (operator.charAt(1) == '=') {
                return new LessOrEqualExpression(left, right, line);
            }
            else {
                return new LessThanExpression(left, right, line);
            }
        }

        else if (operator.charAt(0) == '=') {
            return new EqualExpression(left, right, line);
        }
        else if (operator.charAt(0) == '!') {
            return new NotEqualExpression(left, right, line);
        }
        else if (operator.charAt(0) == '&') {
            return new AndExpression(left, right, line);
        }
        else if (operator.charAt(0) == '|') {
            return new OrExpression(left, right, line);
        }

        return null;//Todo: Make this better, need to return an error or empty object?
    }

    @Override
    public Expression visitUnaryExpr(QLParser.UnaryExprContext ctx) {
        int line = ctx.getStart().getLine();
        Expression expr = visit(ctx.expression());
        String operator = ctx.operator().getText();

        if (operator.charAt(0) == '-') {
            return new NegationExpression(expr, line);
        }
        else if (operator.charAt(0) == '!') {
            return new NotExpression(expr, line);
        }

        return null;//Todo: Make this better, need to return an error or empty object?
    }

    @Override
    public Expression visitNestedExpr(QLParser.NestedExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Expression visitStringConstant(QLParser.StringConstantContext ctx) {
        return new StringConstant(ctx.getText(), ctx.getStart().getLine());
    }

    @Override
    public Expression visitDateConstant(QLParser.DateConstantContext ctx) {
        String dateString = ctx.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(ctx.getText(), formatter);
        return new DateConstant(localDate, ctx.getStart().getLine());
    }

    @Override
    public Expression visitIntegerConstant(QLParser.IntegerConstantContext ctx) {
        return new IntegerConstant(Integer.parseInt(ctx.getText()), ctx.getStart().getLine());
    }

    @Override
    public Expression visitDecimalConstant(QLParser.DecimalConstantContext ctx) {
        return new DecimalConstant(Double.parseDouble(ctx.getText()), ctx.getStart().getLine());
    }

    @Override
    public Expression visitMoneyConstant(QLParser.MoneyConstantContext ctx) {
        return new MoneyConstant(Double.parseDouble(ctx.getText()), ctx.getStart().getLine());
    }

    @Override
    public Expression visitBooleanConstant(QLParser.BooleanConstantContext ctx){
        return new BooleanConstant(Boolean.parseBoolean(ctx.getText()), ctx.getStart().getLine());
    }

    @Override
    public Expression visitIdentifierConstant(QLParser.IdentifierConstantContext ctx){
        return new ConstantExpression(ctx.IDENTIFIER().getText(), expressionTable, ctx.getStart().getLine());
    }
}