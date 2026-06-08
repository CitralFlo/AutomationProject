package dev.citralflo.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AccountsDashboard {

    private static final String TABLE_SELECTOR = "#accountTable";
    private static final String TABLE_ROWS_SELECTOR = "tbody tr";
    private static final String CELL_SELECTOR = "td";

    private static final String CURRENCY_SIGN = "$";
    private static final String EMPTY_STRING = "";

    private static final int ACCOUNT_NUMBER_COLUMN_INDEX = 0;
    private static final int BALANCE_COLUMN_INDEX = 1;

    private final Page page;
    private final Locator accountTable;

    public AccountsDashboard(Page page) {
        this.page = page;

        this.accountTable = this.page.locator(TABLE_SELECTOR);
    }

    public String getFirstAccount() {
        Locator firstAccountCell = this.accountTable.locator(TABLE_ROWS_SELECTOR)
                .first()
                .locator(CELL_SELECTOR)
                .nth(ACCOUNT_NUMBER_COLUMN_INDEX);

        firstAccountCell.waitFor();

        return firstAccountCell.innerText().trim();
    }


    public double getFirstAccountBalance() {

        Locator firstBalanceCell = this.accountTable.locator(TABLE_ROWS_SELECTOR)
                .first()
                .locator(CELL_SELECTOR)
                .nth(BALANCE_COLUMN_INDEX);

        firstBalanceCell.waitFor();

        String stringValue = firstBalanceCell.innerText().replace(CURRENCY_SIGN, EMPTY_STRING).trim();
        return Double.parseDouble(stringValue);
    }
}