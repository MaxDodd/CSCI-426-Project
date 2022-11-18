import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Hashtable;



public class Controller {
    final String MAIN_COLOR = "#F8E9E9";
    final String ACCENT_COLOR = "#31E981";
    private int unusedTempIndex = 0;
    private ArrayList<VBox> templates = new ArrayList<VBox>();
    private XMLParser p;

    @FXML
    private ImageView GroceriesTemplateImage;

    @FXML
    private ImageView GroceriesTemplateImage1;

    @FXML
    private ImageView GroceriesTemplateImage2;

    @FXML
    private ImageView GroceriesTemplateImage3;

    @FXML
    private ImageView GroceriesTemplateImage4;

    @FXML
    private ImageView GroceriesTemplateImage5;

    @FXML
    private ImageView GroceriesTemplateImage6;

    @FXML
    private ImageView GroceriesTemplateImage7;

    @FXML
    private ImageView GroceriesTemplateImage8;

    @FXML
    private Label GroceriesTemplateLabel;

    @FXML
    private Label GroceriesTemplateLabel1;

    @FXML
    private Label GroceriesTemplateLabel2;

    @FXML
    private Label GroceriesTemplateLabel3;

    @FXML
    private Label GroceriesTemplateLabel4;

    @FXML
    private Label GroceriesTemplateLabel5;

    @FXML
    private Label GroceriesTemplateLabel6;

    @FXML
    private Label GroceriesTemplateLabel7;

    @FXML
    private Label GroceriesTemplateLabel8;


    @FXML
    private ImageView GroceriesCreateImage;

    @FXML
    private Label GroceriesCreateLabel;

    @FXML
    private VBox CreateTemplateVBox;


    @FXML
    private VBox TemplateVbox;

    @FXML
    private VBox TemplateVbox1;

    @FXML
    private VBox TemplateVbox2;

    @FXML
    private VBox TemplateVbox3;

    @FXML
    private VBox TemplateVbox4;

    @FXML
    private VBox TemplateVbox5;

    @FXML
    private VBox TemplateVbox6;

    @FXML
    private VBox TemplateVbox7;

    @FXML
    private VBox TemplateVbox8;

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
    private ImageView editImage;

    public TabPane getTabs(){
        return Tabs;
    }

    public void tabChanged(Tab oldTab, Tab newTab){

        oldTab.setStyle("-fx-background-color: " + ACCENT_COLOR + ";-fx-border-color: Black;" );
        newTab.setStyle("-fx-background-color: " + MAIN_COLOR + ";-fx-border-color: Black;" );
    }

    @FXML
    void TemplateImageClicked(MouseEvent event) {
        ArrayList<String> items = new ArrayList<String>();
        String itemsString = "";

        //get the name of the template so we can query the XML
        ImageView image = (ImageView) event.getSource();
        VBox vbox =(VBox) image.getParent();
        Label l = (Label) vbox.getChildren().get(1);

        items = p.getItems(l.getText());

        for (int i = 0; i < items.size(); i++){
            itemsString = itemsString + items.get(i) + "\n";
        }

        //Load the text area with the grocery items
        TemplateTextArea.setText(itemsString);
        //set the title of the pop up to the name of the list
        TemplateTitleLabel.setText(l.getText());

        TemplateBaseAnchorPane.toFront();
    }


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
    void displayTemplate() {
        TemplateBaseAnchorPane.toFront();
    }

    @FXML
    void editClicked(MouseEvent event) {
        TemplateTextArea.setEditable(true);
    }

    @FXML
    void TemplatePopUpExitMouseRelease(MouseEvent event) {
        TemplateBaseAnchorPane.toBack();
    }

    public void bringTabsToFront(){
        Tabs.toFront();
    }


    public void templateSetUp(){
        //Adds the create template Vbox too the list
        templates.add(TemplateVbox);
        templates.add(TemplateVbox1);
        templates.add(TemplateVbox2);
        templates.add(TemplateVbox3);
        templates.add(TemplateVbox4);
        templates.add(TemplateVbox5);
        templates.add(TemplateVbox6);
        templates.add(TemplateVbox7);
        templates.add(TemplateVbox8);

        p = new XMLParser();
        p.parse();

    }

    @FXML
    void tabOpened(ActionEvent event) {
        System.out.println("yay!");
    }

    public void addTemplate(String name){
        //Add a template with name 'name'
        VBox theVbox = templates.get(unusedTempIndex);
        Label l = (Label) theVbox.getChildren().get(1);
        l.setText(name);
        theVbox.setVisible(true);
        theVbox.setDisable(false);
        unusedTempIndex ++;
    }


}
