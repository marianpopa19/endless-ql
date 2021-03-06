package qlviz.gui.renderer.javafx;

import qlviz.gui.renderer.layout.QuestionNotFoundException;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.PropertySetting;
import qlviz.model.style.Widget;

import java.util.ArrayList;
import java.util.List;

public class ChainedWidgetFinder implements WidgetFinder {

    private final List<WidgetFinder> finders;

    public ChainedWidgetFinder(List<WidgetFinder> finders) {
        this.finders = finders;
    }

    @Override
    public Widget findWidgetForQuestion(QuestionViewModel questionViewModel) throws QuestionNotFoundException, WidgetNotFoundException {
        for (WidgetFinder widgetFinder : this.finders) {
            try
            {
                Widget widget = widgetFinder.findWidgetForQuestion(questionViewModel);
                if (widget != null) {
                    return widget;
                }
            }
            catch (WidgetNotFoundException e){
                // Try the next finder
            }
        }
        throw new WidgetNotFoundException();
    }

    @Override
    public List<PropertySetting> findDefaultProperties(QuestionViewModel questionViewModel) throws QuestionNotFoundException {
         for (WidgetFinder widgetFinder : this.finders) {
            List<PropertySetting> propertySettings = widgetFinder.findDefaultProperties(questionViewModel);
            if (propertySettings.size() > 0) {
                return propertySettings;
            }
        }
        return new ArrayList<>();
    }
}
