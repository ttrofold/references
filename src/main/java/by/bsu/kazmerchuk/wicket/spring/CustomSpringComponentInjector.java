package by.bsu.kazmerchuk.wicket.spring;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;

public class CustomSpringComponentInjector
    extends SpringComponentInjector {


    public CustomSpringComponentInjector(WebApplication webapp) {
        super(webapp,
                (ApplicationContext)
                        webapp.getServletContext().getAttribute("appContext"));
    }
}
