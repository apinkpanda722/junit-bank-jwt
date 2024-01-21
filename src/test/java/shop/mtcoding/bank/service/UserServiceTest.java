package shop.mtcoding.bank.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.mtcoding.bank.config.dummy.DummyObject;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;
import shop.mtcoding.bank.dto.user.UserRespDto.JoinRespDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


// Spring 관련 Bean들이 하나도 없는 환경!!
@ExtendWith(MockitoExtension.class)
class UserServiceTest extends DummyObject {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy // 스파이는 Mock과 달리 Spring IoC에 있는 진짜 빈을 가져온다. 그리고 그걸 InjectMocks에 넣는다.
    private BCryptPasswordEncoder passwordEncoder;

    @DisplayName("회원가입을 테스트한다.")
    @Test
    void 회원가입_test() {
        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("ssar");
        joinReqDto.setPassword("1234");
        joinReqDto.setEmail("ssar@nate.com");
        joinReqDto.setFullname("쌀");

        // stub 1
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
//        when(userRepository.findByUserName(any())).thenReturn(Optional.of(new User()));

        // stub 2
        User ssar = newMockUser(1L, "ssar", "쌀");
        when(userRepository.save(any())).thenReturn(ssar);



        // when
        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);

        // then
        assertThat(joinRespDto.getId()).isEqualTo(1L);
        assertThat(joinRespDto.getUsername()).isEqualTo("ssar");
    }
}