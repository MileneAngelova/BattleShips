package softuni.examprepBattleships.web.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.examprepBattleships.session.LoggedUser;

@Controller
public class LogoutController {

    private LoggedUser loggedUser;

    public LogoutController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/logout")
    public String logout() {
        loggedUser.logout();
    return "/index";
}
}
