package dev.citralflo.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import dev.citralflo.models.UserData;

public class RegisterForm {

    private final Locator registerForm;

    public enum Field {
        FIRST_NAME("customer.firstName"),
        LAST_NAME("customer.lastName"),
        ADDRESS("customer.address.street"),
        CITY("customer.address.city"),
        STATE("customer.address.state"),
        ZIP_CODE("customer.address.zipCode"),
        PHONE("customer.phoneNumber"),
        SSN("customer.ssn"),
        USERNAME("customer.username"),
        PASSWORD("customer.password"),
        CONFIRM_PASSWORD("repeatedPassword");

        private final String id;

        Field(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }


    public RegisterForm(Page page) {
        this.registerForm = page.locator("#customerForm");
    }

    public void fillField(Field field, String value) {
        this.registerForm.locator("id=" + field.getId()).fill(value);
    }

    public void fill(UserData user) {
        this.fillField(Field.FIRST_NAME, user.firstName);
        this.fillField(Field.LAST_NAME, user.lastName);
        this.fillField(Field.ADDRESS, user.address);
        this.fillField(Field.CITY, user.city);
        this.fillField(Field.STATE, user.state);
        this.fillField(Field.ZIP_CODE, user.zipCode);
        this.fillField(Field.PHONE, user.phone);
        this.fillField(Field.SSN, user.ssn);
        this.fillField(Field.USERNAME, user.username);
        this.fillField(Field.PASSWORD, user.password);
        this.fillField(Field.CONFIRM_PASSWORD, user.password);
    }


    public void submit() {
        this.registerForm.locator("input[value='Register']").click();
    }

    public String getFieldError(Field field) {
        Locator errorLocator = this.registerForm.locator("id=" + field.getId() + ".errors");

        if (errorLocator.count() == 0) {
            return "";
        }

        if (!errorLocator.isVisible()) {
            return "";
        }

        return errorLocator.innerText();
    }

    public boolean isFieldErrorVisible(Field field) {
        return this.registerForm.locator("id=" + field.getId() + ".errors").isVisible();
    }

}
