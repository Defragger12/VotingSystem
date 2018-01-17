package kuzmenokd.votingsystem.service;

import kuzmenokd.votingsystem.model.Dish;
import kuzmenokd.votingsystem.TestData.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static kuzmenokd.votingsystem.TestData.DishTestData.*;
import static kuzmenokd.votingsystem.TestData.DishTestData.DISH4;
import static kuzmenokd.votingsystem.TestData.RestaurantTestData.RESTAURANT1_ID;

public class MenuServiceTest extends AbstractTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void getMenu() throws Exception {
        List<Dish> menu1 = menuService.getMenu(RESTAURANT1_ID);
        assertMatch(menu1, DISH1, DISH2);
        List<Dish> menu2 = menuService.getMenu(RESTAURANT1_ID + 1);
        assertMatch(menu2, DISH3, DISH4);
    }


    @Test
    public void refreshMenu() throws Exception {
        Dish expected1 = new Dish(null, "еда1", 300, null);
        Dish expected2 = new Dish(null, "еда2", 500, null);
        List<Dish> newMenu = Arrays.asList(expected1, expected2);
        menuService.refreshMenu(newMenu, RESTAURANT1_ID);
        expected1.setId(NEW_DISH_ID);
        expected2.setId(NEW_DISH_ID + 1);
        assertMatch(menuService.getMenu(RESTAURANT1_ID), expected1, expected2);
    }

}