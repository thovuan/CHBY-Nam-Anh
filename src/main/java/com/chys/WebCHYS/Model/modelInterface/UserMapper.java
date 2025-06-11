package com.chys.WebCHYS.Model.modelInterface;

import com.chys.WebCHYS.Model.DTO.ChangepasswordDTO;
import com.chys.WebCHYS.Model.DTO.LoginDTO;
import com.chys.WebCHYS.Model.DTO.RegisterDTO;
import com.chys.WebCHYS.Model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapping từ Entity -> DTO
    LoginDTO toDto(Users user);

    // Mapping từ DTO -> Entity
    Users toEntity(LoginDTO loginDto);

    RegisterDTO rtoDto(Users user);

    Users rtoEntity(RegisterDTO registerDTO);

    ChangepasswordDTO cptoDto(Users user);

    Users cptoEntity (ChangepasswordDTO changepasswordDTO);

}
