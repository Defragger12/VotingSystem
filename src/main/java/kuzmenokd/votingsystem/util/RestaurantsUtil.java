package kuzmenokd.votingsystem.util;

import kuzmenokd.votingsystem.model.Restaurant;
import kuzmenokd.votingsystem.to.RestaurantWithVotes;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantsUtil {

    public static RestaurantWithVotes createWithVotes(Restaurant restaurant) {
        return new RestaurantWithVotes(restaurant.getId(), restaurant.getName(), RestaurantsUtil.getVoteCount(restaurant));
    }

    public static List<RestaurantWithVotes> getWithVotesSorted(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantsUtil::createWithVotes)
                .sorted((o1, o2) -> o2.getVoteCount() - o1.getVoteCount())
                .collect(Collectors.toList());
    }

    public static Integer getVoteCount(Restaurant restaurant) {
        return restaurant.getUsers().size();
    }

}
