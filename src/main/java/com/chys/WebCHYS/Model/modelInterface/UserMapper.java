package com.chys.WebCHYS.Model.modelInterface;

import com.chys.WebCHYS.Model.DTO.ChangepasswordDTO;
import com.chys.WebCHYS.Model.DTO.LoginDTO;
import com.chys.WebCHYS.Model.DTO.RegisterDTO;
import com.chys.WebCHYS.Model.Entity.Users;
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

//    @Mapping(target = "accountType", ignore = true)
    Users rtoEntity(RegisterDTO registerDTO);

//    @AfterMapping
//    protected void setAccountType(RegisterDTO dto, @MappingTarget Users user) {
//        try {
//            user.setAccountType(AccountType.valueOf(dto.getAccountType().toUpperCase()));
//        } catch (IllegalArgumentException | NullPointerException e) {
//            throw new BadRequestException("Loại tài khoản không hợp lệ: " + dto.getAccountType(), "INVALID_ACCOUNT_TYPE");
//        }
//    }

    ChangepasswordDTO cptoDto(Users user);

    Users cptoEntity (ChangepasswordDTO changepasswordDTO);

}
