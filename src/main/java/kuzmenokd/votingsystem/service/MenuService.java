package kuzmenokd.votingsystem.service;

import kuzmenokd.votingsystem.model.Dish;
import kuzmenokd.votingsystem.model.Restaurant;
import kuzmenokd.votingsystem.repository.DishRepository;
import kuzmenokd.votingsystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Dish> getMenu(int restaurantId) {
        return dishRepository.getMenu(restaurantId);
    }

    /**
     * method is used to replace existing menu of a restaurant with new one
     *
     * @param dishes menu
     */
    public List<Dish> refreshMenu(List<Dish> dishes, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getWithMenuById(restaurantId);
        Assert.notNull(restaurant, "restaurant with id = " + restaurantId + " does not exist");
        if (restaurant.getMenu().size() != 0) {
            dishRepository.deleteMenu(restaurant.getId());
        }
        return createMenu(dishes, restaurant);
    }

    /**
     * delete menu of a restaurant with specific id
     *
     * @param restaurantId if of a restaurant
     */
    public void deleteMenu(int restaurantId) {
        dishRepository.deleteMenu(restaurantId);
    }

    /**
     * create menu for a restaurant
     *
     * @param dishes dishes from menu
     * @param restaurant restaurant menu belongs to
     * @return created menu
     */
    private List<Dish> createMenu(List<Dish> dishes, Restaurant restaurant) {
        List<Dish> refreshedMenu = new ArrayList<>();
        dishes.forEach(dish -> {
            dish.setRestaurant(restaurant);
            refreshedMenu.add(dishRepository.save(dish));
        });
        return refreshedMenu;
    }
}
