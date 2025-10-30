package racingcar.util;

import racingcar.util.exception.BaseException;

public interface Console {

    default int readInt() throws BaseException {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            throw new BaseException(e);
        }
    }

    String readLine();

    default long readLong() throws BaseException {
        try {
            return Long.parseLong(readLine());
        } catch (NumberFormatException e) {
            throw new BaseException(e);
        }
    }

    default void printLine(Object message) {
        printLine(message.toString());
    }

    default void printLine(String message) {
        System.out.println(message);
    }

    default void printLine() {
        System.out.println();
    }

}
