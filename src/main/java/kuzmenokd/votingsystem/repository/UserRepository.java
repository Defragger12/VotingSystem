package kuzmenokd.votingsystem.repository;

import kuzmenokd.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.name=?1")
    User getByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.restaurant.id=?1 WHERE u.id=?2")
    void setRestaurant(int restaurantId, int userId);
}
