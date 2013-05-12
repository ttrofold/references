package by.bsu.kazmerchuk.wicket.main;

import by.bsu.kazmerchuk.wicket.AllTablesPage;
import by.bsu.kazmerchuk.wicket.ModalAddTablePanel;
import by.bsu.kazmerchuk.wicket.base.ModalAwarePanel;
import by.bsu.kazmerchuk.wicket.common.link.ModalLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class MainPageChoicePanel
        extends ModalAwarePanel {

    public MainPageChoicePanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        getModal().setContent(new ModalAddTablePanel(getModal().getContentId()));
        getModal().setInitialHeight(100);
        getModal().setInitialWidth(200);
        getModal().setTitle("Add table");

        add(new BookmarkablePageLink<String>("allTables", AllTablesPage.class));
        add(new ModalLink(
                "addTable",
                getModal(),
                new ModalAddTablePanel(getModal().getContentId())));
    }
}
