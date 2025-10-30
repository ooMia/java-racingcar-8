package racingcar;

public class Application {
    public static void main(String[] args) {
        final var raceGame = new ControllerImpl(Global.TOKENIZER, Global.CONSOLE);
        raceGame.setup();
        raceGame.start();
        raceGame.finish();
    }
}
