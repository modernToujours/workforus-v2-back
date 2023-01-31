package site.workforus.forus.employee.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignRequest {

    @NotBlank(message = "ID를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]*$", message = "ID는 영어 대소문자와 숫자만을 포함해야합니다.")
    @Size(min = 5, max = 10, message = "ID는 5~10 글자로 입력해주세요.")
    private String id;

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]*$", message = "이름은 한글만 입력해주세요.")
    @Size(min = 2, max = 6, message = "이름은 2~6 글자로 입력해주세요.")
    private String name;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])[a-zA-Z\\d`~!@#$%^&*()-_=+]*$", message = "비밀번호는 영문자 대문자와 소문자, 숫자, 특수문자('!@#$%^&+=')를 모두 포함해야 합니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8~15 글자로 입력해주세요.")
    private String password;

}
