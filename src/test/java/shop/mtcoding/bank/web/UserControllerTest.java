package shop.mtcoding.bank.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import shop.mtcoding.bank.config.dummy.DummyObject;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:db/teardown.sql")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserControllerTest extends DummyObject {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        userRepository.save(newUser("ssar", "쌀"));
        em.clear();
    }

    @Test
    void join_success_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("love");
        joinReqDto.setPassword("1234");
        joinReqDto.setEmail("love@naver.com");
        joinReqDto.setFullname("러브");

        // 객체 => json
        String requestBody = om.writeValueAsString(joinReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/api/join")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));

//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
    }

    @DisplayName("")
    @Test
    void join_fail_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("ssar");
        joinReqDto.setPassword("1234");
        joinReqDto.setEmail("ssar@naver.com");
        joinReqDto.setFullname("쌀");

        // 객체 => json
        String requestBody = om.writeValueAsString(joinReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/api/join")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));

//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}