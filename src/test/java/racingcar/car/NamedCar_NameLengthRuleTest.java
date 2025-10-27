package racingcar.car;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.car.NamedCar.NameLengthRule;

public class NamedCar_NameLengthRuleTest {

    @Test
    void testInstantiateFailWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new NamedCar(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef", "가나다라마사"})
    void testInstantiateFailWithInvalidNames(String name) {
        assertThrows(IllegalArgumentException.class, () -> new NamedCar(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가나다라마", "abcde"})
    void testInstantiateSuccessWithValidNames(String name) {
        assertDoesNotThrow(() -> new NamedCar(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가나다", "abc", "a", "가"})
    void testInstantiateWithCustomRuleSuccess(String name) {
        var givenNameRule = new NameLengthRule(0, 3);
        assertDoesNotThrow(() -> new NamedCar(name, givenNameRule));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가나다라", "abcd", "", "a", "가"})
    void testInstantiateWithCustomRuleFailWhenOutOfBound(String name) {
        var givenNameRule = new NameLengthRule(2, 3);
        assertThrows(IllegalArgumentException.class, () -> new NamedCar(name, givenNameRule));
    }

}
