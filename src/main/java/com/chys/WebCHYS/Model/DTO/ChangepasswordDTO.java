package com.chys.WebCHYS.Model.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangepasswordDTO {

    @Size(max = 20, message = "Tên đăng nhập không được vượt quá 20 ký tự")
    private String username;

    @Size(min = 8, max = 16, message = "Mật khẩu phải từ 8 đến 16 ký tự")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "Mật khẩu phải chứa ít nhất 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt"
    )
    private String password;

    @NotBlank(message = "Mật khẩu nhập lại không được để trống")
    private String retypePassword;


    @Email(message = "Email không đúng định dạng")
    private String email;


    @Size(min = 10, max = 15, message = "Số điện thoại phải từ 10 đến 15 ký tự")
    @Pattern(regexp = "\\d+", message = "Số điện thoại chỉ được chứa chữ số")
    private String phoneNumber;

}
