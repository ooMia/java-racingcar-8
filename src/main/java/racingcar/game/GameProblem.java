package racingcar.game;

// TODO: 딱 이거 하나 쓰려고 쓰는 게 맞아?
// 여기에 더 추가하거나 모든 Problem 통일하거나
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