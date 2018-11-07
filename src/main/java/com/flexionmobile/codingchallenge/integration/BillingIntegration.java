package com.flexionmobile.codingchallenge.integration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.flexionmobile.codingchallenge.integration.model.PurchaseModel;
import com.flexionmobile.codingchallenge.rest.RestClient;

public class BillingIntegration implements Integration {

    private static final String BUY_PATH = "/buy/%s";
    private static final String GET_ALL_PATH = "/all";
    private static final String CONSUME_PATH = "/consume/%s";

    private static final String JSON_ID = "id";
    private static final String JSON_ITEM_ID = "itemId";
    private static final String JSON_CONSUMED = "consumed";
    private static final String JSON_PURCHASES = "purchases";

    @Override
    public Purchase buy(final String itemId) {

        final String path = String.format(BUY_PATH, itemId);
        final JsonNode response = RestClient.post(path);

        return createPurchase(response);
    }

    @Override
    public void consume(final Purchase purchase) {

        final String path = String.format(CONSUME_PATH, purchase.getId());

        RestClient.post(path);
    }

    @Override
    public List<Purchase> getPurchases() {

        final JsonNode response = RestClient.get(GET_ALL_PATH);

        if (response instanceof NullNode) {

            return null;
        } else {

            final List<Purchase> purchases = new ArrayList<>();

            final JsonNode purchasesJson = response.path(JSON_PURCHASES);
            final Iterator<JsonNode> purchasesIt = purchasesJson.elements();
            while (purchasesIt.hasNext()) {

                final JsonNode purchaseJson = purchasesIt.next();

                purchases.add(createPurchase(purchaseJson));
            }

            return purchases;
        }
    }

    private Purchase createPurchase(final JsonNode json) {

        if (json instanceof NullNode) {

            return null;
        } else {

            final String id = json.get(JSON_ID).asText();
            final String itemId = json.get(JSON_ITEM_ID).asText();
            final boolean consumed = json.get(JSON_CONSUMED).asBoolean();

            return new PurchaseModel(id, itemId, consumed);
        }
    }

}
