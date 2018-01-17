package kuzmenokd.votingsystem.web;

import kuzmenokd.votingsystem.TestData.AbstractControllerTest;
import kuzmenokd.votingsystem.TestData.DishTestData;
import kuzmenokd.votingsystem.TestUtil;
import kuzmenokd.votingsystem.model.Dish;
import kuzmenokd.votingsystem.model.Restaurant;
import kuzmenokd.votingsystem.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static kuzmenokd.votingsystem.TestData.DishTestData.NEW_DISH_ID;
import static kuzmenokd.votingsystem.TestData.RestaurantTestData.*;
import static kuzmenokd.votingsystem.TestData.UserTestData.ADMIN;
import static kuzmenokd.votingsystem.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + "/";

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(getUpdatedRestaurant())))
                .andExpect(status().isOk());
        assertMatch(restaurantRepository.getById(RESTAURANT1_ID), getUpdatedRestaurant());
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = getCreatedRestaurant();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = TestUtil.readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(restaurantRepository.getAll(), RESTAURANT1, RESTAURANT2, expected);
    }

    @Test
    public void testRefreshMenu() throws Exception {
        Dish expected1 = new Dish(null, "еда1", 300, null);
        Dish expected2 = new Dish(null, "еда2", 500, null);
        List<Dish> newMenu = Arrays.asList(expected1, expected2);

        mockMvc.perform(post(REST_URL + RESTAURANT1_ID + "/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isOk());
        expected1.setId(NEW_DISH_ID);
        expected2.setId(NEW_DISH_ID + 1);
        DishTestData.assertMatch(menuService.getMenu(RESTAURANT1_ID), newMenu);
    }

    @Test
    public void testDeleteMenu() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID + "/menu")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
    }

}