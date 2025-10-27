package racingcar.game;

public enum GameProblem {

    CAR_NAME_DUPLICATE,
    ITERATION_NEGATIVE,
    NO_CAR_EXISTS;

    private final String message;

    GameProblem() {
        this.message = this.name();
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }
}
