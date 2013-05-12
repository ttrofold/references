package by.bsu.kazmerchuk.wicket.base;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ClassCssHeaderItem extends CssReferenceHeaderItem {

    public ClassCssHeaderItem(Class<?> scope, PageParameters pageParameters) {
        super(new ClassCssResourceReference(scope), pageParameters, "", "");
    }
}
