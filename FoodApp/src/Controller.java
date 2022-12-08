import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;



public class Controller {
    final String MAIN_COLOR = "#F8E9E9";
    final String ACCENT_COLOR = "#31E981";
    private int unusedTempIndex = 0;
    private ArrayList<VBox> templates = new ArrayList<VBox>();
    private XMLParser p;
    private InspParser ip;
    private Boolean templateEditable = false;

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
    private VBox inspirationVbox;

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
    void InspSearchClickReleased(MouseEvent event) throws FileNotFoundException {

        //index 0 is the included and index 1 is the exlcuded

        ArrayList<Meal> mealsFound = new ArrayList<Meal>();
        ArrayList<String> searchedIngredients = new ArrayList<String>();

        searchedIngredients = getIngredientsChecked();
        ArrayList<String> searchedMealType = getSearchedMealType();
        ArrayList<String> searchedConvenience = getSearchedConvenience();
        ArrayList<String> searchedRestrictions = getSearchedRestrictions();

        mealsFound = ip.search(searchedIngredients, searchedMealType, searchedConvenience, searchedRestrictions );

        //Display the results on the results page
        loadResults(mealsFound, searchedIngredients, searchedMealType, searchedConvenience, searchedRestrictions);


        InspResultsAnchorPane.toFront();
    }

    private void loadResults(ArrayList<Meal> mealsFound, ArrayList<String> searchedIng, ArrayList<String> searchedMealType,
                             ArrayList<String> seachedConvenience, ArrayList<String> excludeTags) throws FileNotFoundException {

        ResultsVbox.getChildren().clear();
        ResultsTagsTilePane.getChildren().clear();


        //display tags
        addMealTypeButtons(searchedMealType);
        addConvButtons(seachedConvenience);
        addRestButtons(excludeTags);

        //display results
        for (int i = 0; i < mealsFound.size(); i++){
            displayMeal(mealsFound.get(i));
        }
        //Create new item
    }

    //I know that I could write the next three methods with a lot less copy and pasting but I don't have the time
    //to think about how to do it. I promise I won't do this on the job : )

    private void addRestButtons(ArrayList<String> restrictions){
        int prevSize = tagsSearchedButtons.size();
        for (int i = prevSize; i < (restrictions.size() + prevSize); i++) {
            tagsSearchedButtons.add(new Button());
            tagsSearchedButtons.get(i).getStyleClass().add("searchedRestriction");
            tagsSearchedButtons.get(i).setText(restrictions.get(i - prevSize));
            ResultsTagsTilePane.getChildren().add(tagsSearchedButtons.get(i));
            tagsSearchedButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    //I know this is very bad practice but I know that it will work for now

                    ArrayList<String> newIngred;
                    ArrayList<String> newMealType;
                    ArrayList<String> newConv;
                    ArrayList<String> newRest;
                    ArrayList<Meal> mealsFound;

                    //Get the button then remove it
                    Button b;
                    TilePane tp;
                    b = (Button) actionEvent.getSource();
                    tp = (TilePane) b.getParent();
                    tp.getChildren().remove(b);

                    //Get the list of restrictions then remove the one that you want to delete
                    newRest = getSearchedRestrictions();
                    newRest.remove(b.getText().toLowerCase());
                    uncheckByName(inspirationVbox, b.getText());

                    //get the rest of the filters
                    newIngred = getIngredientsChecked();
                    newMealType = getSearchedMealType();
                    newConv = getSearchedConvenience();

                    //Search and load the meals
                    mealsFound = ip.search(newIngred, newMealType,newConv,newRest);

                    try {
                        loadResults(mealsFound, newIngred, newMealType, newConv, newRest);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void addMealTypeButtons(ArrayList<String> mealTypes){
        int prevSize = tagsSearchedButtons.size();
        for (int i = prevSize; i < (mealTypes.size() + prevSize); i++) {
            tagsSearchedButtons.add(new Button());
            tagsSearchedButtons.get(i).getStyleClass().add("searchedMealType");
            tagsSearchedButtons.get(i).setText(mealTypes.get(i - prevSize));
            ResultsTagsTilePane.getChildren().add(tagsSearchedButtons.get(i));
            tagsSearchedButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    //Same as above
                    ArrayList<String> newIngred;
                    ArrayList<String> newMealType;
                    ArrayList<String> newConv;
                    ArrayList<String> newRest;
                    ArrayList<Meal> mealsFound;

                    Button b;
                    TilePane tp;
                    b = (Button) actionEvent.getSource();
                    tp = (TilePane) b.getParent();
                    tp.getChildren().remove(b);

                    newRest = getSearchedRestrictions();
                    newIngred = getIngredientsChecked();
                    newConv = getSearchedConvenience();

                    newMealType = getSearchedMealType();
                    newMealType.remove(b.getText());
                    uncheckByName(inspirationVbox, b.getText());



                    mealsFound = ip.search(newIngred, newMealType,newConv,newRest);

                    try {
                        loadResults(mealsFound, newIngred, newMealType, newConv, newRest);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void addConvButtons(ArrayList<String> includeTags){
        int prevSize = tagsSearchedButtons.size();
        for (int i = prevSize; i < (includeTags.size() + prevSize); i++) {
            tagsSearchedButtons.add(new Button());
            tagsSearchedButtons.get(i).getStyleClass().add("searchedTag");
            tagsSearchedButtons.get(i).setText(includeTags.get(i - prevSize));
            ResultsTagsTilePane.getChildren().add(tagsSearchedButtons.get(i));
            tagsSearchedButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    //Same as above
                    ArrayList<String> newIngred;
                    ArrayList<String> newMealType;
                    ArrayList<String> newConv;
                    ArrayList<String> newRest;
                    ArrayList<Meal> mealsFound;

                    Button b;
                    TilePane tp;
                    b = (Button) actionEvent.getSource();
                    tp = (TilePane) b.getParent();
                    tp.getChildren().remove(b);

                    newRest = getSearchedRestrictions();
                    newIngred = getIngredientsChecked();
                    newMealType = getSearchedMealType();

                    newConv = getSearchedConvenience();
                    newConv.remove(b.getText());
                    uncheckByName(inspirationVbox, b.getText());

                    mealsFound = ip.search(newIngred, newMealType,newConv,newRest);

                    try {
                        loadResults(mealsFound, newIngred, newMealType, newConv, newRest);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    private void displayMeal(Meal m) throws FileNotFoundException {




        HBox mealHbox = new HBox();
        mealHbox.getStyleClass().add("results");

        VBox wordsVbox = new VBox();

        ImageView mealImage = new ImageView();
        mealImage.getStyleClass().add("img");

        Label mealName = new Label();
        mealName.getStyleClass().add("title");

        //Label mealDesc = new Label();
        //mealDesc.getStyleClass().add("desc");

        mealName.setText(m.getName());
        //mealDesc.setText(m.getDesc());

        if (m.getImg() != ""){
            InputStream stream = new FileInputStream(m.getImg());
            Image image = new Image(stream);
            mealImage.setImage(image);
            mealImage.setStyle("-fx-border-radius: 20px;");
        }
        mealImage.setFitWidth(100);
        mealImage.setFitHeight(100);


        mealHbox.getChildren().add(mealImage);
        mealHbox.getChildren().add(wordsVbox);

        wordsVbox.getChildren().add(mealName);
        //wordsVbox.getChildren().add(mealDesc);


        ResultsVbox.getChildren().add(mealHbox);
    }

    @FXML
    void MealHBoxMouseRelease(MouseEvent event) {

    }

    @FXML
    void MealResultsBackMouseRelease(MouseEvent event) {
        Tabs.toFront();
        uncheckBoxes(inspirationVbox);

    }

    private void uncheckBoxes(Node n){
        boolean canHave = canHavChildren(n);

        if (n.getClass().getName() == "javafx.scene.control.CheckBox"){
            CheckBox cb = (CheckBox) n;
            cb.setSelected(false);
        }else if (!canHave || ((Parent) n).getChildrenUnmodifiable().size() <= 0){
            return;
        }else{
            Parent p = (Parent) n;
            ObservableList<Node> children = p.getChildrenUnmodifiable();

            for (int i = 0; i < children.size(); i ++){
                uncheckBoxes(children.get(i));
            }
        }
    }

    private void uncheckByName(Node n, String name){
        boolean canHave = canHavChildren(n);

        if ((n.getClass().getName() == "javafx.scene.control.CheckBox")){
            CheckBox cb = (CheckBox) n;
            if (cb.getText() == name){
                cb.setSelected(false);
            }
            return;
        }else if (!canHave || ((Parent) n).getChildrenUnmodifiable().size() <= 0){
            return;
        }else{
            Parent p = (Parent) n;
            ObservableList<Node> children = p.getChildrenUnmodifiable();

            for (int i = 0; i < children.size(); i ++){
                uncheckBoxes(children.get(i));
            }
        }
    }

    private boolean canHavChildren(Node n){
        try{
            Parent p = (Parent) n;
            p.getChildrenUnmodifiable();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @FXML
    void displayTemplate() {
        TemplateBaseAnchorPane.toFront();
    }

    @FXML
    void editClicked(MouseEvent event) throws FileNotFoundException {
        //if it is already editable
        if(TemplateTextArea.editableProperty().get()){
            //make it uneditable
            TemplateTextArea.setEditable(false);
            InputStream stream = new FileInputStream("C:\\Users\\Maxwell Dodd\\Documents\\CSCI 426\\CSCI-426-Project\\FoodApp\\src\\Images\\Edit.png");
            Image image = new Image(stream);
            editImage.setImage(image);
        }else{
            TemplateTextArea.setEditable(true);

            InputStream stream = new FileInputStream("C:\\Users\\Maxwell Dodd\\Documents\\CSCI 426\\CSCI-426-Project\\FoodApp\\src\\Images\\Done.png");
            Image image = new Image(stream);
            editImage.setImage(image);
        }



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
                    //add ingredient
                    ingredientsChecked.add(cb.getText().toLowerCase());
                    //add category
                    ingredientsChecked.add(titledPane.getText().toLowerCase());

                }
            }
        }

        return ingredientsChecked;
    }


    private ArrayList<String> getSearchedMealType(){
        return getTagsByType(MealTypePane);
    }

    private  ArrayList<String> getSearchedConvenience(){
        return getTagsByType(ConveniencePane);
    }

    private ArrayList<String> getSearchedRestrictions(){
        return getTagsByType(RestrictionsPane);
    }

    private ArrayList<String> getTagsByType(TilePane tp){
        ArrayList<String> tagsChecked = new ArrayList<>();
        ObservableList tagCheckBoxes;
        CheckBox cb;

        tagCheckBoxes = tp.getChildren();

        for (int i = 0; i < tagCheckBoxes.size(); i++){
            cb = (CheckBox) tagCheckBoxes.get(i);
            if (cb.isSelected()){
                System.out.println(cb.getText().toLowerCase());
                tagsChecked.add(cb.getText().toLowerCase());
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
