package dev.citralflo.automationproject;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import dev.citralflo.components.LoginForm;
import dev.citralflo.components.RegisterForm;
import dev.citralflo.models.UserData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationTest {

    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://parabank.parasoft.com/parabank/index.htm");
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void registerUser() {
        UserData userData = new UserData();

        LoginForm loginForm = new LoginForm(page);
        loginForm.clickRegisterLink();

        assertTrue(page.url().contains("register.htm"));

        RegisterForm registerForm = new RegisterForm(page);

        assertTrue(page.locator("span[id='customer.firstName.errors']").isHidden());

        registerForm.submit();

        assertTrue(page.url().contains("register.htm"));
        assertTrue(page.locator("span[id='customer.firstName.errors']").isEnabled());

        registerForm.fill(userData);
        registerForm.submit();

        assertTrue(page.content().contains("Welcome"));
    }
}
