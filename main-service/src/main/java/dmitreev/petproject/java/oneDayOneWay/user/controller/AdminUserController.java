package dmitreev.petproject.java.oneDayOneWay.user.controller;

import dmitreev.petproject.java.oneDayOneWay.user.dto.UserDto;
import dmitreev.petproject.java.oneDayOneWay.user.service.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/users")
@Tag(name = "Operations with users available to the administrator.")
public class AdminUserController {

    private final AdminUserService userService;

    @GetMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Getting information about the user by his id.")
    public UserDto getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all users.")
    public List<UserDto> getAllUsers(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        return userService.getAllUsers(from, size);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user.")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
