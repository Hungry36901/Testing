package TaskTacking;

import com.github.saacsos.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXRouter.bind(this, primaryStage, "TaskTracking", 1350, 768);

        configRoute();

        FXRouter.goTo("General");
        FXRouter.setAnimationType("fade");
    }
    private static void configRoute(){

        FXRouter.when("General","general.fxml");
        FXRouter.when("Author","author.fxml");
        FXRouter.when("Commands","commands.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
