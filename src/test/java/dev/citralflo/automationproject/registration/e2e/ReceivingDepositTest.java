package dev.citralflo.automationproject.registration.e2e;

import dev.citralflo.BaseTest;
import dev.citralflo.components.AccountsDashboard;
import dev.citralflo.components.LoginForm;
import dev.citralflo.components.RegisterForm;
import dev.citralflo.components.SideBar;
import dev.citralflo.models.UserData;
import dev.citralflo.utils.DepositUtil;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReceivingDepositTest extends BaseTest {

    @Test
    @Tag("End2End")
    @DisplayName("Registration of a new user and receiving a deposit")
    void registerAndDeposit() throws IOException, InterruptedException {
        UserData userData = new UserData();
        this.registerUser(userData);

        System.out.println("Username: " + userData.username);
        System.out.println("Password: " + userData.password);

        SideBar sideBar = new SideBar(page);
        sideBar.accountsOverview();

        AccountsDashboard accountsDashboard = new AccountsDashboard(page);
        String firstAccount = accountsDashboard.getFirstAccount();
        int firstAccountId = Integer.parseInt(firstAccount);

        double balance = accountsDashboard.getFirstAccountBalance();

        DepositUtil.createDeposit(firstAccountId, 200);
        page.reload();

        assertEquals(balance + 200, accountsDashboard.getFirstAccountBalance());

    }

    void registerUser(UserData userData) {
        LoginForm loginForm = new LoginForm(page);
        loginForm.clickRegisterLink();

        RegisterForm registerForm = new RegisterForm(page);

        registerForm.fill(userData);
        registerForm.submit();
        assertTrue(page.content().contains("Welcome"), "Registration failed, welcome message not found.");
    }

}
