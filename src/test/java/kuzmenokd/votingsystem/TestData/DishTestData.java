package kuzmenokd.votingsystem.TestData;

import kuzmenokd.votingsystem.model.Dish;

import java.util.Arrays;

import static kuzmenokd.votingsystem.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
import static kuzmenokd.votingsystem.TestData.RestaurantTestData.*;

public class DishTestData {

    public static final int DISH1_ID = START_SEQ + 4;

    public static final int NEW_DISH_ID = START_SEQ + 8;

    public static final Dish DISH1 = new Dish(DISH1_ID, "рыба", 500, RESTAURANT1);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "овощи", 300, RESTAURANT1);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "мучное", 400, RESTAURANT2);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "суп", 300, RESTAURANT2);

    public static Dish getCreatedDish() {
        return new Dish(null, "борщ", 400, null);
    }

    public static Dish getUpdatedDish() {
        return new Dish(DISH1_ID, "обновлённая рыба", 550, null);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
