package racingcar.car;

// TODO: 과연 Car를 추상클래스로 만들어서 상속받을 필요가 있을까?
// 불필요한 파일을 사용하는 건 아닐까?
// 일단 합친다음에 필요할것 같으면 다시 분리할까?
abstract class Car {

    // TODO: 이거 어차피 불변인데 그냥 공개하는 건 어때?
    protected final String name;

    protected int distance;

    protected Car(String name) {
        this.name = name;
    }

    protected void forward() throws ArithmeticException {
        try {
            this.distance = Math.addExact(distance, 1);
        } catch (ArithmeticException e) {
            // TODO : 여기 e 인자로 받는 식으로 message 수정
            throw CarProblem.FORWARD_EXCEED_LIMIT.exception();
        }
    }

    // TODO: 전진하지 않으면 멈춰있는 것이다. 아무것도 하지 않는 메서드를 살려둘 이유는?
    protected void stop() {
    }
}
