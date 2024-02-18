package shop.mtcoding.bank.temp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LongTest {

    @DisplayName("")
    @Test
    void long_test3() {
        // given
        Long v1 = 1000L;
        Long v2 = 1000L;

        // when

        // then
        assertThat(v1).isEqualTo(v2);
    }

    @DisplayName("")
    @Test
    void long_test2() {
        // given (2의 8승 - 256범위 (-126 + 127))
         Long v1 = 128L;
         Long v2 = 128L;

        // when
        if (v1 == v2) {
            System.out.println("테스트 : 같습니다.");
        }

        // then
    }

    @DisplayName("")
    @Test
    void long_test() {
        // given
        Long number1 = 1111L;
        Long number2 = 1111L;

        // when
        if (number1.longValue() == number2.longValue()) {
            System.out.println("테스트 : 동일합니다.");
        } else {
            System.out.println("테스트 : 동일하지 않습니다.");
        }

        Long amount1 = 100L;
        Long amount2 = 1000L;

        if (amount1 < amount2) {
            System.out.println("테스트 : amount1이 더 작습니다.");
        } else {
            System.out.println("테스트 : amount1이 더 큽니다.");
        }
        // then
    }
}
