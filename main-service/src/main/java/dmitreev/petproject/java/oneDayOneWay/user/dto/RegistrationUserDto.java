package dmitreev.petproject.java.oneDayOneWay.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationUserDto {

    @NotBlank(message = "'username' can not be blank")
    private String username;
    @NotBlank(message = "'password' can not be blank")
    private String password;
    @NotBlank(message = "'confirmPassword' can not be blank")
    private String confirmPassword;
    @Email
    private String email;
}
