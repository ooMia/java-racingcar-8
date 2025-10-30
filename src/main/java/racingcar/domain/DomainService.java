package racingcar.domain;

import java.util.List;

public class DomainService {

    public static RacingGame createGame(List<String> split) {
        return new RacingGameImpl(split);
    }

}
