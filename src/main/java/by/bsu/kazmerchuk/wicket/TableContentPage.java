package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.wicket.base.AbstractBasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TableContentPage
    extends AbstractBasePage {

    long entityId;

    public TableContentPage(PageParameters parameters) {
        super(parameters);

        entityId = parameters.get("id").toLong();

        add(new TableContentPanel("panel", entityId));
    }
}
