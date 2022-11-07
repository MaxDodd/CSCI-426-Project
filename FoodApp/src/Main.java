import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FoodAppUI.fxml"));
        Scene home = new Scene(root);

        home.getStylesheets().add(getClass().getResource("FoodAppStyleSheet.css").toExternalForm());

        primaryStage.setScene(home);
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}

