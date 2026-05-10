package dev.citralflo.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginForm {

    private final Page page;

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator forgotLoginLink;
    private final Locator registerLink;

    public LoginForm(Page page) {
        this.page = page;

        Locator loginPanel = this.page.locator("#loginPanel");

        this.usernameInput = loginPanel.getByLabel("Username");
        this.passwordInput = loginPanel.getByLabel("Password");

        this.loginButton = loginPanel.getByRole(
                AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName("Log In")
        );

        this.forgotLoginLink = loginPanel.getByRole(
                AriaRole.LINK,
                new Locator.GetByRoleOptions().setName("Forgot login info?")
        );
        this.registerLink = loginPanel.getByRole(
                AriaRole.LINK,
                new Locator.GetByRoleOptions().setName("Register")
        );
    }

    public void clickRegisterLink() {
        this.registerLink.click();
    }


}
