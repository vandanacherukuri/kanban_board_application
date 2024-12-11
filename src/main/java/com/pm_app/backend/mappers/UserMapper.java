package com.pm_app.backend.mappers;

import com.pm_app.backend.dtos.SimplifiedUserDto;
import com.pm_app.backend.dtos.UserGetDto;
import com.pm_app.backend.dtos.UserPostDto;
import com.pm_app.backend.models.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {TaskMapper.class, ToDoMapper.class, ProjectMapper.class}
)
public interface UserMapper {

    UserPostDto UserToUserPostDto(User source);
    User UserPostDtoToUser(UserPostDto source);

    UserGetDto UserToUserGetDto(User source);
    User UserGetDtoToUser(UserGetDto source);

    SimplifiedUserDto UserToSimplifiedUserDto(User source);
    User SimplifiedUserDtoToUser(SimplifiedUserDto source);
}
