package by.bsu.kazmerchuk.domain;

import by.bsu.kazmerchuk.services.FetchTableService;
import by.bsu.kazmerchuk.wicket.base.ClassCssHeaderItem;
import by.bsu.kazmerchuk.wicket.base.ModalAwarePanel;
import by.bsu.kazmerchuk.wicket.interfaces.IUpdateListener;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.Serializable;

public class TableCellComponent extends ModalAwarePanel implements IUpdateListener {

    @SpringBean
    FetchTableService fetchTableService;

    private TableCell tableCell;

    public TableCellComponent(String id, TableCell tableCell) {
        super(id);

        this.tableCell = tableCell;
        setOutputMarkupId(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        getModal().setInitialHeight(100);
        getModal().setInitialWidth(250);
        getModal().setTitle(Model.of("Change value"));

        add(new Label(
                "label",
                new TableCellPropertyModel(getTableCellModel(), "value"))
                .add(new AjaxEventBehavior("onclick") {
                    @Override
                    protected void onEvent(AjaxRequestTarget target) {
                        getModal().setContent(
                                new ModalTableCellEditPanel(
                                        getModal().getContentId(),
                                        tableCell,
                                        TableCellComponent.this));
                        getModal().show(target);
                    }
                }));


    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(new ClassCssHeaderItem(this.getClass(), new PageParameters()));
    }

    @Override
    public void updated(AjaxRequestTarget target) {
        target.add(this);
    }

    public IModel<TableCell> getTableCellModel() {
        return new AbstractReadOnlyModel<TableCell>() {
            @Override
            public TableCell getObject() {
                return tableCell;
            }
        };
    }

    private class ModalTableCellEditPanel extends Panel {

        private TableCell tableCell;
        private IUpdateListener listener;

        public ModalTableCellEditPanel(
                String id, TableCell tableCell, IUpdateListener listener) {
            super(id);

            this.tableCell = tableCell;
            this.listener = listener;

        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            add(new Form<Void>("form") {
                @Override
                protected void onInitialize() {
                    super.onInitialize();

                    // WARN here
                    add(new TextField<Object>("edit", new PropertyModel(ModalTableCellEditPanel.this, "value")));
                    add(new Button("submit").add(new AjaxFormSubmitBehavior(this, "onclick") {
                        @Override
                        protected void onSubmit(AjaxRequestTarget target) {
                            ModalWindow.closeCurrent(target);
                            fetchTableService.updateRowRegardingCell(tableCell);
                            clearForm();
                            listener.updated(target);
                        }

                        private void clearForm() {
                            clearInput();
                        }
                    }));
                }
            });


        }

        public Object getValue() {
            return tableCell.getValue();
        }

        public void setValue(Object value) {
            this.tableCell.setValue(value);
        }
    }

    private class TableCellPropertyModel extends PropertyModel<Object> {


        /**
         * Construct with a wrapped (IModel) or unwrapped (non-IModel) object and a property expression
         * that works on the given model.
         *
         * @param modelObject The model object, which may or may not implement IModel
         * @param expression  Property expression for property access
         */
        public TableCellPropertyModel(Object modelObject, String expression) {
            super(modelObject, expression);
        }

        @Override
        public Object getObject() {
            if(super.getObject() == null || super.getObject().toString().trim().equals("")) {
                return "[No value]";
            }

            return super.getObject();
        }
    }
}
