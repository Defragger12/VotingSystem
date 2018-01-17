package kuzmenokd.votingsystem.TestData;

import kuzmenokd.votingsystem.model.Role;
import kuzmenokd.votingsystem.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;

import static kuzmenokd.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ + 2;
    public static final int ADMIN_ID = START_SEQ + 3;

    public static final User USER = new User(USER_ID, "User", "user123", Role.ROLE_USER);

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin123", Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
