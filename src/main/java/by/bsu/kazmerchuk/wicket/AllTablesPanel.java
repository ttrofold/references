package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.domain.TableDef;
import by.bsu.kazmerchuk.services.RedirectService;
import by.bsu.kazmerchuk.services.TableDefService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class AllTablesPanel
        extends Panel {

    @SpringBean
    RedirectService redirectService;

    @SpringBean
    TableDefService tableDefService;

    public AllTablesPanel(String id) {
        super(id);

        addContent();
    }

    private void addContent() {
        ListView<TableDef> listView = new ListView<TableDef>("list", getTableDefModel()) {
            @Override
            protected void populateItem(final ListItem<TableDef> item) {
                Link link = new Link("link") {
                    @Override
                    public void onClick() {
                        redirectService.redirectToEntityPage(
                                TablePage.class,
                                item.getModelObject().getId());
                    }
                };

                link.add(new Label("label", Model.of(item.getModelObject().getName())));
                item.add(link);
            }
        };
        add(listView);
    }

    private IModel<? extends List<? extends TableDef>> getTableDefModel() {
        return new LoadableDetachableModel<List<? extends TableDef>>() {
            @Override
            public List<? extends TableDef> load() {
                return tableDefService.getAllTableDefs();
            }
        };
    }

}
