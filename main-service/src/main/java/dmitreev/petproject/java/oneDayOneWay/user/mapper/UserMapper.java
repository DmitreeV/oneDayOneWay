package dmitreev.petproject.java.oneDayOneWay.user.mapper;

import dmitreev.petproject.java.oneDayOneWay.user.dto.UserDto;
import dmitreev.petproject.java.oneDayOneWay.user.dto.UserShortDto;
import dmitreev.petproject.java.oneDayOneWay.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    public static UserShortDto toUserShortDto(User user){
        return UserShortDto.builder()
                .username(user.getUsername())
                .build();
    }
}
