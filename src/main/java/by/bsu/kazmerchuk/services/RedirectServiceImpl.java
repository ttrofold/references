package by.bsu.kazmerchuk.services;

import org.apache.wicket.Page;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class RedirectServiceImpl
    implements RedirectService {
    @Override
    public void redirectTo(Class<? extends Page> pageClass) {
        RequestCycle.get().setResponsePage(pageClass);
    }

    @Override
    public void redirectToEntityPage(Class<? extends Page> pageClass, long id) {
        RequestCycle.get().setResponsePage(pageClass, new PageParameters().add("id", id));
    }
}
