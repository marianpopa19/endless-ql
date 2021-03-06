# Lars Lokhoff, Timo Dobber
# This class changes the render order according to QLS form, adds QLS style options

class QLSGuiBuilder(object):
    def __init__(self, gui, qls_ast):
        self.gui = gui
        self.qls_ast = qls_ast
        self.rendered_sections = {}
        self.ql_frame_order = []
        self.qls_frame_order = []

    def parseQLSAST(self, ql_frame_order):
        self.qls_frame_order = []
        self.ql_frame_order = ql_frame_order

        for page in self.qls_ast.pages:
            if page.name not in self.gui.pages:
                self.gui.addPage(page.name)
            else:
                self.gui.current_page = self.gui.pages[page.name]

            render_frame = self.gui.current_page
            default_int_style, default_text_style, default_bool_style = self.getDefaultStyleWidgets(page.default_style_widgets)

            self.parseQLSSection(page, default_int_style, default_text_style, default_bool_style)

        return self.qls_frame_order

    def parseQLSSection(self, section_holder, default_int_style, default_text_style, default_bool_style, parent_section=None):
        for section in section_holder.sections:
            if self.showOrRemoveSection(section):
                if section.name not in self.rendered_sections:
                    render_frame = self.gui.addSection(section.name, parent_section)
                    self.rendered_sections[section.name] = render_frame
                else:
                    render_frame = self.rendered_sections[section.name]

                default_int_style, default_text_style, default_bool_style = self.getDefaultStyleWidgets(section.default_style_widgets, default_int_style, default_text_style, default_bool_style)

                for question in section.questions:
                    if question.variable in self.ql_frame_order:
                        self.parseQLSQuestion(question, render_frame, default_int_style, default_text_style, default_bool_style)

                if section.sections != []:
                    self.parseQLSSection(section, default_int_style, default_text_style, default_bool_style, render_frame)

    def parseQLSQuestion(self, question, question_section, default_style_int, default_style_text, default_style_bool):
        old_question = self.gui.widget_settings[question.variable]
        if question.widget:
            options = self.extractWidgetOptions(question.widget.options)
            widget = [question.widget.widget, old_question[1], old_question[2], question_section, options[0], options[1], options[2], options[3], question.widget.min_value, question.widget.max_value]
            change_needed = True
        else:
            widget, change_needed = self.checkForDefaultWidget(old_question, question_section, default_style_int, default_style_text, default_style_bool)

        if change_needed:
            self.gui.widget_settings[question.variable] = widget
            self.qls_frame_order.append(question.variable)
        else:
            self.gui.widget_settings[question.variable][3] = question_section
            self.qls_frame_order.append(question.variable)

    def checkForDefaultWidget(self, old_question, question_section, default_style_int, default_style_text, default_style_bool):
        question_variable_type = old_question[1]

        if question_variable_type == "boolean" and default_style_bool:
            options = self.extractWidgetOptions(default_style_bool)
            if "radio" in default_style_bool.widget:
                return [old_question[0], question_variable_type, old_question[2], question_section, options[0], options[1], options[2], options[3]], True
            elif "dropdown" in default_style_bool.widget:
                return ["dropdown", question_variable_type, old_question[2], question_section, options[0], options[1], options[2], options[3]], True

        elif question_variable_type == "int" and old_question[0] != "assignment" and default_style_int:
            options = self.extractWidgetOptions(default_style_int)
            if default_style_int.widget == "text":
                return [old_question[0], question_variable_type, old_question[2], question_section, options[0], options[1], options[2], options[3]], True
            elif default_style_int.widget == "slider":
                return ["slider", question_variable_type, old_question[2], question_section, options[0], options[1], options[2], options[3], default_style_int.min_value, default_style_int.max_value], True
            elif default_style_int.widget == "spinbox":
                return ["spinbox", question_variable_type, old_question[2], question_section, options[0], options[1], options[2], options[3], default_style_int.min_value, default_style_int.max_value], True

        return old_question, False

    def extractWidgetOptions(self, default_style_options):
        options = ["#000000", 30, "Times", "12"]

        if default_style_options:
            if default_style_options.options and default_style_options.options.options:
                default_style_options = default_style_options.options.options   
        
            if "color" in  default_style_options:
                options[0] = default_style_options["color"]

            if "width" in default_style_options:
                options[1] = default_style_options["width"]

            if "font" in default_style_options:
                options[2] = default_style_options["font"]

            if "fontsize" in default_style_options:
                options[3] = default_style_options["fontsize"]

        return options

    def getDefaultStyleWidgets(self, default_style_widgets, old_default_int=None, old_default_text=None, old_default_bool=None):
        default_int = old_default_int
        default_text = old_default_text
        default_bool = old_default_bool

        for default_style in default_style_widgets:
            if default_style.options.variable_type == "int":
                default_int = default_style
            elif default_style.options.variable_type == "text":
                default_text = default_style
            elif default_style.options.variable_type == "boolean":
                default_bool = default_style

        return default_int, default_text, default_bool

    def showOrRemoveSection(self, section):
        for question in section.questions:
            if question.variable in self.ql_frame_order:
                return True
        if section.name in self.rendered_sections:
            self.rendered_sections[section.name].destroy()
            del self.rendered_sections[section.name]

        return False