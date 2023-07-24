package dmitreev.petproject.java.oneDayOneWay.auth.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    @NotBlank(message = "'username' can not be blank")
    private String username;
    @NotBlank(message = "'password' can not be blank")
    private String password;
}
