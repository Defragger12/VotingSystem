package kuzmenokd.votingsystem.web;

import kuzmenokd.votingsystem.AuthorizedUser;
import kuzmenokd.votingsystem.model.Dish;
import kuzmenokd.votingsystem.model.Restaurant;
import kuzmenokd.votingsystem.repository.RestaurantRepository;
import kuzmenokd.votingsystem.service.MenuService;
import kuzmenokd.votingsystem.service.VoteService;
import kuzmenokd.votingsystem.to.RestaurantWithVotes;
import kuzmenokd.votingsystem.util.RestaurantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private VoteService voteService;

    static final String REST_URL = "/restaurants";

    @GetMapping
    public List<RestaurantWithVotes> getAll() {
        log.info("get all restaurants");
        return RestaurantsUtil.getWithVotesSorted(restaurantRepository.getAll());
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        log.info("get restaurant {}", id);
        return restaurantRepository.getById(id);
    }

    @GetMapping("/{id}/menu")
    public List<Dish> getMenu(@PathVariable("id") int id) {
        log.info("get menu of restaurant {}", id);
        return menuService.getMenu(id);
    }

    @PutMapping("/{id}/vote")
    public String vote(@PathVariable("id") int id) {
        log.info("user {} votes for restaurant {}", AuthorizedUser.getId(), id);
        return voteService.vote(id);
    }
}
