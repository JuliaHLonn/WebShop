package com.iths.webshopproject.business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import se.iths.webshop.business.OrderLine;
import se.iths.webshop.business.Product;
import se.iths.webshop.business.ProductCategory;
import se.iths.webshop.business.ShopService;
import se.iths.webshop.storage.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShopServiceTest {

    Product product;
    Product product2;

    ShopService service;

    @BeforeAll
    public void initialize() {
        product = new Product("Rake",280, ProductCategory.TOOL);
        product2 = new Product("Sunflower",25,ProductCategory.FLOWER);
        this.service = new ShopService();
    }

    @Test
    void calculateTotalForSingleOrderLineShouldBeSuccessfull(){
        List<OrderLine> shoppingCart = new ArrayList<>();
        OrderLine orderLine = new OrderLine(product,2);
        shoppingCart.add(orderLine);
        double total = service.calculateTotal(shoppingCart);
        assertTrue(total == 560);
    }

    @Test
    void calculateTotalForMultipleOrderLinesShouldBeSuccessfull(){
        List<OrderLine> shoppingCart = new ArrayList<>();
        OrderLine orderLine1 = new OrderLine(product,2);
        OrderLine orderLine2 = new OrderLine(product2,4);
        shoppingCart.add(orderLine1);
        shoppingCart.add(orderLine2);
        double total = service.calculateTotal(shoppingCart);
        assertEquals(660,total);
    }

    @Test
    public void testCalculateTotalForEmptyOrderLinesShouldReturnZero() {
        List<OrderLine> shoppingCart = new ArrayList<>();
        double expectedTotal = 0.0;
        double actualTotal = service.calculateTotal(shoppingCart);
        assertEquals(expectedTotal, actualTotal, 0);
    }

}