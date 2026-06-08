package dev.citralflo.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SideBar {

    private static final String LEFT_PANEL_LOCATOR = "#leftPanel";

    private static final String USER_NAME_LOCATOR = "//p[@class='smallText']";

    private static final String WELCOME_TEXT = "Welcome ";
    private static final String EMPTY_STRING = "";

    private static final String OPEN_ACCOUNT_LOCATOR = "//a[@href='openaccount.htm']";
    private static final String OVERVIEW_LOCATOR = "//a[@href='overview.htm']";
    private static final String TRANSFERS_LOCATOR = "//a[@href='transfer.htm']";
    private static final String BILLPAY_LOCATOR = "//a[@href='billpay.htm']";
    private static final String FIND_TRANSACTION_LOCATOR = "//a[@href='findtrans.htm']";
    private static final String UPDATE_PROFILE_LOCATOR = "//a[@href='updateprofile.htm']";
    private static final String REQUEST_LOAN_LOCATOR = "//a[@href='requestloan.htm']";
    private static final String LOGOUT_LOCATOR = "//a[contains(@href, 'logout')]";

    private final Page page;
    private final Locator leftPanel;

    public SideBar(Page page) {
        this.page = page;
        this.leftPanel = page.locator(LEFT_PANEL_LOCATOR);
    }

    public String getName() {
        String innerText = this.page.locator(USER_NAME_LOCATOR).innerText();

        return innerText.replace(WELCOME_TEXT, EMPTY_STRING);
    }

    public void openNewAccount() {
        this.leftPanel.locator(OPEN_ACCOUNT_LOCATOR).click();
    }

    public void accountsOverview() {
        this.leftPanel.locator(OVERVIEW_LOCATOR).click();
    }

    public void transferFunds() {
        this.leftPanel.locator(TRANSFERS_LOCATOR).click();
    }

    public void billPay() {
        this.leftPanel.locator(BILLPAY_LOCATOR).click();
    }

    public void findTransaction() {
        this.leftPanel.locator(FIND_TRANSACTION_LOCATOR).click();
    }

    public void updateContactInfo() {
        this.leftPanel.locator(UPDATE_PROFILE_LOCATOR).click();
    }

    public void requestLoad() {
        this.leftPanel.locator(REQUEST_LOAN_LOCATOR).click();
    }

    public void logout() {
        this.leftPanel.locator(LOGOUT_LOCATOR).click();
    }

}
