package racingcar.car;

public final class CarBuilder {
    private CarBuilder() {
    }

    static class NamedCarBuilder {
        String name;
        NamedCar.NameLengthRule nameLengthRule = NamedCar.NameLengthRule.DEFAULT_RULE;

        NamedCarBuilder name(String name) {
            this.name = name;
            return this;
        }

        NamedCarBuilder nameLengthRule(NamedCar.NameLengthRule rule) {
            this.nameLengthRule = rule;
            return this;
        }

        NamedCar build() {
            return new NamedCar(name, nameLengthRule);
        }
    }

    public static class RacingCarBuilder {
        final NamedCarBuilder namedCarBuilder = new NamedCarBuilder();
        RacingCar.MoveRule moveRule = RacingCar.MoveRule.DEFAULT_RULE;

        public RacingCarBuilder name(String name) {
            namedCarBuilder.name(name);
            return this;
        }

        public RacingCarBuilder nameLengthRule(NamedCar.NameLengthRule rule) {
            namedCarBuilder.nameLengthRule(rule);
            return this;
        }

        public RacingCarBuilder moveRule(RacingCar.MoveRule moveRule) {
            this.moveRule = moveRule;
            return this;
        }

        public RacingCar build() {
            return new RacingCar(namedCarBuilder.name, namedCarBuilder.nameLengthRule, moveRule);
        }
    }

}
