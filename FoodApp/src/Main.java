import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private Controller c;
    private XMLParser p;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Gets the XML parser
        p = new XMLParser();
        p.parse();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FoodAppUI.fxml"));
        Parent root = loader.load();

        //gets the controller of the UI
        c = (Controller)loader.getController();

        Scene home = new Scene(root);
        primaryStage.setScene(home);
        primaryStage.show();

        setUpGroceries();



    }

    private void setUpGroceries(){
        //gets the names of the templates and adds them to the UI
        //Adding more templates on the screen as needed

        ArrayList<String> names = new ArrayList<String>();

        names = p.getTemplateNames();
        c.templateSetUp();

        for (int i = 0; i < names.size(); i++){
            c.addTemplate(names.get(i));
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}

