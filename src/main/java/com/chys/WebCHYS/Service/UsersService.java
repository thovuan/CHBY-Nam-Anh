package com.chys.WebCHYS.Service;

import com.chys.WebCHYS.Model.DTO.LoginDTO;
import com.chys.WebCHYS.Model.Users;
import com.chys.WebCHYS.Model.modelInterface.UserMapper;
import com.chys.WebCHYS.Repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository userRepository;
    private final UserMapper userMapper;


    public UsersService(UsersRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public LoginDTO getUserDto(Users user) {
        return userMapper.toDto(user);
    }
}
