package racingcar.car;

import java.util.Comparator;

import camp.nextstep.edu.missionutils.Randoms;

public class RacingCar extends NamedCar implements Movable {

    private MoveRule moveRule = MoveRule.DEFAULT_RULE;
    private int distance;

    public RacingCar(String name) {
        super(name);
    }

    // BUILDER CONSTRUCTOR
    RacingCar(String name, NameLengthRule nameLengthRule, MoveRule moveRule) {
        super(name, nameLengthRule);
        this.moveRule = moveRule;
    }

    // TEST PURPOSE ONLY
    RacingCar(String name, int distance) {
        super(name);
        this.distance = distance;
    }

    @Override
    public void move() {
        int randomValue = getRandomDice();
        forward(randomValue);
    }

    private int getRandomDice() {
        return Randoms.pickNumberInRange(moveRule.lowerBound, moveRule.upperBound);
    }

    boolean forward(int value) throws IllegalArgumentException {
        try {
            if (moveRule.isForward(value)) {
                this.distance = Math.addExact(distance, 1);
                return true;
            }
            return false;
        } catch (ArithmeticException e) {
            throw CarProblem.FORWARD_EXCEED_LIMIT.exception(e);
        }
    }

    @Override
    public String toString() {
        // TODO : refactor anyway; constant literal ?
        // TODO: char : char - 두 개 받아서 문자열로 출력하는 함수를 만들까?
        var status = new StringBuilder(this.name);
        status.append(" : ");
        status.repeat('-', this.distance);
        return status.toString();
    }

    public static Comparator<RacingCar> comparator() {
        return Comparator.comparingInt(value -> value.distance);
    }

    public static record MoveRule(int minimumDiceToMove, int lowerBound, int upperBound) {
        public static final MoveRule DEFAULT_RULE = new MoveRule(4, 0, 9);

        public boolean isForward(int dice) {
            validate(dice);
            return minimumDiceToMove <= dice;
        }

        private void validate(int dice) {
            if (dice < lowerBound || dice > upperBound) {
                throw CarProblem.MOVE_ARGUMENT_OUT_OF_RANGE.exception();
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private String name;
        private MoveRule moveRule = MoveRule.DEFAULT_RULE;
        private NameLengthRule nameLengthRule = NameLengthRule.DEFAULT_RULE;

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder moveRule(MoveRule moveRule) {
            this.moveRule = moveRule;
            return this;
        }

        Builder nameLengthRule(NameLengthRule nameLengthRule) {
            this.nameLengthRule = nameLengthRule;
            return this;
        }

        RacingCar build() {
            return new RacingCar(name, nameLengthRule, moveRule);
        }
    }
}
