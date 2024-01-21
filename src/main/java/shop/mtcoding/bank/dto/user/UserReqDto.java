package shop.mtcoding.bank.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserReqDto {

    @Setter
    @Getter
    public static class JoinReqDto {
        // 영문, 숫자 허용, 길이 최소 2 ~ 20 이내
        @Pattern(regexp = "", message = "영문/숫자 2~20자 이내로 작성해 주세요.")
        @NotEmpty // null이거나, 공백일 수 없다.
        private String username;

        // 길이 4 ~ 28
        @NotEmpty
        private String password;

        // 이메일 형시
        @NotEmpty
        private String email;

        // 영어, 한글, 길이 1 ~ 20
        @NotEmpty
        private String fullname;

        public User toEntity(BCryptPasswordEncoder passwordEncoder) {
            return User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .fullname(fullname)
                    .role(UserEnum.CUSTOMER)
                    .build();
        }
    }
}
