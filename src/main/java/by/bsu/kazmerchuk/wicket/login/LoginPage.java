package by.bsu.kazmerchuk.wicket.login;

import by.bsu.kazmerchuk.wicket.base.AbstractBasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LoginPage extends AbstractBasePage {
	private static final long serialVersionUID = 1L;

	public LoginPage(final PageParameters parameters) {
		super(parameters);

        add(new LoginPanel("loginPanel"));
    }
}
