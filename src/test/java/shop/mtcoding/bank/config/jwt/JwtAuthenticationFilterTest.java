package shop.mtcoding.bank.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import shop.mtcoding.bank.config.dummy.DummyObject;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.user.UserReqDto.LoginReqDto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// SpringBootTest 하는 곳에는 전부 다 teardown.sql을 붙여 주자.
@Sql("classpath:db/teardown.sql") // 실행시점 : BeforeEach 실행 직전마다!!
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class JwtAuthenticationFilterTest extends DummyObject {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User ssar = userRepository.save(newUser("ssar", "쌀"));
    }

    @DisplayName("")
    @Test
    void successfulAuthentication_test() throws Exception {
        // given
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setUsername("ssar");
        loginReqDto.setPassword("1234");
        String requestBody = om.writeValueAsString(loginReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/api/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn()
                .getResponse()
                .getContentAsString();

        String jwtToken = resultActions.andReturn()
                .getResponse()
                .getHeader(JwtVo.HEADER);

        System.out.println("테스트 : " + jwtToken);

        // then
        resultActions.andExpect(status().isOk());
        assertNotNull(jwtToken);
        assertTrue(jwtToken.startsWith(JwtVo.TOKEN_PREFIX));
        resultActions.andExpect(jsonPath("$.data.username")
                .value("ssar"));
    }

    @DisplayName("")
    @Test
    void unsuccessfulAuthentication_test() throws Exception{
        // given
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setUsername("ssar");
        loginReqDto.setPassword("12345");
        String requestBody = om.writeValueAsString(loginReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/api/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));

        String responseBody = resultActions.andReturn()
                .getResponse()
                .getContentAsString();

        String jwtToken = resultActions.andReturn()
                .getResponse()
                .getHeader(JwtVo.HEADER);

        System.out.println("테스트 : " + jwtToken);

        // then
        resultActions.andExpect(status().isUnauthorized());
    }

}