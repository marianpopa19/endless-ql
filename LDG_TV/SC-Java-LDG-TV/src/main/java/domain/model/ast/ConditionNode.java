package domain.model.ast;

import java.util.ArrayList;
import java.util.List;

public class ConditionNode extends ASTNode {

    private List<Condition> conditions;
    private List<QuestionNode> questionNodes;
    private ElseNode elseNode;

    public ConditionNode(boolean visible) {
        this.questionNodes = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.elseNode = new ElseNode();
        this.setDisabled(visible);
    }

    /**
     * Add a QuestionNode to the elseNodes list.
     * @param elseNode QuestionNode to add.
     */
    public void addElseQuestion(QuestionNode elseNode) {
        this.elseNode.getQuestionNodes().add(elseNode);
    }

    /**
     * Adds a QuestionNode to the questionNodes list.
     * @param q QuestionNode to add
     */
    public void addQuestion(QuestionNode q){
        this.questionNodes.add(q);
    }

    /**
     * Adds a Condition to the conditions list.
     * @param c Condition to add
     */
    public void addCondition(Condition c){this.conditions.add(c);}

    /**
     * Checks whether the conditions for this ConditionNode are satisfied.
     * @return boolean whether conditions are satisfied.
     */
    public boolean checkConditions() {
        boolean temp = true;
        for (int i = 0; i < this.conditions.size(); i++) {
            Condition condition = this.conditions.get(i);
            String operator = condition.getOperator();
            if (operator != null) {
                switch (operator) {
                    case "||":
                        temp = temp || (Boolean) condition.getVariable().getValue().getValue();
                    case "&&":
                        temp = temp && (Boolean) condition.getVariable().getValue().getValue();
                }
            } else {
                temp = (Boolean) condition.getVariable().getValue().getValue();
            }
        }
        for (QuestionNode qan : this.getQuestionNodes()){
            qan.setDisabled(!temp);
        }
        for (QuestionNode qan : this.elseNode.getQuestionNodes()){
            qan.setDisabled(temp);
        }
        return temp;
    }
    public List<QuestionNode> getQuestionNodes() {
        return questionNodes;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public ElseNode getElseNode() {
        return elseNode;
    }
}