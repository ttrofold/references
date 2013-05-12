package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.domain.TableDef;
import by.bsu.kazmerchuk.services.TableDefService;
import by.bsu.kazmerchuk.wicket.interfaces.IUpdateListener;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModalRenameTablePanel extends Panel {
    private long entityId;
    private IUpdateListener listener;
    private TableDef tableDef;

    @SpringBean
    TableDefService tableDefService;

    public ModalRenameTablePanel(
            String id,
            long entityId,
            IUpdateListener listener) {
        super(id);

        this.entityId = entityId;
        this.listener = listener;

        addContent();
    }

    private void addContent() {
        add(new Form<Void>("form") {

            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new RequiredTextField<String>(
                        "text",
                        new PropertyModel(ModalRenameTablePanel.this, "name")));
                add(new Button("submit").add(new AjaxFormSubmitBehavior(this, "onclick") {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        ModalWindow.closeCurrent(target);
                        clearFields();
                        tableDefService.renameTableDef(getTableDef());
                        listener.updated(target);
                    }
                }));
            }

            private void clearFields() {
                clearInput();
            }
        });
    }

    private TableDef getTableDef() {
        return tableDef == null? tableDef = tableDefService.getById(entityId) : tableDef;
    }

    public String getName() {
        return getTableDef().getName();
    }

    public void setName(String newName) {
        getTableDef().setName(newName);
    }

    @Override
    protected void onDetach() {
        super.onDetach();

        tableDef = null;
    }
}
