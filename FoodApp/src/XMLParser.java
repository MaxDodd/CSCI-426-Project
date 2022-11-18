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


public class XMLParser {
    final int NAME_INDEX = 0;


    private Document templatesDoc;



    public void parse(){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();

            templatesDoc = builder.parse(new File("src/data/list_templates/list_templates.xml"));

            templatesDoc.getDocumentElement().normalize();

        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public ArrayList getTemplateDetails(String tag){
        //tab is the name of the template in the XML file

        //Where the list of details (Name, Ingredients,...)
        ArrayList<String> detailsArray = new ArrayList<String>();

        //Get the template Node
        Node template = templatesDoc.getElementById(tag);
        //Get it's children
        NodeList tempDetails = template.getChildNodes();


        for (int i = 1; i < tempDetails.getLength(); i++){
            Node detail = tempDetails.item(i);

            if (detail.getNodeName() == "ingredient"){
                detailsArray.add(detail.getNodeValue());
            }else if (detail.getNodeName() == "name"){
                detailsArray.set(NAME_INDEX,detail.getNodeValue());
            }
        }

        return detailsArray;
    }

    public ArrayList getTemplateNames(){
        ArrayList<String> names = new ArrayList<String>();
        NodeList templates =  templatesDoc.getElementsByTagName("templates").item(0).getChildNodes();
        for (int i = 0; i < templates.getLength(); i++){
            //Filters out white space
            if (templates.item(i).getNodeType() == Node.ELEMENT_NODE){
                //gets the name we want users to see
                names.add(getName(templates.item(i)));
            }
        }

        return  names;
    }

    private String getName(Node n){
        NodeList children = n.getChildNodes();
        Node child;
        for (int i = 0; i < children.getLength(); i++){
            child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE){
                //WHY IS THIS NOT GETTING THE NAME??
                if (child.getNodeName().equals("name") ){
                    return  child.getTextContent();
                }
            }
        }
        return "NA";
    }

    public ArrayList getItems(String name){
        ArrayList<String> items = new ArrayList<String>();
        Node template = null;
        NodeList templateChildren = null;
        String nameGotten;

        //gets all the names
        NodeList names = templatesDoc.getElementsByTagName("name");

        //Get the Node of the right template based on the name given
        for (int i = 0; i < names.getLength(); i++){
            nameGotten = names.item(i).getTextContent();
            if (nameGotten.equals(name)){
                template = names.item(i).getParentNode();
            }else{
                System.out.println("template does not exist");
            }
        }

        //with the parent Node of the template get the items
        if (template != null){
            templateChildren = template.getChildNodes();

            for (int i = 0; i < templateChildren.getLength(); i ++){
                if ((templateChildren.item(i).getNodeType() == Node.ELEMENT_NODE)
                    && (templateChildren.item(i).getNodeName() != "name")){
                    //Add item to list
                    items.add(templateChildren.item(i).getTextContent());
                }
            }
        }

        return items;
    }

}
