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

}
