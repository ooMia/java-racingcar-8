package racingcar.game;

import java.util.List;

public record GameWinners(
        List<String> winnerNames
) {
    @Override
    public String toString() {
        return String.join(", ", winnerNames);
    }
}
