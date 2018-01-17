package kuzmenokd.votingsystem.repository;

import kuzmenokd.votingsystem.TestData.DishTestData;
import kuzmenokd.votingsystem.model.Restaurant;
import kuzmenokd.votingsystem.TestData.AbstractTest;
import kuzmenokd.votingsystem.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static kuzmenokd.votingsystem.TestData.DishTestData.DISH1;
import static kuzmenokd.votingsystem.TestData.DishTestData.DISH2;
import static kuzmenokd.votingsystem.TestData.RestaurantTestData.*;

public class RestaurantRepositoryTest extends AbstractTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuService menuService;

    @Test
    public void getById() throws Exception {
        Restaurant restaurant = restaurantRepository.getById(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void save() throws Exception {
        Restaurant created = getCreatedRestaurant();
        restaurantRepository.save(created);
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, created);
    }

    @Test
    public void delete() throws Exception {
        restaurantRepository.delete(RESTAURANT1_ID);
        assertMatch(restaurantRepository.getAll(), RESTAURANT2);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void getWithMenuById() throws Exception {
        Restaurant restaurant = restaurantRepository.getWithMenuById(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
        DishTestData.assertMatch(menuService.getMenu(RESTAURANT1_ID), DISH1, DISH2);
    }
}
