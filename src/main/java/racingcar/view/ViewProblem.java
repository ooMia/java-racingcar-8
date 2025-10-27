package racingcar.view;

enum ViewProblem {

    FAILED_TO_PARSE_LONG,
    INTEGER_OUT_OF_BOUND;

    private final String message;

    ViewProblem() {
        this.message = this.name();
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }

    public IllegalArgumentException exception(Exception e) {
        return new IllegalArgumentException(this.message, e);
    }
}
