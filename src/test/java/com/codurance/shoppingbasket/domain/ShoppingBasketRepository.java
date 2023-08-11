package com.codurance.shoppingbasket.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingBasketRepository {
    List<ShoppingBasket> items = new ArrayList<>();

    public void add(String basketId, String itemName, int quantity) {
        Optional<ShoppingBasket> basket = items.
            stream().
            filter(item -> item.getId().equals(basketId)).
            findFirst();
        if (basket.isPresent()) {
            basket.get().add(new ShoppingBasketItem(itemName, quantity));
        }
    }

    public boolean existBasket(String basketId) {
        throw new UnsupportedOperationException();
    }

    public void createShoppingBasket(String shopping_basket) {
        throw new UnsupportedOperationException();
    }
}
