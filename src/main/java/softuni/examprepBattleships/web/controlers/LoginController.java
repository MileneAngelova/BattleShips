package softuni.examprepBattleships.web.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.examprepBattleships.models.DTO.user.LoginDTO;
import softuni.examprepBattleships.services.user.LoginService;

import javax.validation.Valid;

@Controller
public class LoginController {

private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @ModelAttribute("loginDTO")
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        if(!this.loginService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }
        return "redirect:/home";
    }
}
