package com.chys.WebCHYS.Service;

import com.chys.WebCHYS.Model.DTO.RegisterDTO;
import com.chys.WebCHYS.Model.Users;

public interface AuthService {
    Users register(RegisterDTO registerDTO);
}
