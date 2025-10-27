package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,a", "가,a,가",})
    void throwWhenNameDuplicate(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input, "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    @Nested
    class ParsingNameFromInputContainBlanksTest {

        @ParameterizedTest
        @ValueSource(strings = {"abcdef", "가나다라마바", "a,a bb a,b"})
        void throwWhenNameTooLong(String input) {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException(input, "1"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"a, abcdf ", " 가나다라1 , 가나다라2 , 가나다라3 ",})
        void successWhenNameLengthEqualToOrLessThanFiveWithLeadingOrTrailingSpaces(String names) {
            assertSimpleTest(() ->
                    assertThatNoException()
                            .isThrownBy(() -> runException(names, "1"))
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"", ",", " , ", ",,", ", ,", " , , "})
        void throwWhenInvalidNameWithBlanksOrEmpty(String input) {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException(input, "1"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }
    }

    @Nested
    class ParsingIterationTest {

        @ParameterizedTest
        @ValueSource(strings = {"-1", "2147483648", "4294967298", "9223372036854775807"})
        void throwWhenIterationNotInRangeOfInteger(String iteration) {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", iteration))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void throwWhenIterationNotInteger() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "3.3"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void successWhenIterationZero() {
            assertSimpleTest(() ->
                    assertThatNoException()
                            .isThrownBy(() -> runException("pobi,woni", "0"))
            );
        }
    }
}
