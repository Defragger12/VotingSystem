package kuzmenokd.votingsystem.service;

import kuzmenokd.votingsystem.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import kuzmenokd.votingsystem.TestData.AbstractTest;

import static kuzmenokd.votingsystem.TestData.UserTestData.*;
import static kuzmenokd.votingsystem.TestData.UserTestData.ADMIN;
import static kuzmenokd.votingsystem.TestData.UserTestData.assertMatch;

public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService service;

    @Test
    public void getByName() throws Exception {
        User user = service.getByName(USER.getName());
        assertMatch(user, USER);
        User admin = service.getByName(ADMIN.getName());
        assertMatch(admin, ADMIN);
    }
}
