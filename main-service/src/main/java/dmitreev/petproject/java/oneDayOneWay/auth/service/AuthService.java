package dmitreev.petproject.java.oneDayOneWay.auth.service;

import dmitreev.petproject.java.oneDayOneWay.auth.dto.JwtRequest;
import dmitreev.petproject.java.oneDayOneWay.auth.dto.JwtResponse;
import dmitreev.petproject.java.oneDayOneWay.error.exception.BadRequestException;
import dmitreev.petproject.java.oneDayOneWay.error.exception.UnauthorizedException;
import dmitreev.petproject.java.oneDayOneWay.user.dto.RegistrationUserDto;
import dmitreev.petproject.java.oneDayOneWay.user.dto.UserDto;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.service.UserService;
import dmitreev.petproject.java.oneDayOneWay.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Incorrect login or password.");
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        log.info("Token was received for the user {}.", authRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords don't match.");
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            throw new BadRequestException("The user with the specified name already exists.");
        }
        User user = userService.createNewUser(registrationUserDto);
        log.info("A new user has been registered with id {}.", user.getId());
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }
}
