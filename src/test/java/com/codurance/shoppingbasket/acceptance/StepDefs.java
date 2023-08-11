package com.codurance.shoppingbasket.acceptance;

import com.codurance.shoppingbasket.domain.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StepDefs {

    ClockService clockService = mock(ClockService.class);
    ShoppingBasketRepository repository = new ShoppingBasketRepository();
    ShoppingBasketService shoppingBasketService = new ShoppingBasketService(clockService, repository);

    ShoppingBasket shoppingBasket = null;

    @Given("today is {string}")
    public void today_is(String dateString) throws ParseException {
        when(clockService.getCurrentDate()).thenReturn(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
    }

    @Given("I add {int} units of {string} to my shopping basket")
    public void i_add_units_of_to_my_shopping_basket(Integer quantity, String itemName) throws NotFound {
        this.shoppingBasketService.add(itemName, "BREAD", quantity);
    }

    @When("I check the content of my shopping basket")
    public void i_check_the_content_of_my_shopping_basket() {
        shoppingBasket = this.shoppingBasketService.get();
    }

    @Then("it should have a date {string}")
    public void it_should_have_a_date(String dateString) {
        assertEquals(dateString, shoppingBasket.getFormatedDate());
    }

    @Then("the total is {int} pounds")
    public void the_total_is_pounds(Integer expectedTotal) {
        assertEquals(expectedTotal, shoppingBasket.getTotal());
    }

    @Then("contains the following items")
    public void contains_the_following_items(DataTable dataTable) {
        List<ShoppingBasketItem> list = shoppingBasket.getItems();
        int i = 0;
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> row : rows) {
            ShoppingBasketItem item = list.get(i);
            assertEquals(row.get(0), item.getName());
            assertEquals(row.get(1), item.getQuantity() + "");
            assertEquals(row.get(2), item.getPrizePerUnit() + "");
            assertEquals(row.get(2), item.getTotal() + "");
            i++;
        }
    }

}
