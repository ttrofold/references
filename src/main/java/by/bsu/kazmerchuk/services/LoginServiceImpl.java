package by.bsu.kazmerchuk.services;

import by.bsu.kazmerchuk.wicket.main.MainPage;
import org.apache.log4j.Logger;

public class LoginServiceImpl
        implements LoginService {

    RedirectService redirectService;

    private static Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

    @Override
    public boolean doLogin(String username, String password) {

        LOGGER.info("Logged in");

        redirectService.redirectTo(MainPage.class);
        return true;
    }

    public void setRedirectService(RedirectService redirectService) {
        this.redirectService = redirectService;
    }
}
