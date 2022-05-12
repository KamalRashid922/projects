package eg.edu.alexu.csd.oop.db.cs49;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class WriteXml {
	private static final WriteXml instance = new WriteXml();
    
    //private constructor to avoid client applications to use constructor
    private WriteXml(){}

    public static WriteXml getInstance(){
        return instance;
    }
	
static int counter=1;
ArrayList<ArrayList<String>> rows = new ArrayList<>();
	public String createxmlfile(ArrayList<String> list,StringBuilder sb,String s)
	{
		
		
		try {
			
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement(sb.toString()); //table name
            document.appendChild(root);
 
            // row element
            Element row = document.createElement("row");
 
            root.appendChild(row);
 
          
 
            //set columns
            for (int i = 1; i < list.size(); i++) {
             Element column = document.createElement("column"+i);
            	column.appendChild(document.createTextNode(list.get(i)));
            	
	            row.appendChild(column);
			}
            rows.add(list);
           
         
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(s));
 
            transformer.transform(domSource, streamResult);
			 return "completed successfully!";
			
		} catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
		return null;
	}
	public  int addNodeToXML(ArrayList<String> list,StringBuilder sb,String s) throws SAXException, IOException {
try {
counter++;
			
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.parse(new File(s));
 
            // root element
            Element root = document.getDocumentElement(); //table name
            
 
            // row element
            Element row = document.createElement("row");
 
            root.appendChild(row);
 
          
 
            //set columns
            for (int i = 1; i < list.size()+1; i++) {
             Element column = document.createElement("column"+i);
            	column.appendChild(document.createTextNode(list.get(i-1)));
            
	            row.appendChild(column);
	           
			}
            rows.add(list);
         
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
           
            StreamResult streamResult = new StreamResult(new File(s));
 
            transformer.transform(domSource, streamResult);
			 
			
		} catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
		
	return counter;
	
}
}
