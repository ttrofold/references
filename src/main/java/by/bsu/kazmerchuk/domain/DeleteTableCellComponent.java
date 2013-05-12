package by.bsu.kazmerchuk.domain;

import by.bsu.kazmerchuk.services.FetchTableService;
import by.bsu.kazmerchuk.wicket.common.link.BlueAjaxLink;
import by.bsu.kazmerchuk.wicket.interfaces.IUpdateListener;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

public class DeleteTableCellComponent extends Panel {

    @SpringBean
    FetchTableService fetchTableService;

    IModel<Row> row;

    public DeleteTableCellComponent(String componentId, IModel<Row> row) {
        super(componentId);

        this.row = row;
        addContent();
    }

    private void addContent() {
        add(new BlueAjaxLink("deleteLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                row.getObject().setDeleted(true);
                fetchTableService.deleteRow(row.getObject());
                walk(target);
            }
        });
    }

    private void walk(final AjaxRequestTarget target) {
        getPage().visitChildren(new IVisitor<Component, Object>() {
            @Override
            public void component(Component object, IVisit<Object> visit) {
                if(object instanceof IUpdateListener)
                    ((IUpdateListener) object).updated(target);
            }
        });
    }
}
