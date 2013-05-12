package by.bsu.kazmerchuk.wicket;

import by.bsu.kazmerchuk.wicket.login.LoginPage;
import by.bsu.kazmerchuk.wicket.spring.CustomSpringComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication
{    	

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return LoginPage.class;
	}

	@Override
	public void init()
	{
		super.init();

		// add your configuration here
        getComponentInstantiationListeners().add(new CustomSpringComponentInjector(this));
	}
}
