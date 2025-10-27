package racingcar.util.external;

public class MissionUtil {

    public final static MissionUtil instance = new MissionUtil();

    /**
     * Reads a line from user input. This method blocks until the user inputs a line.
     * @return the line read from user input
     */
    public String readLine() {
        return camp.nextstep.edu.missionutils.Console.readLine();
    }

    /**
     * Picks a random number in the given range [startInclusive, endInclusive].
     * For example, if startInclusive is 1 and endInclusive is 3, it may return 1, 2, or 3.
     * @param startInclusive the start of the range (inclusive)
     * @param endInclusive the end of the range (inclusive)
     * @return a random number within the specified range
     */
    public int pickNumberInRange(int startInclusive, int endInclusive) {
        return camp.nextstep.edu.missionutils.Randoms.pickNumberInRange(startInclusive, endInclusive);
    }
}
