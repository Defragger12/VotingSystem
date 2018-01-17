package kuzmenokd.votingsystem.service;

import kuzmenokd.votingsystem.model.Role;
import kuzmenokd.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kuzmenokd.votingsystem.AuthorizedUser;

import java.time.LocalTime;

import static kuzmenokd.votingsystem.model.Restaurant.VotingStopTime;


@Service
public class VoteService {

    @Autowired
    private UserRepository userRepository;

    /**
     * performs vote operation. AuthorizedUser votes for a restaurant with specific id
     *
     * @param restaurantId id of a restaurant AuthorizedUser is voting for
     * @return message, that informs AuthorizedUser about how vote operation ended
     */
    public String vote(int restaurantId) {
        if (!AuthorizedUser.getRoles().contains(Role.ROLE_USER)) {
            return "You have no rights to vote";
        }
        if (AuthorizedUser.getRestaurantId() != null) {
            if (AuthorizedUser.getRestaurantId() == restaurantId) {
                return "You already voted for this restaurant";
            }
            if (LocalTime.now().isAfter(VotingStopTime)) {
                return "You can't change your vote after " + VotingStopTime;
            }
        }
        userRepository.setRestaurant(restaurantId, AuthorizedUser.getId());
        AuthorizedUser.get().setRestaurantId(restaurantId);
        return "Thank you for your vote";
    }
}
