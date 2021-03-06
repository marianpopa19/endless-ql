package org.uva.sea.languages.qls.parser.visitor;

import org.uva.sea.languages.qls.parser.elements.Page;
import org.uva.sea.languages.qls.parser.elements.Parameter;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;
import org.uva.sea.languages.qls.parser.elements.specification.DefaultStyle;
import org.uva.sea.languages.qls.parser.elements.specification.Question;
import org.uva.sea.languages.qls.parser.elements.specification.Section;
import org.uva.sea.languages.qls.parser.elements.style.*;

public interface IStyleASTVisitor<T> {

    T visit(Stylesheet node);

    T visit(Parameter node);

    T visit(Page node);

    T visit(Color node);

    T visit(Font node);

    T visit(FontSize node);

    T visit(Widget node);

    T visit(Width node);

    T visit(DefaultStyle node);

    T visit(Question node);

    T visit(Section node);

}
