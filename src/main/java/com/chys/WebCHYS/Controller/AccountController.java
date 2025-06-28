package com.chys.WebCHYS.Controller;

import com.chys.WebCHYS.Model.APIResponse.APIResponse;
import com.chys.WebCHYS.Model.DTO.ChangepasswordDTO;
import com.chys.WebCHYS.Model.DTO.LoginDTO;
import com.chys.WebCHYS.Model.DTO.RegisterDTO;
import com.chys.WebCHYS.Model.Entity.Users;
import com.chys.WebCHYS.Model.modelInterface.UserMapper;
import com.chys.WebCHYS.Service.AuthService;
import com.chys.WebCHYS.Service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/auth")
public class AccountController {

    private final UsersService usersService;

    private final AuthService authService;
    private final UserMapper userMapper;

    public AccountController(UsersService usersService, AuthService authService, UserMapper userMapper) {
        this.usersService = usersService;
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> loginPage(Model model, @RequestBody @Valid LoginDTO loginDTO) {
        LoginDTO userDto = usersService.login(loginDTO.getUsername(), loginDTO.getPassword());

        APIResponse<LoginDTO> response = APIResponse.<LoginDTO>builder()
                .status(HttpStatus.OK.value())
                .datetime(OffsetDateTime.now())
                .message("Đăng nhập thành công")
                .data(userDto)
                .build();



        return ResponseEntity.ok(response);

    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<RegisterDTO>> register(@Valid @RequestBody RegisterDTO registerDTO) {
        Users savedUser = authService.register(registerDTO);
        RegisterDTO responseDto = userMapper.rtoDto(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.<RegisterDTO>builder()
                        .status(201)
                        .datetime(OffsetDateTime.now())
                        .message("Đăng ký thành công")
                        .data(responseDto)
                        .build());
    }

    @PutMapping("/changepassword")
    public ResponseEntity<APIResponse<ChangepasswordDTO>> changepassword(@Valid @RequestBody ChangepasswordDTO changepasswordDTO) {
        Users chps = authService.changepassword(changepasswordDTO);
        ChangepasswordDTO responseDTO = userMapper.cptoDto(chps);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.<ChangepasswordDTO>builder()
                        .status(200)
                        .datetime(OffsetDateTime.now())
                        .message("Đổi mật khẩu thành công")
                        .data(responseDTO)
                        .build());

    }
}
