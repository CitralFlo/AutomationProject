package dev.citralflo.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import dev.citralflo.models.UserData;

public class LoginForm {

    private static final String LOGIN_PANEL_SELECTOR = "#loginPanel";

    private static final String USERNAME_SELECTOR = "Username";
    private static final String PASSWORD_SELECTOR = "Password";

    private static final String LOGIN_LABEL = "Log In";
    private static final String RECOVER_PASSWORD_LABEL = "Forgot login info?";
    private static final String REGISTER_LABEL = "Register";

    private final Page page;

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator forgotLoginLink;
    private final Locator registerLink;

    public LoginForm(Page page) {
        this.page = page;

        Locator loginPanel = this.page.locator(LOGIN_PANEL_SELECTOR);

        this.usernameInput = loginPanel.getByLabel(USERNAME_SELECTOR);
        this.passwordInput = loginPanel.getByLabel(PASSWORD_SELECTOR);

        this.loginButton = loginPanel.getByRole(
                AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName(LOGIN_LABEL)
        );

        this.forgotLoginLink = loginPanel.getByRole(
                AriaRole.LINK,
                new Locator.GetByRoleOptions().setName(RECOVER_PASSWORD_LABEL)
        );
        this.registerLink = loginPanel.getByRole(
                AriaRole.LINK,
                new Locator.GetByRoleOptions().setName(REGISTER_LABEL)
        );
    }

    public void clickRegisterLink() {
        this.registerLink.click();
    }

    public void clickLoginButton() {
        this.loginButton.click();
    }

    public void clickForgotPasswordButton() {
        this.forgotLoginLink.click();
    }

    public void login(UserData userData) {
        this.fillUsernameInput(userData.username);
        this.fillPassword(userData.password);

        this.clickLoginButton();
    }

    private void fillPassword(String password) {
        this.passwordInput.fill(password);
    }

    private void fillUsernameInput(String username) {
        this.usernameInput.fill(username);
    }

}
