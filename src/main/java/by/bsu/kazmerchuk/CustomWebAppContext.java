package by.bsu.kazmerchuk;

import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CustomWebAppContext
    extends WebAppContext
    implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    protected void startContext() throws Exception {
        _scontext.setAttribute(
                "appContext",
                applicationContext);

        super.startContext();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
}
