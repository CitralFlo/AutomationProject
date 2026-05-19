package dev.citralflo.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DepositUtil {

    //https://parabank.parasoft.com/parabank/services/bank/deposit?accountId=14232&amount=123
    private static final String PARASOFT_URI = "https://parabank.parasoft.com/parabank/services/bank/deposit";
    private static final String ACCOUNT_ID = "accountId";
    private static final String AMOUNT = "amount";

    private DepositUtil() {

    }

    public static void createDeposit(int accountId, int amount) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        try {
            String url = PARASOFT_URI + "?" + ACCOUNT_ID + "=" + accountId + "&" + AMOUNT + "=" + amount;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            response.statusCode();
        }
        catch (IOException | InterruptedException exception) {
            client.close();
            throw exception;
        }
        finally {
            client.close();
        }

    }

}
