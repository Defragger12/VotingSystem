package kuzmenokd.votingsystem.repository;

import kuzmenokd.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    Dish save(Dish dish);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish WHERE restaurant.id = ?1")
    void deleteMenu(int restaurantId);

    @Query("SELECT d FROM Dish d WHERE restaurant.id = ?1")
    List<Dish> getMenu(int restaurantId);
}
