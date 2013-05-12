package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.services.RedirectService;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TablePagePanel
        extends Panel {
    @SpringBean
    RedirectService redirectService;

    long id;

    public TablePagePanel(String id, long entityId) {
        super(id);

        this.id = entityId;
        addContent();
    }

    private void addContent() {
        add(new Link("modifyContent") {
            @Override
            public void onClick() {
                redirectService.redirectToEntityPage(TableContentPage.class, id);
            }
        });
        add(new Link("modifyStructure") {
            @Override
            public void onClick() {
                redirectService.redirectToEntityPage(TableStructurePage.class, id);
            }
        });

    }
}
