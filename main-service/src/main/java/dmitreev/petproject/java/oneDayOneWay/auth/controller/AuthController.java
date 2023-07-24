package dmitreev.petproject.java.oneDayOneWay.auth.controller;

import dmitreev.petproject.java.oneDayOneWay.auth.dto.JwtRequest;
import dmitreev.petproject.java.oneDayOneWay.auth.service.AuthService;
import dmitreev.petproject.java.oneDayOneWay.user.dto.RegistrationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
