package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.dto.UserDto;

public enum UserMapper {
    PROFILE_MAPPER;

    public final User buildDtoToEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .fatherName(userDto.getFatherName())
                .fin(userDto.getFin())
                .role(userDto.getRole())
                .build();
    }

    public final UserDto buildEntityToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .fatherName(user.getFatherName())
                .fin(user.getFin())
                .role(user.getRole())
                .build();
    }
}
