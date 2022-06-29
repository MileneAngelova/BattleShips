package softuni.examprepBattleships.web.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.examprepBattleships.models.DTO.user.CreateShipDTO;
import softuni.examprepBattleships.services.ShipService;
import softuni.examprepBattleships.services.user.LoginService;

import javax.validation.Valid;

@Controller
public class ShipController {

    private ShipService shipService;
    private LoginService loginService;

    public ShipController(ShipService shipService, LoginService loginService) {
        this.shipService = shipService;
        this.loginService = loginService;
    }

    @ModelAttribute("createShipDTO")
    public CreateShipDTO initCreateShipDTO() {
        return new CreateShipDTO();
    }

    @GetMapping("/ships/add")
    public String ships() {
        return "/ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDTO createShipDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.shipService.create(createShipDTO)) {
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO", bindingResult);

            return "redirect:/ships/add";
        }
        return "redirect:/home";
    }
}
