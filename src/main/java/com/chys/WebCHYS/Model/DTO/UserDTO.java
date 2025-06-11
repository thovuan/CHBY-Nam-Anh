package com.chys.WebCHYS.Model.DTO;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String password;

    private String email;

    private String phoneNumber;
}
