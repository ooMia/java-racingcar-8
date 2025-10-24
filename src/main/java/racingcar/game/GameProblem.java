package racingcar.game;

enum GameProblem {
    DUPLICATE_CAR_NAME;

    private final String message;

    GameProblem() {
        this.message = this.name();
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }
}