package cardgame.utils;

public final class ConsoleUtil {

    // clear the console
    public static void clear() {
        System.out.print("\033[1;1H\033[2J");
    }
}
