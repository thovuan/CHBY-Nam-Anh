package com.chys.WebCHYS.Service;

import com.chys.WebCHYS.ExceptionHandler.BadRequestException;
import com.chys.WebCHYS.ExceptionHandler.ConflictException;
import com.chys.WebCHYS.ExceptionHandler.UserNotFoundException;
import com.chys.WebCHYS.Model.DTO.ChangepasswordDTO;
import com.chys.WebCHYS.Model.DTO.RegisterDTO;
import com.chys.WebCHYS.Model.Entity.Users;
import com.chys.WebCHYS.Model.modelInterface.UserMapper;
import com.chys.WebCHYS.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UsersRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Users register(RegisterDTO registerDTO) {
        // Kiểm tra trùng
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new ConflictException("Tên đăng nhập đã tồn tại", "USERNAME_EXISTS");
        }

        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new ConflictException("Email đã tồn tại", "EMAIL_EXISTS");
        }

        if (!registerDTO.getPassword().equals(registerDTO.getRetypePassword())) {
            throw new BadRequestException("Mật khẩu không trùng khớp", "PASSWORD_NOT_MATCH");
        }


        // Tạo entity từ DTO
        Users user = userMapper.rtoEntity(registerDTO);

        // Mã hoá mật khẩu bằng cách đơn giản (khi chưa có Spring Security)
        //user.setPassword(user.getPassword()); // hoặc giữ nguyên nếu chưa cần

//        // Gán mặc định
//        user.setActive(true);
//        user.setRole("USER");

        return userRepository.save(user);
    }

    @Override
    public Users changepassword(ChangepasswordDTO changepasswordDTO) {
        // 1. Kiểm tra username tồn tại
        Users user = userRepository.findByUsername(changepasswordDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Tên đăng nhập không tìm thấy"));

        // 2. Kiểm tra mật khẩu trùng khớp
        if (!changepasswordDTO.getPassword().equals(changepasswordDTO.getRetypePassword())) {
            throw new BadRequestException("Mật khẩu không trùng khớp", "PASSWORD_NOT_MATCH");
        }

        // 3. Cập nhật mật khẩu (nếu chưa dùng encode thì cứ gán trực tiếp)
        user.setPassword(changepasswordDTO.getPassword());

        return userRepository.save(user);
    }

    private String simpleHash(String password) {
        // ⚠️ Chỉ là ví dụ. Sau này nên dùng BCrypt hoặc SHA-256.
        return "**" + password + "**";
    }
}
