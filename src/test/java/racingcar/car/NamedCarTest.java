package racingcar.car;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.car.CarBuilder.NamedCarBuilder;
import racingcar.car.NamedCar.NameLengthRule;

class NamedCarTest {

    private NamedCarBuilder builder;

    @BeforeEach
    void setUp() {
        this.builder = new NamedCarBuilder();
    }

    @Test
    void testInstantiateFailWithNull() {
        assertThrows(IllegalArgumentException.class, () -> builder.name(null).build());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef", "가나다라마사"})
    void testInstantiateFailWithInvalidNames(String invalidName) {
        assertThrows(IllegalArgumentException.class, () -> builder.name(invalidName).build());
    }

    @ParameterizedTest
    @ValueSource(strings = {"가나다라마", "abcde"})
    void testInstantiateSuccessWithValidNames(String validName) {
        assertDoesNotThrow(() -> builder.name(validName).build());
    }

    @ParameterizedTest
    @ValueSource(strings = {"가나다", "abc", "a", "가"})
    void testInstantiateWithCustomRuleSuccess(String name) {
        var givenNameRule = new NameLengthRule(0, 3);
        assertDoesNotThrow(() -> builder.name(name).nameLengthRule(givenNameRule).build());
    }

    @ParameterizedTest
    @ValueSource(strings = {"가나다라", "abcd", "", "a", "가"})
    void testInstantiateWithCustomRuleFailWhenOutOfBound(String name) {
        var givenNameRule = new NameLengthRule(2, 3);
        assertThrows(IllegalArgumentException.class, () -> builder.name(name).nameLengthRule(givenNameRule).build());
    }

}
