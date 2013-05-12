package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.wicket.base.AbstractBasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TablePage
    extends AbstractBasePage {

    private long id;

    public TablePage(PageParameters parameters) {
        super(parameters);

        id = parameters.get("id").toLong();

        addContent();
    }

    private void addContent() {
        add(new TablePagePanel("panel", id));
    }
}
