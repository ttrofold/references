package by.bsu.kazmerchuk.wicket.main;

import by.bsu.kazmerchuk.wicket.base.AbstractBasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class MainPage
    extends AbstractBasePage {


    public MainPage(PageParameters parameters) {
        super(parameters);

        add(new MainPageChoicePanel("choicePanel"));
    }
}
