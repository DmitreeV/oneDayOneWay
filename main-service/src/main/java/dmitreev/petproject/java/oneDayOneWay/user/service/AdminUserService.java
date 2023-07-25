package dmitreev.petproject.java.oneDayOneWay.user.service;

import dmitreev.petproject.java.oneDayOneWay.user.dto.UserDto;

import java.util.List;

public interface AdminUserService {

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers(Integer from, Integer size);

    void deleteUser(Long userId);
}
