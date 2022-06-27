package softuni.examprepBattleships.web.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.examprepBattleships.models.DTO.user.RegisterDTO;
import softuni.examprepBattleships.services.user.RegisterService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @ModelAttribute("registerDTO")
    public RegisterDTO initRegistration() {
        return new RegisterDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDto", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:/register";
        }
        if (!registerService.register(registerDTO)) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/register";
        }

        return "redirect:/login";
    }
}
