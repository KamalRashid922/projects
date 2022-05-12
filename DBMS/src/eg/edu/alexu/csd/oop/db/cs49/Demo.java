package eg.edu.alexu.csd.oop.db.cs49;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.XMLConstants;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Demo {
	 private static Demo instance = new Demo();
	    
	    //private constructor to avoid client applications to use constructor
	    private Demo(){}

	    public static Demo getInstance(){
	        return instance;
	    }
    

    public void createxsd(String path,String finalone) throws SAXException, IOException {
        try {

    		File fXmlFile = new File(path);
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();



            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource domSource = new DOMSource(doc);
            //to create a file use something like this:
            transformer.transform(domSource, new StreamResult(new File(finalone)));
          
        }
        catch (FactoryConfigurationError | ParserConfigurationException | TransformerException e) {
            //handle exception
            e.printStackTrace();
        }
    }

  

}
