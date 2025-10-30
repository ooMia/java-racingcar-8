package racingcar.domain;

import java.util.List;

public interface RacingGame {

    RacingGameImpl.State play();

    RacingGameImpl.Winner winners();

}

class RacingGameImpl implements RacingGame {

    protected List<? extends Car> cars;

    RacingGameImpl() {
    }

    RacingGameImpl(List<String> carNames) {
        cars = carNames.stream().map(CarImpl::new).toList();
        validate();
    }

    private void validate() {
        if (cars.isEmpty()) {
            throw DomainProblem.EMPTY_CARS.exception();
        }
    }

    @Override
    public State play() {
        for (Car car : cars) {
            car.move();
        }
        return currentState();
    }

    State currentState() {
        return State.of(cars);
    }

    @Override
    public Winner winners() {
        return Winner.of(cars);
    }

    record State(List<String> carStates) {
        static State of(List<? extends Car> cars) {
            List<String> carStates = cars.stream().map(Car::toString).toList();
            return new State(carStates);
        }

        @Override
        public String toString() {
            return String.join("\n", carStates);
        }
    }

    record Winner(List<String> carNames) {
        static Winner of(List<? extends Car> cars) {
            Car winner = cars.stream().max(Car::compareTo).orElseThrow();
            List<String> winnerNames = cars.stream()
                    .filter(car -> Winner.isTargetWinner(car, winner))
                    .map(CarImpl.class::cast)
                    .map(car -> car.name)
                    .toList();
            return new Winner(winnerNames);
        }

        static boolean isTargetWinner(Car target, Car winner) {
            return target.compareTo(winner) >= 0;
        }

        @Override
        public String toString() {
            return StringTemplate.winner(carNames);
        }
    }

}
