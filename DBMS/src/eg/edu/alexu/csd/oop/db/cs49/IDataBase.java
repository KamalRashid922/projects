package eg.edu.alexu.csd.oop.db.cs49;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class IDataBase implements Database {
	int counter=1;
	readxml re=readxml.getInstance();
	WriteXml wr=WriteXml.getInstance();
	Demo demo=Demo.getInstance();
	Parser om=new Parser();
	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		String s="DataBase\\" ;
		//convert the string (databasename) into list of words again
		StringBuilder strb=new StringBuilder(databaseName);
		ArrayList< String> list=new ArrayList<String>();
		while(strb.length()!=0) {
			list.add(strb.substring(0, strb.indexOf(" ")));
			strb.delete(0, strb.indexOf(" ")+1);
		}
		
		//boolean b= list.get(0).contains(".xml");
		boolean b;
		try {
			b = executeStructureQuery(list.get(0));
			if(b==true && dropIfExists==false) {      //create table
				//getting the name of table from the path
				StringBuilder sb=new StringBuilder(list.get(0));
				
				while(true) {
					sb.deleteCharAt(0);
					if(sb.charAt(0)=='\\') {
						sb.deleteCharAt(0);
						break;
					}
				}
				sb.deleteCharAt(sb.length()-1);
				sb.deleteCharAt(sb.length()-1);
				sb.deleteCharAt(sb.length()-1);
				sb.deleteCharAt(sb.length()-1);
				s=s+list.get(0);
			    return wr.createxmlfile(list, sb, s);
			}
			else if(b==false && dropIfExists==false) {   //create database
				s=s+list.get(0);
				File file=new File(s);
				if(file.mkdir()==true) {
					return "completed successfully!";
				}else {
					return databaseName+" is used!";
				}
			}
			else if(b==true && dropIfExists==true) {     //drop table
				databaseName="DataBase\\"+databaseName;
				try {
					File file = new File(databaseName);
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(file);
					doc.getDocumentElement().normalize();
					NodeList rowlist = doc.getElementsByTagName("row");
					int n=rowlist.getLength();
					for (int i = 1; i < n; i++) {
						doc.getDocumentElement().removeChild(rowlist.item(1));
					}
					//transform the DOM Object to an XML File
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer;
					transformer = transformerFactory.newTransformer();
					DOMSource domSource = new DOMSource(doc);
					StreamResult streamResult = new StreamResult(new File(databaseName));
					transformer.transform(domSource, streamResult);
					return "table droped successfully";
				} catch (DOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}else if(b==false && dropIfExists==true) {										  //drop database
				s=s+list.get(0);
				File file=new File(s);
				File[] contents=file.listFiles();
				if(contents != null) {
					for (File f : contents) {
						if(! Files.isSymbolicLink(f.toPath())) {
							f.delete();
						}
					}
				}
				return "database is droped";
			}else {
				return "Syntax Error";
			}
			 
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "Error";
		
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		
		return query.contains(".xml");
	}

	
	//////////////////////////////////////////////////////////////////////////SELECT  <=====
	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		
	
		ArrayList<String> querylist=om.regexchecker ("([A-Za-z]{1,}.{1})|(\\d{1,}.{1})", query);

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(query);
		int i=0;
		 ArrayList<String> a=new  ArrayList<>();
		while(m.find()) {
			a.add(m.group());
			i++;
		}


		String databasename=querylist.get(querylist.size()-1);
		String path="DataBase\\"+databasename+"\\"+querylist.get(querylist.size()-2)+".xml";
		String finalone="";
		for(int j=0;j<path.length();j++)
		{

			if(path.charAt(j)==' ')
			{
				j++;
			}
			finalone=finalone+path.charAt(j);
		}


		re.readfromxmlfile(finalone,a);


				return re.p;
	}

	
	/////|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||//UPDATE <======
	@Override
	public int executeUpdateQuery(String query) throws SQLException {	
		ArrayList<String> l=om.regexchecker("([A-Za-z]{1,}.{1})|(\\d{1,}.{1})", query);
	
		//update
		if(l.get(0).equalsIgnoreCase("update ")) {        //update
			
			try {
				l.remove(0);
				if(!(l.get(1).equalsIgnoreCase("set "))) {
					return 0;
				}
				l.remove(1);
				if(!(l.get(3).equalsIgnoreCase("where "))) {
					return 0;
				}
				l.remove(3);
				//removing the last character
				for (int i = 0; i < l.size(); i++) {
					StringBuilder stringbuilder=new StringBuilder(l.get(i));
					if(stringbuilder.charAt(stringbuilder.length()-1)==' '  ||
						stringbuilder.charAt(stringbuilder.length()-1)=='(' ||
						stringbuilder.charAt(stringbuilder.length()-1)==')' ||
						stringbuilder.charAt(stringbuilder.length()-1)=='\''||
						stringbuilder.charAt(stringbuilder.length()-1)==',' ||
						stringbuilder.charAt(stringbuilder.length()-1)=='=' ) {
						stringbuilder=stringbuilder.deleteCharAt(stringbuilder.length()-1);
						l.remove(i);
						l.add(i, stringbuilder.toString());
					}
				}
				
				return re.update(l);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		else if(l.get(0).equalsIgnoreCase("delete ")) {        //delete
			
			try {
				l.remove(0);
				if(!(l.get(1).equalsIgnoreCase("where "))) {
					return 0;
				}
				l.remove(1);
				
				//removing the last character
				for (int i = 0; i < l.size(); i++) {
					StringBuilder stringbuilder=new StringBuilder(l.get(i));
					if(stringbuilder.charAt(stringbuilder.length()-1)==' '  ||
						stringbuilder.charAt(stringbuilder.length()-1)=='(' ||
						stringbuilder.charAt(stringbuilder.length()-1)==')' ||
						stringbuilder.charAt(stringbuilder.length()-1)=='\''||
						stringbuilder.charAt(stringbuilder.length()-1)==',' ||
						stringbuilder.charAt(stringbuilder.length()-1)=='=' ) {
						stringbuilder=stringbuilder.deleteCharAt(stringbuilder.length()-1);
						l.remove(i);
						l.add(i, stringbuilder.toString());
					}
				}
				return re.delete(l);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
		else if(l.get(l.size()-1).equalsIgnoreCase("insert")) {
			//insert
		
			
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(query);
			ArrayList<String> querylist=om.regexchecker ("([A-Za-z]{1,}.{1})|(\\d{1,}.{1})", query);
			boolean c=false;
			if(querylist.get(querylist.size()-1).equalsIgnoreCase("insert"))
			{
				c=true;
			}
			query="";
			for(int i=0;i<querylist.size()-1;i++)
			{
				query=query+querylist.get(i);
			}
			if(c==true) {
				String s="DataBase\\" ;
				StringBuilder strb=new StringBuilder(query);
				ArrayList< String> list=new ArrayList<String>();
				while(strb.length()!=0) {
					list.add(strb.substring(0, strb.indexOf(" ")));
					strb.delete(0, strb.indexOf(" ")+1);
				}
				StringBuilder sb=new StringBuilder(list.get(0));
				boolean b= list.get(0).contains(".xml");
				
				if(b==true ) {      //create table
					//getting the name of table from the path
				
					while(true) {
						sb.deleteCharAt(0);
						if(sb.charAt(0)=='\\') {
							sb.deleteCharAt(0);
							break;
						}
					}
					sb.deleteCharAt(sb.length()-1);
					sb.deleteCharAt(sb.length()-1);
					sb.deleteCharAt(sb.length()-1);
					sb.deleteCharAt(sb.length()-1);


				}
				s=s+list.get(0);

		ArrayList<String> values=new ArrayList<String>();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).compareTo("values")==0)
			{
				for(int j=i+1;j<list.size();j++)
				{
					
					values.add(list.get(j));
				}
			}
		}
			
			
		try {
	
			return wr.addNodeToXML(values,sb, s);
		} 
			catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
				
				return 0; 
		    
		}
		else {
			return 0;
		}
	}
	
	
	public void createxsd(String p,String s) throws SAXException, IOException
	{
		
		demo.createxsd(p,s);
	}
	
	
}