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

    public static record NameLengthRule(int lowerBound, int upperBound) {
        public static final NameLengthRule DEFAULT_RULE = new NameLengthRule(1, 5);

        void validate(String name) {
            if (name == null) {
                throw CarProblem.CAR_NAME_NON_NULL_CONSTRAINT.exception();
            }
            if (name.length() < lowerBound || upperBound < name.length()) {
                throw CarProblem.CAR_NAME_OUT_OF_BOUND.exception();
            }
        }
    }
}
