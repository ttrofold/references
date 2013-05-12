package by.bsu.kazmerchuk.wicket.base;

import by.bsu.kazmerchuk.wicket.interfaces.IModalProvider;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * This one shouldn't perform any initialization before having been added to Page!
 */
public abstract class ModalAwarePanel extends Panel {

    public ModalAwarePanel(String id) {
        super(id);
    }

    public ModalAwarePanel(String id, IModel<?> model) {
        super(id, model);
    }

    protected ModalWindow getModal() {
        return ((IModalProvider) getPage()).getModal();
    }
}
