package kuzmenokd.votingsystem.web;

import kuzmenokd.votingsystem.TestData.AbstractControllerTest;
import kuzmenokd.votingsystem.model.Restaurant;
import kuzmenokd.votingsystem.service.VoteService;
import kuzmenokd.votingsystem.to.RestaurantWithVotes;
import kuzmenokd.votingsystem.util.RestaurantsUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static kuzmenokd.votingsystem.TestData.RestaurantTestData.RESTAURANT1;
import static kuzmenokd.votingsystem.TestData.RestaurantTestData.RESTAURANT1_ID;
import static kuzmenokd.votingsystem.TestData.UserTestData.USER;
import static kuzmenokd.votingsystem.TestUtil.contentJson;
import static kuzmenokd.votingsystem.TestUtil.userHttpBasic;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    private VoteService voteService;

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> allRestaurants = restaurantRepository.getAll();
        List<RestaurantWithVotes> restaurantWithVotes = RestaurantsUtil.getWithVotesSorted(allRestaurants);

        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(restaurantWithVotes));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1));
    }

    @Test
    public void testGetMenu() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/menu")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(menuService.getMenu(RESTAURANT1_ID)));
    }

    @Test
    public void testVote() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
    }

}