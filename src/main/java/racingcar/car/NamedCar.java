package racingcar.car;

class NamedCar {

    public final String name;

    protected NamedCar(String name) {
        NameLengthRule.DEFAULT_RULE.validate(name);
        this.name = name;
    }

    protected NamedCar(String name, NameLengthRule nameLengthRule) {
        nameLengthRule.validate(name);
        this.name = name;
    }

    public record NameLengthRule(int lowerBound, int upperBound) {
        public static final NameLengthRule DEFAULT_RULE = new NameLengthRule(1, 5);

        void validate(String name) {
            if (name == null || name.isBlank()) {
                throw CarProblem.NAME_NOT_PRINTABLE_CONSTRAINT.exception();
            }
            if (name.length() < lowerBound || upperBound < name.length()) {
                throw CarProblem.NAME_OUT_OF_BOUND.exception();
            }
        }
    }
}
