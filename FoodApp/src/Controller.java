import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Hashtable;



public class Controller {
    final String MAIN_COLOR = "#F8E9E9";
    final String ACCENT_COLOR = "#31E981";
    private int unusedTempIndex = 0;
    private ArrayList<VBox> templates = new ArrayList<VBox>();
    private XMLParser p;
    private InspParser ip;

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
    private TilePane TagsTilePane;

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
    private TilePane RestrictionsPane;

    @FXML
    private TilePane ConveniencePane;

    @FXML
    private TilePane MealTypePane;

    @FXML
    private ImageView editImage;

    @FXML
    private VBox ResultsVbox;

    @FXML
    private AnchorPane proteinsAnchorPane;

    @FXML
    private TilePane ResultsTagsTilePane;

    @FXML
    private AnchorPane InspResultsAnchorPane;

    @FXML
    private Accordion ingredientsAccordion;

    @FXML
    private HBox tagHbox;

    public TabPane getTabs(){
        return Tabs;
    }

    private ArrayList<Button> tagsSearchedButtons = new ArrayList<>();

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

        //index 0 is the included and index 1 is the exlcuded
        ArrayList[] tagsSearched;

        ArrayList<Meal> mealsFound = new ArrayList<Meal>();
        ArrayList<String> searchedIngredients = new ArrayList<String>();

        searchedIngredients = getIngredientsChecked();
        tagsSearched = getTagsSearched();

        mealsFound = ip.search(searchedIngredients, tagsSearched[0], tagsSearched[1]);

        //Display the results on the results page
        loadResults(mealsFound, searchedIngredients, tagsSearched[0], tagsSearched[1]);


        InspResultsAnchorPane.toFront();
    }

    private void loadResults(ArrayList<Meal> mealsFound, ArrayList<String> searchedIng, ArrayList<String> includeTags,
                             ArrayList<String> excludeTags){
        //tagsSearchedButtons.add(TagOneButton);

        //display tags
        for (int i = 0; i < includeTags.size(); i++){
            tagsSearchedButtons.add(new Button());
            tagsSearchedButtons.get(i).setText(includeTags.get(i));
            ResultsTagsTilePane.getChildren().add(tagsSearchedButtons.get(i));
            tagsSearchedButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Button b;
                    TilePane tp;
                    b = (Button) actionEvent.getSource();
                    tp = (TilePane) b.getParent();
                    tp.getChildren().remove(b);
                }
            });
        }

        //display results
        for (int i = 0; i < mealsFound.size(); i++){
            displayMeal(mealsFound.get(i));
        }
        //Create new item

    }

    private void displayMeal(Meal m){
        HBox mealHbox = new HBox();
        mealHbox.getStyleClass().add("results");

        VBox wordsVbox = new VBox();

        ImageView mealImage = new ImageView();
        mealImage.getStyleClass().add("img");

        Label mealName = new Label();
        mealName.getStyleClass().add("title");

        Label mealDesc = new Label();
        mealDesc.getStyleClass().add("desc");



        mealName.setText(m.getName());
        mealDesc.setText(m.getDesc());
        mealImage.setImage(mealImage.getImage());

        mealHbox.getChildren().add(mealImage);
        mealHbox.getChildren().add(wordsVbox);

        wordsVbox.getChildren().add(mealName);
        wordsVbox.getChildren().add(mealDesc);


        ResultsVbox.getChildren().add(mealHbox);
    }

    @FXML
    void MealHBoxMouseRelease(MouseEvent event) {

    }

    @FXML
    void MealResultsBackMouseRelease(MouseEvent event) {
        Tabs.toFront();
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

    private ArrayList<String> getIngredientsChecked(){
        ArrayList<String> ingredientsChecked = new ArrayList<String>();
        ObservableList category =  ingredientsAccordion.getChildrenUnmodifiable();

        for (int i = 0; i < category.size(); i++){
            TitledPane titledPane = (TitledPane) category.get(i);
            AnchorPane anchorPane = (AnchorPane) titledPane.getContent();
            TilePane tilePane = (TilePane) anchorPane.getChildren().get(0);
            ObservableList ingredients = tilePane.getChildren();

            for(int j = 0; j < ingredients.size(); j++){
                CheckBox cb = (CheckBox) ingredients.get(j);
                if (cb.isSelected()){
                    System.out.println(cb.getText());
                    ingredientsChecked.add(cb.getText());
                }
            }
        }

        return ingredientsChecked;
    }

    private ArrayList[] getTagsSearched(){
        ArrayList[] results = {new ArrayList(),new ArrayList()};

        ArrayList<String> included = new ArrayList<String>();
        //get meal type tags
        ArrayList<String> mealTypesChecked = new ArrayList<String>();
        //get convenience tags
        ArrayList<String> convenienceChecked = new ArrayList<>();
        //get restriction tags
        ArrayList<String> restrictionsChecked = new ArrayList<String>();

        mealTypesChecked = getTagsByType(MealTypePane);
        convenienceChecked = getTagsByType(ConveniencePane);
        restrictionsChecked = getTagsByType(RestrictionsPane);

        included.addAll(mealTypesChecked);
        included.addAll(convenienceChecked);

        results[0] = included;
        results[1] = restrictionsChecked;

        return results;
    }

    private ArrayList<String> getTagsByType(TilePane tp){
        ArrayList<String> tagsChecked = new ArrayList<>();
        ObservableList tagCheckBoxes;
        CheckBox cb;

        tagCheckBoxes = tp.getChildren();

        for (int i = 0; i < tagCheckBoxes.size(); i++){
            cb = (CheckBox) tagCheckBoxes.get(i);
            if (cb.isSelected()){
                tagsChecked.add(cb.getText());
            }
        }

        return tagsChecked;

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

    public void inspSetUp(){
        ip = new InspParser();
        ip.parse();
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
