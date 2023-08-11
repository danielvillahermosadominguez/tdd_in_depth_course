package com.codurance.shoppingbasket.domain;


public class ShoppingBasketItem {
    private final String name;
    private final int quantity;

    public ShoppingBasketItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public int getQuantity() {
        throw new UnsupportedOperationException();
    }

    public double getPrizePerUnit() {
        throw new UnsupportedOperationException();
    }

    public double getTotal() {
        throw new UnsupportedOperationException();
    }
}
