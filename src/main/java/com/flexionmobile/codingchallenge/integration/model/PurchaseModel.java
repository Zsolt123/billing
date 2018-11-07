package com.flexionmobile.codingchallenge.integration.model;

import com.flexionmobile.codingchallenge.integration.Purchase;

public class PurchaseModel implements Purchase {

    private String id;
    private String itemId;
    private boolean consumed;

    public PurchaseModel(final String id, final String itemId, final boolean consumed) {

        this.id = id;
        this.itemId = itemId;
        this.consumed = consumed;
    }

    @Override
    public String getId() {

        return id;
    }

    @Override
    public String getItemId() {

        return itemId;
    }

    @Override
    public boolean getConsumed() {

        return consumed;
    }
}
