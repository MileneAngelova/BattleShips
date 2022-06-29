package softuni.examprepBattleships.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.examprepBattleships.models.Category;
import softuni.examprepBattleships.models.enums.ShipType;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findAllByName(int name);

    Category findByName(ShipType name);
}
