package controllers;

public class GameController implements Runnable {
    private Boolean running;

    private final int TICKS_PER_SECOND = 120;
    private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private final int MAX_FRAMESKIP = 5;
    private static long CURRENT_GAME_TICK = 0;

    public GameController() {
        running = false;
    }

    @Override
    public void run() {
        Long next_game_tick = System.currentTimeMillis();
        Integer loops = 0;
        running = true;

        while (running) {
            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
                // This loop is to update the game. Not to draw it.
                next_game_tick += SKIP_TICKS;
                loops++;
                CURRENT_GAME_TICK++;

                System.out.println("Updating: I happen 120 times a second");
            }
        }
    }
}
