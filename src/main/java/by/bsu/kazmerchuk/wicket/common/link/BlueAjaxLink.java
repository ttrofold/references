package by.bsu.kazmerchuk.wicket.common.link;

import by.bsu.kazmerchuk.wicket.base.ClassCssHeaderItem;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BlueAjaxLink extends AjaxLink {

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(new ClassCssHeaderItem(BlueAjaxLink.class, new PageParameters()));
    }

    public BlueAjaxLink(String id) {
        super(id);
    }

    public BlueAjaxLink(String id, IModel model) {
        super(id, model);
    }


}
