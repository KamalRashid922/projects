package eg.edu.alexu.csd.oop.db.cs49;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class readxml {

	static Object p[][]=new Object[100][100];
	 private static final readxml instance = new readxml();
	    
	    //private constructor to avoid client applications to use constructor
	    private readxml(){}

	    public static readxml getInstance(){
	        return instance;
	    }


	  public static  void readfromxmlfile(String path,ArrayList <String>  a ) {

	    try {

		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
				
	

		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
		NodeList nList = doc.getElementsByTagName("row");
				
		System.out.println("----------------------------");
		if(a.size()==0)
		{
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList list1=nNode.getChildNodes();
				for(int i=0;i<list1.getLength();i++)
				{
					Node n=list1.item(i);
					p[temp][i]="column"+(i+1)+" "+n.getTextContent();
					System.out.println("column"+(i+1)+" "+n.getTextContent());
				}
		}
		}
		}


		else {
	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
			
				
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			NodeList list1=nNode.getChildNodes();
			for(int i=0;i<a.size();i++)
			{
				
				Node n=list1.item(Integer.parseInt( a.get(i))-1);
				p[temp][i]="column"+(i+1)+" "+n.getTextContent();
				System.out.println("column"+(a.get(i))+" "+n.getTextContent());
			}
			
	}
	
	}
		}
		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }
	
	
	//update method
	public int update(ArrayList<String> list) {
		String path="DataBase\\"+list.get(list.size()-1)+list.get(0)+".xml";
		
		try {
			File file = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc;
			NodeList rowlist;
			try {
				doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();
				
				rowlist = doc.getElementsByTagName("row");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();return 0;
			}
			
			
			int x=0,y=0;
			String s1;
			String s2;
			boolean b=true;
			for (int i = 0; i < rowlist.getLength(); i++) {
				 Node node = rowlist.item(i); 
				 if (node.getNodeType() == Node.ELEMENT_NODE) { 
					 Element element = (Element) node; 
					 int numberOfColumns= node.getChildNodes().getLength();
					 
					 if(i==0) {
						 for (int j = 0; j < numberOfColumns; j++) {
							 s1="column"+(j+1);
								if( element.getElementsByTagName(s1).item(0).getTextContent().equals(list.get(3) )){
									x=j+1;
								}else if(element.getElementsByTagName(s1).item(0).getTextContent().equals(list.get(1))) {
									y=j+1;
								}
						 }
						 continue;
					 }
					 s1="column"+x;
					 s2="column"+y;
					 if( element.getElementsByTagName(s1).item(0).getTextContent().equals(list.get(4))){
						 Node n=element.getElementsByTagName(s2).item(0);
						 n.setTextContent(list.get(2));
						 b=false;
					 }
				 }	 
			}
			if(b==true) {
				return 2;
			}

            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;
            transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(path));
            transformer.transform(domSource, streamResult);
            return 1;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return 0;
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return 0;
		}catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}
	
	//delete method
	public int delete (ArrayList<String> list) {
		String path="DataBase\\"+list.get(list.size()-1)+list.get(0)+".xml";
		
		try {
			File file = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			
			NodeList rowlist = doc.getElementsByTagName("row");
			
			int x=0,y=0;
			String s1;
			String s2;
			for (int i = 0; i < rowlist.getLength(); i++) {
				 Node node = rowlist.item(i); 
				 
				 if (node.getNodeType() == Node.ELEMENT_NODE) { 
					 Element element = (Element) node; 
					 int numberOfColumns= node.getChildNodes().getLength();
					 
					 if(i==0) {
						 for (int j = 0; j < numberOfColumns; j++) {
							 s1="column"+(j+1);
								if( element.getElementsByTagName(s1).item(0).getTextContent().equals(list.get(1) )){
									x=j+1;
								}
						 }
						 continue;
					 }
					 s1="column"+x;
					 if( element.getElementsByTagName(s1).item(0).getTextContent().equals(list.get(2))){
						 doc.getDocumentElement().removeChild(node);
					 }	
				 }	 
			}

            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;
            transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(path));
            transformer.transform(domSource, streamResult);
			return 1;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return 0;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return 0;
		}catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return 0;
		}catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();return 0;
		}
		
		
	}
	
	
	
}


