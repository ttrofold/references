package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.wicket.base.AbstractBasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AllTablesPage
        extends AbstractBasePage {
    public AllTablesPage(PageParameters parameters) {
        super(parameters);

        addPanel();
    }

    private void addPanel() {
        add(new AllTablesPanel("allTablesPanel"));
    }
}
