package az.digital.crystalabsheronapp.mapper;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User fromDtoToEntity(UserDto userDto);

    UserDto fromEntityToDto(User user);
}
