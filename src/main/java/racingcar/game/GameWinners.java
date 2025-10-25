package racingcar.game;

import java.util.List;

// TODO: 너 뭐야 DTO야?
// 근데 왜 쓸데없이 출력 방식에 대한 로직이 있어?
public record GameWinners(
        List<String> winnerNames
) {
    @Override
    public String toString() {
        return String.join(", ", winnerNames);
    }
}
