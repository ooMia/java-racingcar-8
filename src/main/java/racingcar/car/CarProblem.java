package racingcar.car;

enum CarProblem {

    MOVE_ARGUMENT_OUT_OF_RANGE,
    FORWARD_EXCEED_LIMIT,
    NAME_OUT_OF_BOUND,
    NAME_NOT_PRINTABLE_CONSTRAINT;

    private final String message;

    CarProblem() {
        this.message = this.name();
    }

    IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }

    IllegalArgumentException exception(Exception e) {
        return new IllegalArgumentException(this.message, e);
    }
}
