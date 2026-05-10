package dev.citralflo.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import dev.citralflo.models.UserData;

public class RegisterForm {

    private final Page page;

    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator addressInput;
    private final Locator cityInput;
    private final Locator stateInput;
    private final Locator zipCodeInput;
    private final Locator phoneInput;
    private final Locator ssnInput;
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator confirmPasswordInput;
    private final Locator registerButton;

    public RegisterForm(Page page) {
        this.page = page;

        Locator registerForm = page.locator("#customerForm");

        this.firstNameInput = registerForm.locator("id=customer.firstName");
        this.lastNameInput = registerForm.locator("id=customer.lastName");
        this.addressInput = registerForm.locator("id=customer.address.street");
        this.cityInput = registerForm.locator("id=customer.address.city");
        this.stateInput = registerForm.locator("id=customer.address.state");
        this.zipCodeInput = registerForm.locator("id=customer.address.zipCode");
        this.phoneInput = registerForm.locator("id=customer.phoneNumber");
        this.ssnInput = registerForm.locator("id=customer.ssn");
        this.usernameInput = registerForm.locator("id=customer.username");
        this.passwordInput = registerForm.locator("id=customer.password");
        this.confirmPasswordInput = registerForm.locator("id=repeatedPassword");
        this.registerButton = registerForm.locator("input[value='Register']");
    }

    public void fill(UserData user) {
        this.firstNameInput.fill(user.firstName);
        this.lastNameInput.fill(user.lastName);
        this.addressInput.fill(user.address);
        this.cityInput.fill(user.city);
        this.stateInput.fill(user.state);
        this.zipCodeInput.fill(user.zipCode);
        this.phoneInput.fill(user.phone);
        this.ssnInput.fill(user.ssn);
        this.usernameInput.fill(user.username);
        this.passwordInput.fill(user.password);
        this.confirmPasswordInput.fill(user.password);
    }

    public void submit() {
        registerButton.click();
    }

    public String getErrorMessageForField(String fieldName) {
        return page.locator("id=" + fieldName + ".errors").innerText();
    }

    public void fillFirstName(String name) {
        this.firstNameInput.fill(name);
    }

}
