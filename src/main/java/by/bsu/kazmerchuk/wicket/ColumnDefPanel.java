package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.domain.ColumnDef;
import by.bsu.kazmerchuk.domain.TypeDef;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class ColumnDefPanel
    extends Panel {

    TableStructurePanel.FormCallback callback;

    public ColumnDefPanel(
            String id,
            IModel<ColumnDef> model,
            TableStructurePanel.FormCallback callback) {
        super(id, model);

        this.callback = callback;
        addFields();
    }



    protected void addFields() {
        add(new RequiredTextField<String>("name", new NotifyingPropertyModel(getDefaultModel(), "name")));
        add(new Label("type", new PropertyModel<TypeDef>(getDefaultModel(), "typeDef")));

    }

    private class NotifyingPropertyModel extends PropertyModel<String> {

        /**
         * Construct with a wrapped (IModel) or unwrapped (non-IModel) object and a property expression
         * that works on the given model.
         *
         * @param modelObject The model object, which may or may not implement IModel
         * @param expression  Property expression for property access
         */
        public NotifyingPropertyModel(Object modelObject, String expression) {
            super(modelObject, expression);
        }

        @Override
        public void setObject(String object) {
            super.setObject(object);

            callback.notifyModelChanged();
        }
    }
}
