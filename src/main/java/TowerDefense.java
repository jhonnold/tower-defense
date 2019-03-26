import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TowerDefense extends Application implements Runnable {
    // Game Loop Variables
    public Boolean running = true;
    private final Integer TICKS_PER_SECOND = 120;
    private final Integer SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private final Integer MAX_FRAMESKIP = 5;
    private static Long CURRENT_GAME_TICK = 0l;

    // Drawing Variables
    private final Integer FRAMES_PER_SECOND = 60;
    private final Double FRAME_DURATION =  1 / (double) FRAMES_PER_SECOND;

    @Override
    public void start(Stage primaryStage) {
        // Set the frame rate to ~60 FPS
        Timeline animationLoop = new Timeline();
        animationLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.seconds(FRAME_DURATION), (ActionEvent event) -> {
            System.out.printf("Drawing: I happen %d times a second\n", FRAMES_PER_SECOND);
        });

        animationLoop.getKeyFrames().add(kf);
        animationLoop.play();

        new Thread(this, "Game Loop").start();

        primaryStage.setTitle("Tower Defense");
        primaryStage.show();
    }

    @Override
    public void run() {
        Long next_game_tick = System.currentTimeMillis();
        Integer loops;

        while (running) {
            loops = 0;

            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
                // This loop is to update the game. Not to draw it.
                next_game_tick += SKIP_TICKS;
                loops++;
                CURRENT_GAME_TICK++;
                System.out.printf("Updating: I happen %d times a second\n", TICKS_PER_SECOND);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
