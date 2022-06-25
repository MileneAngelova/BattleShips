package softuni.examprepBattleships.services.user;

import org.springframework.stereotype.Service;
import softuni.examprepBattleships.models.DTO.user.LoginDTO;
import softuni.examprepBattleships.models.User;
import softuni.examprepBattleships.repositories.UserRepository;
import softuni.examprepBattleships.session.LoggedUser;

import java.util.Optional;

@Service
public class LoginService {
    private UserRepository userRepository;
    private LoggedUser userSession;

    public LoginService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (user.isEmpty()) {
            return false;
        }
        this.userSession.login(user.get());
        return true;
    }
}
