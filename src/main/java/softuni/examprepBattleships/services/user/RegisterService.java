package softuni.examprepBattleships.services.user;

import org.springframework.stereotype.Service;
import softuni.examprepBattleships.models.DTO.user.RegisterDTO;
import softuni.examprepBattleships.models.User;
import softuni.examprepBattleships.repositories.UserRepository;

import java.util.Optional;

@Service
public class RegisterService {

    private UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> byEmail = userRepository.findByEmail(registerDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }

        Optional<User> byUsername = userRepository.findByUsername(registerDTO.getEmail());
        if (byUsername.isPresent()) {
            return false;
        }

            User user = new User();

            user.setUsername(registerDTO.getUsername());
            user.setEmail(registerDTO.getEmail());
            user.setFullName(registerDTO.getFullName());
            user.setPassword(registerDTO.getPassword());

            this.userRepository.save(user);

            return true;
        }
}
