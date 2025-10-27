package racingcar.util.external;

public class MissionUtil {

    public static MissionUtil instance = new MissionUtil();

    public String readLine() {
        return camp.nextstep.edu.missionutils.Console.readLine();
    }

    public int pickNumberInRange(int startInclusive, int endInclusive) {
        return camp.nextstep.edu.missionutils.Randoms.pickNumberInRange(startInclusive, endInclusive);
    }
}
