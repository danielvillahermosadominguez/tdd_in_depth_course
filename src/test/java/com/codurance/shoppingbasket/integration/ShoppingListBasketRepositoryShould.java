package com.codurance.shoppingbasket.integration;

import com.codurance.shoppingbasket.domain.ShoppingBasketRepository;
import org.junit.jupiter.api.Test;

//Out decision for integration tests: We expect, we save the data and we asume the database works.
public class ShoppingListBasketRepositoryShould {
    @Test
    void create_a_shopping_list() {
        ShoppingBasketRepository repository = new ShoppingBasketRepository();
        repository.createShoppingBasket("SHOPPING_BASKET");
    }

    @Test
    void add_item_to_the_repository() {
        ShoppingBasketRepository repository = new ShoppingBasketRepository();
        repository.add("SHOPPING_BASKET", "ITEM", 1);
    }
}
