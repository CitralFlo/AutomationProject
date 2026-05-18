package dev.citralflo.automationproject.registration.functional;

import dev.citralflo.BaseTest;
import dev.citralflo.components.LoginForm;
import dev.citralflo.components.RegisterForm;
import dev.citralflo.models.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationFormTests extends BaseTest {

    RegisterForm registerForm;

    @BeforeEach
    void setup() {
        LoginForm loginForm = new LoginForm(page);
        loginForm.clickRegisterLink();

        assertTrue(page.url().contains("register.htm"));

        registerForm = new RegisterForm(page);
    }

    @Test
    @Tag("Functional")
    @DisplayName("Validation of first name field in registration form")
    void nameFieldValidation() {
        assertFalse(this.registerForm.isFieldErrorVisible(RegisterForm.Field.FIRST_NAME));

        registerForm.submit();

        assertTrue(this.registerForm.isFieldErrorVisible(RegisterForm.Field.FIRST_NAME));

        String errorMessageForField = registerForm.getFieldError(RegisterForm.Field.FIRST_NAME);

        assertTrue(errorMessageForField.contains("First name is required."));

        registerForm.fillField(RegisterForm.Field.FIRST_NAME, "John");
        registerForm.submit();

        assertFalse(this.registerForm.isFieldErrorVisible(RegisterForm.Field.FIRST_NAME));

    }

}
