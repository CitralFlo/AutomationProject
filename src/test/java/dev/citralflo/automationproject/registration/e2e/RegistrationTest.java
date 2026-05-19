package dev.citralflo.automationproject.registration.e2e;

import dev.citralflo.BaseTest;
import dev.citralflo.components.LoginForm;
import dev.citralflo.components.RegisterForm;
import dev.citralflo.components.SideBar;
import dev.citralflo.models.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationTest extends BaseTest {

    @Test
    @Tag("End2End")
    @DisplayName("Successful registration of a new user")
    void registerUser() {
        UserData userData = new UserData();

        System.out.println("Username: " + userData.username);
        System.out.println("Password: " + userData.password);

        LoginForm loginForm = new LoginForm(page);
        loginForm.clickRegisterLink();

        assertTrue(page.url().contains("register.htm"));

        RegisterForm registerForm = new RegisterForm(page);

        registerForm.submit();

        assertTrue(page.url().contains("register.htm"));
        assertTrue(registerForm.isFieldErrorVisible(RegisterForm.Field.FIRST_NAME));

        registerForm.fill(userData);
        registerForm.submit();

        assertTrue(page.content().contains("Welcome"));

        SideBar sideBar = new SideBar(page);

        String firstAndLastName = userData.firstName + " " + userData.lastName;
        String sideBarName = sideBar.getName();

        assertEquals(firstAndLastName, sideBarName);
    }

}
