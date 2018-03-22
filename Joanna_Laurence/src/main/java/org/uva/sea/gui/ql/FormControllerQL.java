package org.uva.sea.gui.ql;

import javafx.scene.layout.VBox;
import org.uva.sea.gui.BaseFormController;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;

import java.util.HashMap;
import java.util.Map;

public class FormControllerQL extends BaseFormController {
    protected Map<String, VBox> createContainer(EvaluationResult evaluationResult) {
        HashMap<String, VBox> panes = new HashMap<>();
        panes.put("default", this.container); //TODO
        return panes;
    }
}
