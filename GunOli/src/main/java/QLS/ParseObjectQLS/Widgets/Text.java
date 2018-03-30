package QLS.ParseObjectQLS.Widgets;

import QLS.Analysis.WidgetVisitorInterface;

public class Text extends Widget {
    Text(int line) {
        super(WidgetType.Text, line);
    }



    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
