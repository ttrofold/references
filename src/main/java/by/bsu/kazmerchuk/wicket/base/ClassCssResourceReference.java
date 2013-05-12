package by.bsu.kazmerchuk.wicket.base;

import org.apache.wicket.request.resource.CssResourceReference;

public class ClassCssResourceReference extends CssResourceReference {
    public ClassCssResourceReference(Class<?> scope) {
        super(scope, scope.getSimpleName() + ".css");
    }
}
