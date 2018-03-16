package parsing.checkers;

import classes.values.BooleanValue;
import classes.values.NumericValue;
import classes.values.Value;
import parsing.checkers.errors.TypeError;
import parsing.gen.QLParser;
import parsing.visitors.BaseVisitor;

public class TypeChecker extends BaseVisitor {
    // Typechecker checks if there are any inconsistensies in the types that were given in the syntax of the code
    public TypeChecker(QLParser.FormContext ctx){
        initQuestionMap();
        visitBlock(ctx.block());
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Value expectedType = (Value) visit(ctx.type());
        Class valueType = visit(ctx.expression()).getClass();
        System.out.println(expectedType.getType());

//        if(!haveSameType(valueType, expectedType)){
//            throw new TypeError(id, ctx.type().getText());
//        }

        return super.visitFixedQuestion(ctx);
    }

    @Override
    public Object visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
        String id = ctx.getText();

        if(!haveSameType(BooleanValue.class, getQuestion(id).getValue().getClass())){
            throw new TypeError(id, "boolean");
        }

        return super.visitBoolIdentifier(ctx);
    }

    @Override
    public Double visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
        String id = ctx.getText();

        if(!haveSameType(NumericValue.class, getQuestion(id).getValue().getClass())){
            throw new TypeError(id, "number");
        }

        return super.visitNumIdentifier(ctx);
    }

    private boolean haveSameType(Class value, Class type){
        return value.isAssignableFrom(type);
    }
}
