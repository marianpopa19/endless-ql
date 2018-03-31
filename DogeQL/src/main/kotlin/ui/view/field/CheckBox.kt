package ui.view.field

import tornadofx.bind
import tornadofx.checkbox
import ui.model.BooleanViewModel

class CheckBox(question: BooleanViewModel) : QuestionField(question) {

    override val root = checkbox {
        bind(question.booleanValue)

        attachListener(selectedProperty())
    }
}