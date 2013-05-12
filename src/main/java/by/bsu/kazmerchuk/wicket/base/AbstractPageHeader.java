package by.bsu.kazmerchuk.wicket.base;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class AbstractPageHeader extends Panel {
    public AbstractPageHeader(String id) {
        super(id);

        add(new Label("header"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(
                new CssReferenceHeaderItem(
                        new ClassCssResourceReference(AbstractPageHeader.class), getPage().getPageParameters(), "", ""));
    }
}
