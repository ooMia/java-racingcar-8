package racingcar.domain;

import java.util.List;

class StringTemplate {
    static String car(String name, int distance) {
        var sb = new StringBuilder(name);
        sb.append(" : ");
        sb.repeat("-", distance);
        return sb.toString();
    }

    static String winner(List<String> carNames) {
        return "최종 우승자 : " + String.join(", ", carNames);
    }
}
