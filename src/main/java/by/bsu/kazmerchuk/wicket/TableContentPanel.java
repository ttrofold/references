package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.domain.Row;
import by.bsu.kazmerchuk.domain.Table;
import by.bsu.kazmerchuk.services.FetchTableService;
import by.bsu.kazmerchuk.wicket.base.ClassCssHeaderItem;
import by.bsu.kazmerchuk.wicket.interfaces.IUpdateListener;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TableContentPanel extends Panel implements IUpdateListener {

    private long entityId;

    @SpringBean
    FetchTableService fetchTableService;

    public TableContentPanel(String id, long entityId) {
        super(id);

        this.entityId = entityId;

        setOutputMarkupId(true);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(new ClassCssHeaderItem(this.getClass(), new PageParameters()));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Table table = fetchTableService.fetch(entityId);

        add(new AjaxLink("addRowLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                table.generateNewRow();
                target.add(TableContentPanel.this);
            }
        });

        add(new DefaultDataTable<Row, Void>(
                "data",
                table.getTableDef().getColumnDefList(),
                table,
                Integer.MAX_VALUE));
    }


    @Override
    public void updated(AjaxRequestTarget target) {
        target.add(this);
    }
}
