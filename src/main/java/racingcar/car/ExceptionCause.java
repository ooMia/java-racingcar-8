package racingcar.car;

enum ExceptionCause {

    MOVE_ARGUMENT_OUT_OF_RANGE,
    INVALID_INPUT("잘못된 사용자 입력입니다."), FORWARD_EXCEED_LIMIT, INVALID_CAR_NAME_LENGTH;

    private final String message;

    ExceptionCause() {
        this.message = this.name();
    }

    ExceptionCause(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }

}