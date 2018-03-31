package qls.models.ast

import qls.models._

sealed trait DisplayItem

case class Section(title: StringValue, content: List[DisplayItem])
    extends DisplayItem

case class Question(identifier: Identifier,
                    styling: Option[List[StylingConfiguration]])
    extends DisplayItem

case class DefaultDecl(nodeType: NodeType,
                       styling: List[StylingConfiguration])
    extends DisplayItem

sealed trait StylingConfiguration {
  def value: Expression
}

case class WidthStyling(value: IntegerValue) extends StylingConfiguration
case class FontStyling(value: StringValue) extends StylingConfiguration
case class FontSizeStyling(value: IntegerValue) extends StylingConfiguration
case class ColorStyling(value: StringValue) extends StylingConfiguration
case class WidgetStyling(value: WidgetExpression) extends StylingConfiguration