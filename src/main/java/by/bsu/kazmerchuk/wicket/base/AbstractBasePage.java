package by.bsu.kazmerchuk.wicket.base;

import by.bsu.kazmerchuk.wicket.interfaces.IModalProvider;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AbstractBasePage extends WebPage implements IModalProvider {

    private ModalWindow modal;
    public AbstractBasePage(PageParameters parameters) {
        super(parameters);

        add(new AbstractPageHeader("header"));
        add(modal = new ModalWindow("modal"));
//        add(new AbstractPageFooter("footer"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(
                new ClassCssHeaderItem(AbstractBasePage.class, getPageParameters()));
    }

    @Override
    public ModalWindow getModal() {
        return modal;
    }
}
