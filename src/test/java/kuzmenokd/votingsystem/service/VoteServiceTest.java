package kuzmenokd.votingsystem.service;

import kuzmenokd.votingsystem.TestData.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

import static kuzmenokd.votingsystem.TestData.RestaurantTestData.RESTAURANT1_ID;
import static kuzmenokd.votingsystem.TestData.UserTestData.ADMIN;
import static kuzmenokd.votingsystem.TestData.UserTestData.USER;
import static kuzmenokd.votingsystem.TestUtil.mockAuthorize;
import static kuzmenokd.votingsystem.model.Restaurant.VotingStopTime;
import static org.junit.Assert.*;

public class VoteServiceTest extends AbstractTest {

    @Autowired
    private VoteService voteService;

    @Before
    public void setUp() throws Exception {
        mockAuthorize(USER);
    }

    @Test
    public void vote() throws Exception {
        assertEquals(voteService.vote(RESTAURANT1_ID), "Thank you for your vote");
        assertEquals(voteService.vote(RESTAURANT1_ID), "You already voted for this restaurant");
        if (LocalTime.now().isAfter(VotingStopTime)) {
            assertEquals(voteService.vote(RESTAURANT1_ID + 1), "You can't change your vote after " + VotingStopTime);
        } else {
            assertEquals(voteService.vote(RESTAURANT1_ID + 1), "Thank you for your vote");
        }

        mockAuthorize(ADMIN);
        assertEquals(voteService.vote(RESTAURANT1_ID), "You have no rights to vote");
    }
}