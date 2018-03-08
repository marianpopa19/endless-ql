from sys import exit, argv
from scanparse.qllex import LexTokenizer
from scanparse.qlyacc import QLParser
from visitors.render import Render
from PyQt5.QtCore import pyqtSlot
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QDialog
from PyQt5.QtWidgets import QDialogButtonBox
from PyQt5.QtWidgets import QFormLayout
from PyQt5.QtWidgets import QGroupBox
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QMessageBox
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QSpinBox
from PyQt5.QtWidgets import QDoubleSpinBox
from PyQt5.QtWidgets import QCalendarWidget
from json import dumps


class Dialog(QDialog):
    def __init__(self, form):
        super(Dialog, self).__init__()
        self.form = form
        self.formGroupBox = QGroupBox(form.identifier)
        self.create_form(form)

        buttonBox = QDialogButtonBox(QDialogButtonBox.Ok | QDialogButtonBox.Cancel)
        buttonBox.accepted.connect(self.accept)
        buttonBox.rejected.connect(self.reject)

        mainLayout = QVBoxLayout()
        mainLayout.addWidget(self.formGroupBox)
        mainLayout.addWidget(buttonBox)
        self.setLayout(mainLayout)

        self.setWindowTitle("Form")

    def create_form(self, form):
        layout = QFormLayout()

        # TODO evaluate and check show field of question
        for question in form.block:
            question.pyqt5_render(layout)

        self.formGroupBox.setLayout(layout)

    # TODO output json
    @pyqtSlot()
    def accept(self):
        result = {}

        for child in self.formGroupBox.children()[1:]:
            key = self.form.find_question_of_widget(child)

            if key:
                result[key] = child.value()

        print(result)

        with open('out.json', 'w') as file:
            file.write(dumps(result))

        self.close()
        QMessageBox.information(self, 'Submission', 'Your answers have been submitted successfully.', QMessageBox.Ok, QMessageBox.Ok)
        # self.close()

    @pyqtSlot()
    def reject(self):
        # QMessageBox.warning(self, 'Warning', 'Are you sure you want to cancel the questionnaire?', QMessageBox.Ok, QMessageBox.Ok)
        self.hide()


if __name__ == '__main__':
    with open('../tests/test3.ql') as f:
        data = f.read()

    parser = QLParser()
    lexer = LexTokenizer()
    ast = parser.parser.parse(data, lexer.lexer)

    visitor = Render()
    visitor.visit(ast)

    app = QApplication(argv)
    dialog = Dialog(visitor.form)
    exit(dialog.exec_())