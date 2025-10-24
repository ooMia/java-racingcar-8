package racingcar.car;

enum CarProblem {

    MOVE_ARGUMENT_OUT_OF_RANGE,
    INVALID_INPUT("잘못된 사용자 입력입니다."), FORWARD_EXCEED_LIMIT, INVALID_CAR_NAME_LENGTH;

    private final String message;

    CarProblem() {
        this.message = this.name();
    }

    CarProblem(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }

}