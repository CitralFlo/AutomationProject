package dev.citralflo.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AccountsDashboard {

    private final Page page;
    private final Locator accountTable;

    public AccountsDashboard(Page page) {
        this.page = page;

        this.accountTable = this.page.locator("#accountTable");
    }

    public String getFirstAccount() {
        Locator firstAccountCell = accountTable.locator("tbody tr")
                .first()
                .locator("td")
                .first();

        firstAccountCell.waitFor();

        return firstAccountCell.innerText().trim();
    }


    public double getFirstAccountBalance() {

        Locator firstBalanceCell = accountTable.locator("tbody tr")
                .first()
                .locator("td")
                .nth(1);

        firstBalanceCell.waitFor();

        String stringValue = firstBalanceCell.innerText().replace("$", "").trim();
        return Double.valueOf(stringValue);
    }
}