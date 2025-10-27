package racingcar.car;

import java.util.Comparator;
import racingcar.util.external.MissionUtil;
import racingcar.view.OutputView;

public class RacingCar extends NamedCar {

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

    public static Comparator<RacingCar> comparator() {
        return Comparator.comparingInt(car -> car.distance);
    }

    public void move() {
        int randomValue = getRandomDice();
        forward(randomValue);
    }

    private int getRandomDice() {
        return MissionUtil.instance.pickNumberInRange(moveRule.lowerBound, moveRule.upperBound);
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
        return OutputView.racingCar(name, distance);
    }

    public record MoveRule(int lowerBound, int minimumDiceToMove, int upperBound) {
        public static final MoveRule DEFAULT_RULE = new MoveRule(0, 4, 9);

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
}
