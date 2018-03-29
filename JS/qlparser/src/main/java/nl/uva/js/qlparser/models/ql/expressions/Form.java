package nl.uva.js.qlparser.models.ql.expressions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.helpers.JsonRepresentationHelper;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Form implements FormExpression {

    @JsonIgnore @NonNull private String name;
    @JsonIgnore private LinkedList<FormExpression> formExpressions;

    @JsonIgnore private HashMap<String, FormExpression> expressionsByName;

    @Override
    @JsonIgnore
    public LinkedList<Component> getComponents() {
        expressionsByName = new HashMap<>();

        LinkedList<Component> components = new LinkedList<>();

        NonNullRun.consumer(formExpressions, fe -> {
            for (FormExpression formExpression : fe) {
                List<Component> formExpressionComponents = formExpression.getComponents();
                components.addAll(formExpressionComponents);

                expressionsByName.put(formExpression.getName(), formExpression);
            }
        });

        return components;
    }

    @Override
    public void checkType() {
        formExpressions.forEach(Expression.TypeCheckable::checkType);
    }

    @JsonProperty("name")
    public String getHumanizedName() {
        return StringUtils.capitalize(
                Arrays.stream(StringUtils.splitByCharacterTypeCamelCase(name))
                        .map(String::toLowerCase)
                        .filter(StringUtils::isNotBlank)
                        .collect(Collectors.joining(" ")));
    }

    @JsonProperty("expressionReferences")
    public List<Object> getJsonRepresentation() {
        return JsonRepresentationHelper.visualInformation(formExpressions);
    }
}
