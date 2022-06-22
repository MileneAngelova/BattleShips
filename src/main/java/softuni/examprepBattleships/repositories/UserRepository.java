package softuni.examprepBattleships.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.examprepBattleships.models.Ship;

@Repository
public interface UserRepository extends JpaRepository<Ship, Long> {

}
