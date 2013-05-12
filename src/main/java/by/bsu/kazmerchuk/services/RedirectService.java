package by.bsu.kazmerchuk.services;

import org.apache.wicket.Page;

public interface RedirectService {

    void redirectTo(Class<? extends Page> pageClass);

    void redirectToEntityPage(Class<? extends Page> pageClass, long id);
}
