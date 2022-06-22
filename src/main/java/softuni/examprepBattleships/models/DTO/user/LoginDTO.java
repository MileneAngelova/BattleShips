package softuni.examprepBattleships.models.DTO.user;

import javax.validation.constraints.Size;

public class LoginDTO {

    @Size(min = 3, max = 10)
    private String username;

    @Size(min = 3)
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
