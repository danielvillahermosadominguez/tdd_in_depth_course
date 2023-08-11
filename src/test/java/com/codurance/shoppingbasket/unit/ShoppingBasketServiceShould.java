package com.codurance.shoppingbasket.unit;

import com.codurance.shoppingbasket.domain.ClockService;
import com.codurance.shoppingbasket.domain.NotFound;
import com.codurance.shoppingbasket.domain.ShoppingBasketRepository;
import com.codurance.shoppingbasket.domain.ShoppingBasketService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ShoppingBasketServiceShould {
    @Test
    void add_an_item_to_the_basket_when_the_basket_exists() throws NotFound {
        ClockService clockService = mock(ClockService.class);
        ShoppingBasketRepository repository = mock(ShoppingBasketRepository.class);
        when(repository.existBasket(any())).thenReturn(true);
        ShoppingBasketService shoppingBasketService = new ShoppingBasketService(clockService, repository);

        shoppingBasketService.add("basketId", "BREAD", 1);

        verify(repository).add("basketId", "BREAD", 1);
    }

    @Test
    void add_an_item_to_the_basket_when_the_basket_doesnt_exist() {
        ClockService clockService = mock(ClockService.class);
        ShoppingBasketRepository repository = mock(ShoppingBasketRepository.class);
        when(repository.existBasket(any())).thenReturn(false);
        ShoppingBasketService shoppingBasketService = new ShoppingBasketService(clockService, repository);

        Exception ex = assertThrows(NotFound.class, () -> {
            shoppingBasketService.add("basketId", "BREAD", 1);
        });

        assertEquals("There is not a shopping basket with the id " + "basketId",
            ex.getMessage()
        );
    }
}
