package com.chys.WebCHYS.Service;

import com.chys.WebCHYS.ExceptionHandler.BadCredentialsException;
import com.chys.WebCHYS.ExceptionHandler.UserNotFoundException;
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

    public LoginDTO login (String username, String password) {
        Users user = userRepository.findByUsername(username)
                .filter(pw -> password.equals(pw.getPassword()))
                .orElseThrow(() -> new UserNotFoundException("Tài khoản hoặc mật khẩu sai! Vui lòng thử lại"));

        // So sánh mật khẩu (giả sử dùng BCrypt)
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new BadCredentialsException("Sai mật khẩu");
//        }

//        if (!password.equals(user.getPassword())) {
//            throw new BadCredentialsException("Sai mật khẩu");
//        }

        // Đăng nhập thành công => map sang DTO để trả về
        return userMapper.toDto(user);
    }
}
