package dmitreev.petproject.java.oneDayOneWay.auth.service;

import dmitreev.petproject.java.oneDayOneWay.auth.dto.JwtRequest;
import dmitreev.petproject.java.oneDayOneWay.auth.dto.JwtResponse;
import dmitreev.petproject.java.oneDayOneWay.error.model.AppError;
import dmitreev.petproject.java.oneDayOneWay.user.service.UserService;
import dmitreev.petproject.java.oneDayOneWay.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

//    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
//        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
//            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
//        }
//        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
//            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
//        }
//        User user = userService.createNewUser(registrationUserDto);
//        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
//    }
}
