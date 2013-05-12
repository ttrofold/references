package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.domain.TableDef;
import by.bsu.kazmerchuk.services.RedirectService;
import by.bsu.kazmerchuk.services.TableDefService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModalAddTablePanel
    extends Panel {

    @SpringBean
    TableDefService tableDefService;
    @SpringBean
    RedirectService redirectService;

    public ModalAddTablePanel(String id) {
        super(id);

        addContent();
    }

    private void addContent() {
        add(new Form<TableDef>("form", Model.of(new TableDef())) {
            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new TextField<String>("name", new PropertyModel<String>(getModel(), "name")));
                add(new Button("submit").add(new AjaxFormSubmitBehavior(this, "onclick") {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target) {
                        clearInput();
                        ModalWindow.closeCurrent(target);
                        tableDefService.addNew(getModelObject());
                        redirectService.redirectToEntityPage(TablePage.class, tableDefService.getMaxTableDefId());
                    }
                })) ;
            }
        });
    }
}
