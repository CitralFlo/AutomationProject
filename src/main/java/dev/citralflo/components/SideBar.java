package dev.citralflo.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SideBar {

    private final Page page;
    private final Locator leftPanel;

    public SideBar(Page page) {
        this.page = page;
        this.leftPanel = page.locator("#leftPanel");
    }

    public String getName() {
        String innerText = this.page.locator("//p[@class='smallText']").innerText();

        return innerText.replace("Welcome ", "");
    }

    public void openNewAccount() {
        this.leftPanel.locator("//a[@href='openaccount.htm']").click();
    }

    public void accountsOverview() {
        this.leftPanel.locator("//a[@href='overview.htm']").click();
    }

    public void transferFunds() {
        this.leftPanel.locator("//a[@href='transfer.htm']").click();
    }

    public void billPay() {
        this.leftPanel.locator("//a[@href='billpay.htm']").click();
    }

    public void findTransaction() {
        this.leftPanel.locator("//a[@href='findtrans.htm']").click();
    }

    public void updateContactInfo() {
        this.leftPanel.locator("//a[@href='updateprofile.htm']").click();
    }

    public void requestLoad() {
        this.leftPanel.locator("//a[@href='requestloan.htm']").click();
    }

    public void logout() {
        this.leftPanel.locator("//a[contains(@href, 'logout')]").click();
    }

}
