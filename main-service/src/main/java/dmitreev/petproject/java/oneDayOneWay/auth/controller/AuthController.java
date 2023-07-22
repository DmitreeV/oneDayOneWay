package dmitreev.petproject.java.oneDayOneWay.auth.controller;

import dmitreev.petproject.java.oneDayOneWay.auth.dto.JwtRequest;
import dmitreev.petproject.java.oneDayOneWay.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

//    @PostMapping("/registration")
//    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
//        return authService.createNewUser(registrationUserDto);
//    }
}
