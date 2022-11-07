import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private ImageView GroceriesAddImage;

    @FXML
    private Label GroceriesAddLabel;

    @FXML
    private VBox GroceriesAddTemplateVBox;

    @FXML
    private ImageView GroceriesTemplateImage;

    @FXML
    private Label GroceriesTemplateLabel;

    @FXML
    private VBox GroceriesTemplateVbox;

    @FXML
    private CheckBox InspDairyCheckBox1;

    @FXML
    private CheckBox InspGrainCheckBox;

    @FXML
    private CheckBox InspProtCheckBox;

    @FXML
    private ImageView InspSearchImageView;

    @FXML
    private CheckBox InspSpiceCheckBox;

    @FXML
    private CheckBox InspTypeCheckBox;

    @FXML
    private CheckBox InspVegCheckBox;

    @FXML
    private ImageView LessonImageView;

    @FXML
    private Label LessonLabel;

    @FXML
    private VBox LessonVBox;

    @FXML
    private HBox MealHBox;

    @FXML
    private ImageView MealImage;

    @FXML
    private AnchorPane MealInfoAnchorPane;

    @FXML
    private Label MealNameLabel;

    @FXML
    private Label MealNamePopUpLabel;

    @FXML
    private AnchorPane MealPopUpAnchorPane;

    @FXML
    private Button MealPopUpBackButton;

    @FXML
    private ImageView MealPopUpImageView;

    @FXML
    private TextArea MealPopUpTextArea;

    @FXML
    private ImageView MealResultsBackImageView;

    @FXML
    private Label MeanDescLabel;

    @FXML
    private ImageView RequiredLessonOne;

    @FXML
    private Tab TabInspiration;

    @FXML
    private Tab TabLesson;

    @FXML
    private TabPane Tabs;

    @FXML
    private Button TagOneButton;

    @FXML
    private AnchorPane TemplatePopUpAnchorPane;

    @FXML
    private AnchorPane TemplateBaseAnchorPane;

    @FXML
    private Button TemplatePopUpExitButton;

    @FXML
    private TextArea TemplateTextArea;

    @FXML
    private Label TemplateTitleLabel;

    @FXML
    void InspSearchClickReleased(MouseEvent event) {

    }

    @FXML
    void MealHBoxMouseRelease(MouseEvent event) {

    }

    @FXML
    void MealResultsBackMouseRelease(MouseEvent event) {

    }

    @FXML
    void TemplateImageMouseRelease(MouseEvent event) {
        TemplateBaseAnchorPane.toFront();
    }

    @FXML
    void TemplatePopUpExitMouseRelease(MouseEvent event) {
        TemplateBaseAnchorPane.toBack();
    }

    public void bringTabsToFront(){
        Tabs.toFront();
    }

}
