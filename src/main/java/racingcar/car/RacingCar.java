package racingcar.car;

public class RacingCar extends Car implements Comparable<RacingCar> {

    // TODO: 그래 여기서 관리하면 변경 상황이 실시간 반영되고 편할 수 있어
    // 하지만 만약 출력 형식이 바뀐다면?
    // 출력이라는 건 이미 RacingCar의 관심사 밖 아니야?
    private final StringBuilder status;

    public RacingCar(String name) {
        super(name);
        validateName(name);
        this.status = buildStatus(name);
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || name.length() > 5) {
            throw CarProblem.INVALID_CAR_NAME_LENGTH.exception();
        }
    }

    private StringBuilder buildStatus(String name) {
        var status = new StringBuilder(name);
        status.append(" : ");
        return status;
    }

    // Convenient constructor for simulation.
    // Forces the car's state as same as it moved for N-times.
    RacingCar(String name, int distance) {
        super(name);
        validateName(name);
        this.status = buildStatus(name);

        while (distance-- > 0) {
            this.move();
        }
    }

    private void move() {
        super.forward();
        status.append('-');
    }

    /**
     * @param dice control argument which decides car's moving behavior
     * @throws IllegalArgumentException argument should in range [0, 10)
     */
    public void conditionalMove(int dice) {
        if (dice < 0 || dice > 9) {
            throw CarProblem.MOVE_ARGUMENT_OUT_OF_RANGE.exception();
        }
        if (dice >= 4) {
            move();
            return;
        }
        super.stop();
    }

    public String name() {
        return super.name;
    }

    @Override
    public String toString() {
        return status.toString();
    }

    // TODO: char : char - 두 개 받아서 문자열로 출력하는 함수를 만들까?

    @Override
    public int compareTo(RacingCar o) {
        return Integer.compare(o.distance, super.distance);
    }
}
