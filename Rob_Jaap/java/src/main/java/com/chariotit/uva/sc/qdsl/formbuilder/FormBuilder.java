package com.chariotit.uva.sc.qdsl.formbuilder;

import com.chariotit.uva.sc.qdsl.ast.ql.node.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.BooleanConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.IntegerConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.MoneyConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.StringConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.operator.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.BooleanTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.IntegerTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.MoneyTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.StringTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class FormBuilder extends NodeVisitor {

    public void buildForm(QLAstRoot astRoot) {
        astRoot.acceptVisitor(this);
    }

    @Override
    public void visitBooleanConstant(BooleanConstant booleanConstant) {

    }

    @Override
    public void visitIntegerConstant(IntegerConstant integerConstant) {

    }

    @Override
    public void visitMoneyConstant(MoneyConstant moneyConstant) {

    }

    @Override
    public void visitStringConstant(StringConstant stringConstant) {

    }

    @Override
    public void visitDivideOp(DivideOp divideOp) {

    }

    @Override
    public void visitEqOp(EqOp eqOp) {

    }

    @Override
    public void visitGteOp(GteOp gteOp) {

    }

    @Override
    public void visitGtOp(GtOp gtOp) {

    }

    @Override
    public void visitLteOp(LteOp lteOp) {

    }

    @Override
    public void visitLtOp(LtOp ltOp) {

    }

    @Override
    public void visitMinusOp(MinusOp minusOp) {

    }

    @Override
    public void visitMultiplyOp(MultiplyOp multiplyOp) {

    }

    @Override
    public void visitNeqOp(NeqOp neqOp) {

    }

    @Override
    public void visitNotOp(NotOp notOp) {

    }

    @Override
    public void visitPlusOp(PlusOp plusOp) {

    }

    @Override
    public void visitBooleanType(BooleanTypeNode booleanType) {

    }

    @Override
    public void visitIntegerType(IntegerTypeNode integerType) {

    }

    @Override
    public void visitMoneyType(MoneyTypeNode moneyType) {

    }

    @Override
    public void visitStringType(StringTypeNode stringType) {

    }

    @Override
    public void visitAstRoot(QLAstRoot astRoot) {
        System.out.println("ROOT");
    }

    @Override
    public void visitForm(Form form) {
        System.out.println("FORM: " + form.getLabel());
    }

    @Override
    public void visitConstBinOpExpression(ConstBinOpExpression constBinOpExpression) {

    }

    @Override
    public void visitIfBlock(IfBlock ifBlock) {
        System.out.println("IFBLOCK:");
    }

    @Override
    public void visitLabel(Label label) {

    }

    @Override
    public void visitLabelBinOpExpression(LabelBinOpExpression labelBinOpExpression) {

    }

    @Override
    public void visitLabelExpression(LabelExpression labelExpression) {

    }

    @Override
    public void visitLineElement(LineElement lineElement) {

    }

    @Override
    public void visitQuestion(Question question) {
        System.out.println("Q: " + question.getQuestion());
    }

    @Override
    public void visitTypeExpression(TypeExpression typeExpression) {

    }

    @Override
    public void visitUnOpExpression(UnOpExpression unOpExpression) {

    }
}