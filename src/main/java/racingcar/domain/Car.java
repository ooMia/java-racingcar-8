package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public interface Car extends Comparable<Car> {
    boolean move();
}

class CarImpl implements Car {

    final String name;
    protected int distance;

    CarImpl(String name) {
        NameRule.DEFAULT.validate(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return StringTemplate.car(name, distance);
    }

    @Override
    public boolean move() {
        int dice = Randoms.pickNumberInRange(0, 9);
        return forward(dice);
    }

    boolean forward(int dice) {
        if (dice >= 4) {
            distance++;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Car o) {
        if (o instanceof CarImpl other) {
            return Integer.compare(this.distance, other.distance);
        }
        throw DomainProblem.UNSUPPORTED_COMPARISON.exception();
    }

    record NameRule(int minInclusive, int maxInclusive) {
        static final NameRule DEFAULT = new NameRule(1, 5);

        void validate(String name) {
            if (!isValidLength(name)) {
                throw DomainProblem.INVALID_CAR_NAME.exception();
            }
        }

        private boolean isValidLength(String name) {
            int length = name.length();
            return minInclusive <= length && length <= maxInclusive;
        }
    }

}
