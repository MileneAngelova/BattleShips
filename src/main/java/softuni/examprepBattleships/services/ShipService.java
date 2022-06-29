package softuni.examprepBattleships.services;

import org.springframework.stereotype.Service;
import softuni.examprepBattleships.models.Category;
import softuni.examprepBattleships.models.DTO.user.CreateShipDTO;
import softuni.examprepBattleships.models.Ship;
import softuni.examprepBattleships.models.User;
import softuni.examprepBattleships.models.enums.ShipType;
import softuni.examprepBattleships.repositories.CategoryRepository;
import softuni.examprepBattleships.repositories.ShipRepository;
import softuni.examprepBattleships.repositories.UserRepository;
import softuni.examprepBattleships.session.LoggedUser;

import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public ShipService(ShipRepository shipRepository,
                       CategoryRepository categoryRepository,
                       LoggedUser loggedUser, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(CreateShipDTO createShipDTO) {

        Optional<Ship> optShip = this.shipRepository.findByName(createShipDTO.getName());

        if (optShip.isPresent()) {
            return false;
        }

       ShipType type = switch (createShipDTO.getCategory()) {
            case 0 -> ShipType.BATTLE;
            case 1 -> ShipType.CARGO;
            case 2 -> ShipType.PATROL;
            default -> ShipType.BATTLE;

        };

        Category category = this.categoryRepository.findByName(type);
        Optional<User> owner = this.userRepository.findById(this.loggedUser.getId());

        Ship ship = new Ship();

        ship.setName(createShipDTO.getName());
        ship.setPower(createShipDTO.getPower());
        ship.setHealth(createShipDTO.getHealth());
        ship.setCreated(createShipDTO.getCreated());
        ship.setCategory(category);
        ship.setUser(owner.get());

        this.shipRepository.save(ship);

        return true;
}

}