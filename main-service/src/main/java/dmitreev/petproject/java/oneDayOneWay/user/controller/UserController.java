package dmitreev.petproject.java.oneDayOneWay.user.controller;

import dmitreev.petproject.java.oneDayOneWay.user.dto.RegistrationUserDto;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import dmitreev.petproject.java.oneDayOneWay.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public User saveUser(@Valid @RequestBody RegistrationUserDto userDto) {
        return userService.createNewUser(userDto);
    }
}
