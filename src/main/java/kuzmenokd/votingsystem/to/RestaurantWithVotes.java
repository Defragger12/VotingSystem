package kuzmenokd.votingsystem.to;

import kuzmenokd.votingsystem.model.Restaurant;
import kuzmenokd.votingsystem.util.RestaurantsUtil;

public class RestaurantWithVotes {

    private final Integer id;

    private final String name;

    private final Integer voteCount;

    public RestaurantWithVotes(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.voteCount = RestaurantsUtil.getVoteCount(restaurant);
    }

    public RestaurantWithVotes(Integer id, String name, Integer voteCount) {
        this.id = id;
        this.name = name;
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantWithVotes that = (RestaurantWithVotes) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
            return id == null ? 0 : id;
    }

    @Override
    public String toString() {
        return "RestaurantWithVotes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
