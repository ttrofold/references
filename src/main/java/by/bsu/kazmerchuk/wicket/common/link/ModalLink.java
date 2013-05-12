package by.bsu.kazmerchuk.wicket.common.link;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IModel;

public class ModalLink extends AjaxLink<String> {

    ModalWindow modal;
    Component modalContent;

    public ModalLink(String id, ModalWindow window, Component modalContent) {
        super(id, null);

        modal = window;
        this.modalContent = modalContent;

        customizeModal();
    }

    protected void customizeModal() {
    }

    @Override
    public void onClick(AjaxRequestTarget target) {

        modal.setContent(modalContent.setMarkupId(modal.getContentId()));
        target.prependJavaScript("Wicket.Window.unloadConfirmation = false;");
        modal.show(target);
    }
}
