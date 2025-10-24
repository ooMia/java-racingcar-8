package racingcar.car;

abstract class Car {

    protected final String name;

    protected int distance;

    protected Car(String name) {
        this.name = name;
    }

    protected void forward() throws ArithmeticException {
        try {
            this.distance = Math.addExact(distance, 1);
        } catch (ArithmeticException e) {
            throw CarProblem.FORWARD_EXCEED_LIMIT.exception();
        }
    }

    protected void stop() {
    }
}
