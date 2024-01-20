package shop.mtcoding.bank.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc // Mock(가짜) 환경에 MockMvc가 등록됨
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SecurityConfigTest {

    // 가짜 환경에 등록된 MockMvc를 DI함.
    @Autowired
    private MockMvc mvc;

    // 서버는 일관성있게 에러가 리턴해야 한다.
    // 서버가 모르는 에러가 프론트한테 전달되지 않게, 서버에서 직접 다 제어가능 해야 한다.
    @DisplayName("인증 테스트")
    @Test
    void authentication_test() throws Exception {
        // given // when
        ResultActions resultActions = mvc.perform(get("/api/s/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();

        // then
        assertThat(httpStatusCode).isEqualTo(401);
    }

    @DisplayName("인가 테스트")
    @Test
    void authorization_test() throws Exception{
        // given // when
        ResultActions resultActions = mvc.perform(get("/api/admin/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();

        // then
        assertThat(httpStatusCode).isEqualTo(401);
    }
}