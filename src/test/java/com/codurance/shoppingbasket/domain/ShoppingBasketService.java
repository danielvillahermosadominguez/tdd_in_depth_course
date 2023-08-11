package com.codurance.shoppingbasket.domain;

public class ShoppingBasketService {
    private final ClockService clockService;
    private final ShoppingBasketRepository repository;

    public ShoppingBasketService(ClockService clockService, ShoppingBasketRepository repository) {
        this.clockService = clockService;
        this.repository = repository;
    }

    public void add(String basketId, String itemName, Integer quantity) throws NotFound {
        boolean exist = repository.existBasket(basketId);
        if (exist) {
            repository.add(basketId, itemName, quantity);
        } else {
            throw new NotFound("There is not a shopping basket with the id " + basketId);
        }

    }

    public ShoppingBasket get() {
        throw new UnsupportedOperationException();
    }
}
