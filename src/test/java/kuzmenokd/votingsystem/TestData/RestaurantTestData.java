package kuzmenokd.votingsystem.TestData;

import kuzmenokd.votingsystem.model.Restaurant;

import java.util.Arrays;

import static kuzmenokd.votingsystem.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "оригинальный");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "странный");

    public static Restaurant getCreatedRestaurant() {
        return new Restaurant(null, "название нового ресторана");
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(RESTAURANT1_ID, "название обновлённого ресторана");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu", "users");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu", "users").isEqualTo(expected);
    }
}
