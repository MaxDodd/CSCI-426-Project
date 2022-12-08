import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InspParser {
    private Document templatesDoc;

    public void parse(){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();

            templatesDoc = builder.parse(new File("src/data/inspirations.xml"));

            templatesDoc.getDocumentElement().normalize();

        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Meal> search(ArrayList<String> searchedIngredients, ArrayList<String> mealTypes,
                                  ArrayList<String> convenienceTags, ArrayList<String> restrictions){
        Node meal;
        Node detail;
        NodeList mealDetailsList;
        NodeList meals = templatesDoc.getElementsByTagName("item");
        ArrayList<String> searchedTags = new ArrayList<>();
        List<String> ingredientsInMeal;
        List<String> tagsList;
        ArrayList<Node> filteredMeals = new ArrayList<>();
        ArrayList<Meal> mealsInResults = new ArrayList<Meal>();


        //for each meal
        for (int i = 0; i < meals.getLength(); i++){
            meal = meals.item(i);
            mealDetailsList = meal.getChildNodes();

            //filter out the meals that don't have the meal type or convenience of the meal type selected
            for (int j = 0; j < mealDetailsList.getLength(); j++){

                detail = mealDetailsList.item(j); //grab the jth detail of the meal

                if (detail.getNodeName() == "tags"){
                    //if that detail is the tags
                    tagsList = Arrays.asList(detail.getTextContent().split(" "));
                    tagsList = cleanStrings(tagsList);
                    searchedTags.addAll(mealTypes);
                    searchedTags.addAll(convenienceTags);

                    //check if the meal has all the tags
                    if (searchDetailTags(searchedTags, tagsList)){
                        //if so add it to the filtered list
                        filteredMeals.add(meal);
                    }
                }
            }
        }

        //from the list of meals that have the tags: remove the meals that don't have any
        //of the ingredients

        //for each filtered meal
        if (searchedIngredients.size() == 0){
            Node filteredMeal;
            Meal m;
            for (int i = 0; i < filteredMeals.size(); i++){
                m = new Meal(); //create meal object
                filteredMeal = filteredMeals.get(i);
                m.setName(getMealDetail(filteredMeal, "name"));
                m.setDesc(getMealDetail(filteredMeal, "desc"));
                m.setImg(getMealDetail(filteredMeal, "img"));
                mealsInResults.add(m);
            }
        }
        for (int i = 0; i < filteredMeals.size(); i ++){
            Meal m = new Meal(); //create meal object
            Node filteredMeal = filteredMeals.get(i);
            mealDetailsList = filteredMeal.getChildNodes();
            //for each detail
            for (int j = 0; j < mealDetailsList.getLength(); j ++){
                detail = mealDetailsList.item(j);
                //check if it is the ingredient detail
                if (detail.getNodeName() == "ingredients"){
                    ingredientsInMeal = Arrays.asList(detail.getTextContent().split(" "));
                    ingredientsInMeal = cleanStrings(ingredientsInMeal);

                    //check if it contains at least on of the ingredients searched
                    if (searchDetailIngredients(searchedIngredients, ingredientsInMeal)){
                        m.setName(getMealDetail(filteredMeal, "name"));
                        m.setDesc(getMealDetail(filteredMeal, "desc"));
                        m.setImg(getMealDetail(filteredMeal, "img"));
                        mealsInResults.add(m);
                    }
                }
            }
        }


        return mealsInResults;
    }

    private String getMealDetail(Node meal, String nodeName){
        NodeList children = meal.getChildNodes();
        for (int i = 0; i < children.getLength(); i++){
            if (children.item(i).getNodeName() == nodeName){
                return children.item(i).getTextContent();
            }
        }

        return "NAME NOT FOUND";
    }


    private List cleanStrings(List<String> list){
        String s;
        for (int i = 0; i < list.size(); i++){
            s = list.get(i);
            list.set(i, s.replaceAll("\t",""));
            s = list.get(i);
            list.set(i, s.replaceAll("\n",""));
        }

        return list;
    }

    private boolean searchDetailIngredients(ArrayList<String> searchedDetailList, List<String> DetailList){
        for (int i = 0; i < searchedDetailList.size(); i ++){
            if (DetailList.contains(searchedDetailList.get(i))){
                return true;
            }
        }
        return false;
    }

    private boolean searchDetailTags(ArrayList<String> searched, List<String> tagsList){
        for (int i = 0; i < searched.size(); i ++){
            if (!tagsList.contains(searched.get(i))){
                return false;
            }
        }

        return true;
    }

}
