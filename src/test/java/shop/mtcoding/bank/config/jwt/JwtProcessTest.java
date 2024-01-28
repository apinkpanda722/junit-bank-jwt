package shop.mtcoding.bank.config.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.mtcoding.bank.config.auth.LoginUser;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtProcessTest {

    @DisplayName("")
    @Test
    void create_test() {
        // given
        User user = User.builder()
                .id(1L)
                .role(UserEnum.CUSTOMER)
                .build();

        LoginUser loginUser = new LoginUser(user);

        // when
        String jwtToken = JwtProcess.create(loginUser);

        // then
        assertTrue(jwtToken.startsWith(JwtVo.TOKEN_PREFIX));
    }

    @DisplayName("")
    @Test
    void verify_test() {
        // given
        User user = User.builder()
                .id(1L)
                .role(UserEnum.CUSTOMER)
                .build();

        LoginUser loginUser = new LoginUser(user);
        String jwtToken = JwtProcess.create(loginUser);
        String replacedJwtToken = jwtToken.replace(JwtVo.TOKEN_PREFIX, "");

        // when
        LoginUser verifiedLoginUser = JwtProcess.verify(replacedJwtToken);
        System.out.println("테스트 : " + verifiedLoginUser.getUser().getId());
        System.out.println("테스트 : " + verifiedLoginUser.getUser().getRole());

        // then
        assertThat(verifiedLoginUser.getUser().getId()).isEqualTo(1L);
        assertThat(verifiedLoginUser.getUser().getRole()).isEqualTo(UserEnum.CUSTOMER);
    }

}