package com.flexionmobile.codingchallenge.integration.model;

import com.flexionmobile.codingchallenge.integration.Purchase;

public class PurchaseModel implements Purchase {

    private final boolean consumed;
    private final String id;
    private final String itemId;

    public PurchaseModel(final boolean consumed, final String id, final String itemId) {

        this.consumed = consumed;
        this.id = id;
        this.itemId = itemId;
    }

    public boolean getConsumed() {

        return consumed;
    }

    public String getId() {

        return id;
    }

    public String getItemId() {

        return itemId;
    }

}
