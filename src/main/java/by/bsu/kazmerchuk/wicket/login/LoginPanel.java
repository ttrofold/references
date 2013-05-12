package by.bsu.kazmerchuk.wicket.login;

import by.bsu.kazmerchuk.services.LoginService;
import by.bsu.kazmerchuk.wicket.base.ClassCssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class LoginPanel extends Panel {

    @SpringBean
    LoginService loginService;

    Form form;
    TextField loginTextField;
    TextField<String> passwordTextField;

    public LoginPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("message", new ResourceModel("login.message")));

        form = new Form("loginForm") {
            @Override
            protected void onSubmit() {
                loginService.doLogin(
                        loginTextField.getDefaultModelObjectAsString(),
                        passwordTextField.getDefaultModelObjectAsString());
            }
        };
        add(form);

        loginTextField = new TextField<String>("loginTextField", Model.of(""));
        passwordTextField = new PasswordTextField("passwordTextField", Model.of(""));


        form.add(loginTextField);
        form.add(passwordTextField);

        form.add(new Button("submit"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(new ClassCssHeaderItem(LoginPanel.class, getPage().getPageParameters()));
    }
}
