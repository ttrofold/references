package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.wicket.base.AbstractBasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TableStructurePage
    extends AbstractBasePage {

    long id;
    public TableStructurePage(PageParameters parameters) {
        super(parameters);

        id = parameters.get("id").toLong();
        addContent();
    }

    private void addContent() {
        add(new TableStructurePanel("panel", id));
    }
}
