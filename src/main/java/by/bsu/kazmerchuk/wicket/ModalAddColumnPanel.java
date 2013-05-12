package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.domain.ColumnDef;
import by.bsu.kazmerchuk.domain.TypeDef;
import by.bsu.kazmerchuk.services.ColumnDefService;
import by.bsu.kazmerchuk.wicket.interfaces.IUpdateListener;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.List;

public class ModalAddColumnPanel
        extends Panel {

    @SpringBean
    ColumnDefService columnDefService;

    private long entityId;
    private IUpdateListener listener;

    public ModalAddColumnPanel(String id, long entityId, IUpdateListener listener) {
        super(id);

        this.entityId = entityId;
        this.listener = listener;
        addContent();
    }

    private void addContent() {
        add(new Form<ColumnDef>("form", Model.of(new ColumnDef())) {
            @Override
            protected void onSubmit() {

            }

            @Override
            protected void onInitialize() {
                super.onInitialize();

                setOutputMarkupId(true);
                add(new TextField<String>("name", new PropertyModel(getModel(), "name")));
                add(new DropDownChoice<TypeDef>("type",
                        new PropertyModel(getModel(), "typeDef"),
                        getChoices(),
                        new IChoiceRenderer<TypeDef>() {
                            @Override
                            public Object getDisplayValue(TypeDef object) {
                                return object.name();
                            }

                            @Override
                            public String getIdValue(TypeDef object, int index) {
                                return TypeDef.values()[index].name();
                            }
                        }) {
                    @Override
                    protected CharSequence getDefaultChoice(String selectedValue) {
                        return selectedValue == null ? "" : selectedValue;
                    }
                }.setNullValid(true).setRequired(true));

                add(new Button("submit").add(new AjaxFormSubmitBehavior(this, "onclick") {
                    @Override
                    protected void onAfterSubmit(AjaxRequestTarget target) {
                        columnDefService.addNew(entityId, getFormModel().getObject());
                        clearFields();
                        ModalWindow.closeCurrent(target);
                        target.add(ModalAddColumnPanel.this);
                        listener.updated(target);
                    }
                }));
            }

            public void clearFields() {
                clearInput();
            }

            public IModel<ColumnDef> getFormModel() {
                return getModel();
            }
        });
    }

    private List<? extends TypeDef> getChoices() {
        return Arrays.asList(TypeDef.values());
    }


}
