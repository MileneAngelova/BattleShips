package softuni.examprepBattleships.services;

import org.springframework.stereotype.Service;
import softuni.examprepBattleships.models.DTO.user.CreateShipDTO;
import softuni.examprepBattleships.models.Ship;
import softuni.examprepBattleships.repositories.ShipRepository;

import java.util.Optional;

@Service
public class ShipService {

    private ShipRepository shipRepository;

    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public boolean create(CreateShipDTO createShipDTO) {

        Optional<Ship> optShip = this.shipRepository.findByName(createShipDTO.getName());

        if (optShip.isPresent()) {
            return false;
        }
        Ship ship = new Ship();

        this.shipRepository.save(ship);

        return true;
}

}