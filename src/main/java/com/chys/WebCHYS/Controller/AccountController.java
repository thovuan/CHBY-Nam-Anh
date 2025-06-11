package com.chys.WebCHYS.Controller;

import com.chys.WebCHYS.Model.APIResponse.APIResponse;
import com.chys.WebCHYS.Model.DTO.LoginDTO;
import com.chys.WebCHYS.Model.DTO.UserDTO;
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

    public AccountController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> loginPage(Model model, @RequestBody @Valid LoginDTO loginDTO) {
        LoginDTO userDto = usersService.login(loginDTO.getUsername(), loginDTO.getPassword());

        APIResponse<LoginDTO> response = new APIResponse<>(
                HttpStatus.OK.value(),
                OffsetDateTime.now(),
                "Đăng nhập thành công",
                userDto
        );

        return ResponseEntity.ok(response);

    }
}
