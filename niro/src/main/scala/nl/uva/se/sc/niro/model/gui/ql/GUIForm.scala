package nl.uva.se.sc.niro.model.gui.ql

case class GUIForm(name: String, questions: Seq[GUIQuestion]) {
  def collectQuestionOnName(name: String): Seq[GUIQuestion] = questions.filter(_.id == name)
}