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

    public ArrayList<Meal> search(ArrayList<String> searchedIngredients, ArrayList<String> includeTags, ArrayList<String> excludeTags){
        Node meal;
        Node detail;
        NodeList mealDetailsList;
        NodeList meals = templatesDoc.getElementsByTagName("item");
        List<String> ingredientsInMeal;
        List<String> tags;
        ArrayList<Meal> mealsInResults = new ArrayList<Meal>();


        //for each meal
        for (int i = 0; i < meals.getLength(); i++){
            meal = meals.item(i);
            mealDetailsList = meal.getChildNodes();


            for (int j = 0; j < mealDetailsList.getLength(); j ++){
                //search the ingredients. if it includes any of the searched items then add it
                detail = mealDetailsList.item(j);
                Meal m = new Meal();
                if (detail.getNodeName() == "ingredients"){
                    //see if any of the ingredients match
                    ingredientsInMeal = Arrays.asList(detail.getTextContent().split(" "));
                    ingredientsInMeal = cleanStrings(ingredientsInMeal);
                    if (searchDetail(searchedIngredients, ingredientsInMeal)){
                        //if any ingredients match
                        m.setName(getMealChild(meal, "name"));
                        m.setDesc(getMealChild(meal, "desc"));
                        m.setImg(getMealChild(meal, "img"));
                        mealsInResults.add(m);
                    }
                }else if (detail.getNodeName() == "tags"){
                    //search the tags
                    tags = Arrays.asList(detail.getTextContent().split(" "));

                    if (searchDetail(includeTags, tags)){
                        m.setName(getMealChild(meal, "name"));
                        m.setDesc(getMealChild(meal, "desc"));
                        m.setImg(getMealChild(meal, "img"));
                        mealsInResults.add(m);
                    }
                }

            }
        }

        return mealsInResults;
    }

    private String getMealChild(Node meal, String nodeName){
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

    private boolean searchDetail(ArrayList<String> searchedDetailList, List<String> DetailList){
        for (int i = 0; i < searchedDetailList.size(); i ++){
            if (DetailList.contains(searchedDetailList.get(i))){
                return true;
            }
        }
        return false;
    }

}
