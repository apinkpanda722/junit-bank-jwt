package shop.mtcoding.bank.temp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegexTest {

    @DisplayName("")
    @Test
    void 한글만_허용_test() {
        String value = "한글";
        boolean result = Pattern.matches("^[ㄱ-ㅎ가-힣]+$", value);
        System.out.println("테스트 : " + result);
    }

    @DisplayName("")
    @Test
    void 한글은_허용하지_않는다_test() {
        String value = "abc";
        boolean result = Pattern.matches("^[^ㄱ-ㅎ가-힣]*$", value);
        System.out.println("테스트 : " + result);
    }

    @DisplayName("")
    @Test
    void 영어만_허용_test() {
        String value = "ssar";
        boolean result = Pattern.matches("^[a-zA-Z]+$", value);
        System.out.println("테스트 : " + result);
    }

    @DisplayName("")
    @Test
    void 영어는_허용하지_않는다_test() {
        String value = "가22";
        boolean result = Pattern.matches("^[^a-zA-Z]*$", value);
        System.out.println("테스트 : " + result);
    }

    @DisplayName("")
    @Test
    void 영어와_숫자만_허용한다_test() {
        String value = "ssar123!";
        boolean result = Pattern.matches("^[a-zA-Z0-9]+$", value);
        System.out.println("테스트 : " + result);
    }

    @DisplayName("")
    @Test
    void 영어만_되고_길이는_최소2_최대4이다_test() {
        String value = "ㄱㄱㄹ";
        boolean result = Pattern.matches("^[a-zA-Z]{2,4}$", value);
        System.out.println("테스트 : " + result);
    }

    // username, email, fullname
    @DisplayName("회원가입 Request에서 유저명은 영문/숫자 2~20자 이내로 작성해야 한다.")
    @Test
    void user_username_test() {
        String username = "sdsfffffffffffff23ㄱ";
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,20}$", username);
        System.out.println("테스트 : " + result);
    }

    @DisplayName("회원가입 Request에서 유저풀네임은 영문/한글 1~20자 이내로 작성해야 한다.")
    @Test
    void user_fullname_test() {
        String fullname = "sdsfffffffffffff가";
        boolean result = Pattern.matches("^[a-zA-Z가-힣]{1,20}$", fullname);
        System.out.println("테스트 : " + result);
    }

    @DisplayName("")
    @Test
    void user_email_test() {
        String email = "ssar1@nate.com";
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", email);
        System.out.println("테스트 : " + result);
    }
}
