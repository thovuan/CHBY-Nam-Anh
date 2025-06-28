package com.chys.WebCHYS.Service;

import com.chys.WebCHYS.Model.DTO.ChangepasswordDTO;
import com.chys.WebCHYS.Model.DTO.RegisterDTO;
import com.chys.WebCHYS.Model.Entity.Users;

public interface AuthService {
    Users register(RegisterDTO registerDTO);

    Users changepassword(ChangepasswordDTO changepasswordDTO);
}
