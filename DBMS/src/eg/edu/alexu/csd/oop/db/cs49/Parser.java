package eg.edu.alexu.csd.oop.db.cs49;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class Parser {
	static GUI frame;
	static String currentDatabase="DataBase\\";
	static ArrayList<String> tablesname=new ArrayList();
	
	public static void main(String[] args) {
		
		frame=new GUI("DBMS");
		frame.setVisible(true);
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
	
	
		frame.btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parse(frame.command.getText());
			}
		});
	
		
	}
	
	public static void parse (String query) {
		IDataBase d=new IDataBase();
		//using regex
		query=query+" ";
		ArrayList<String> list=regexchecker("([A-Za-z]{1,}.{1})|(\\d{1,}.{1})", query);
		//removing the last character
		for (int i = 0; i < list.size(); i++) {
			StringBuilder sb=new StringBuilder(list.get(i));
			sb=sb.deleteCharAt(sb.length()-1);
			list.remove(i);
			list.add(i, sb.toString());
		}
		
		
		try {
			
			if(list.get(0).equalsIgnoreCase("create")) {           //create
				
				
				if(list.get(1).equalsIgnoreCase("database")) {                 //database
					if(list.size()!=3) {
						frame.label.setText("Syntax Error");
						return;
					}
					frame.label.setText(d.createDatabase(list.get(2)+" " ,false));
					currentDatabase=list.get(2)+"\\";
					
				}else if(list.get(1).equalsIgnoreCase("table")) {              //table
					if(list.size()<=4) {
						frame.label.setText("Syntax Error");
						return;
					}
					String str=currentDatabase+list.get(2)+".xml ";
					for (int i = 3; i < list.size(); i++) {
						str=str+list.get(i)+" ";
					}
					tablesname.add(list.get(2));
					frame.label.setText(d.createDatabase(str ,false));
					
				}else {
					frame.label.setText("Can't be created!");
				}
			}
			
			else if(list.get(0).equalsIgnoreCase("drop")) {        //drop
				if(list.size()!=3) {
					frame.label.setText("Syntax Error");
					return;
				}
				if(list.get(1).equalsIgnoreCase("database")) {
					frame.label.setText(d.createDatabase(list.get(2)+" " ,true)); //drop data base
					
				}else if(list.get(1).equalsIgnoreCase("table")) {              //drop table
					frame.label.setText(d.createDatabase(currentDatabase+list.get(2)+".xml " ,true));
		
				}else {
					frame.label.setText("Can't be droped!");
				}
			}
			else if(list.get(0).equalsIgnoreCase("use")) {         //use
				if(list.size()!=2) {
					frame.label.setText("Syntax Error");
					return;
				}
				frame.label.setText(d.createDatabase(list.get(1)+" " ,false));
				currentDatabase = list.get(1)+"\\";
			}
			else if(list.get(0).equalsIgnoreCase("select")){         //select
				int b=0;;
				if(list.get(1).equalsIgnoreCase("coulmn"))
				{
					int i=1;
				while(list.get(i).equalsIgnoreCase("coulmn"))	
				{
					i++;
				}
				System.out.println(i);
				if(list.get(i).equalsIgnoreCase("from")) {
					i++;
					
				for(int j=0;j<tablesname.size();j++)
				{
					
					if(list.get(i).equalsIgnoreCase(tablesname.get(j)))
							{
						        b=1;
						        break;
							}
					
				}
				}
				}
				else if(list.get(1).equalsIgnoreCase("from"))
				{
					for(int j=0;j<tablesname.size();j++)
					{
						
						if(list.get(2).equalsIgnoreCase(tablesname.get(j)))
								{
							        b=1;
							        break;
								}
						
					}
				}
				if(b==1) {
				query=query+currentDatabase;
				
				d.executeQuery(query);
				}
				else {
					System.out.println("the input is not correct please check again");
				}
				
				
			}
			else if(list.get(0).equalsIgnoreCase("insert")){         //insert
				int b=0;
				String s="DataBase\\" ;
				String str1=currentDatabase+"\\"+list.get(2)+".xml ";
				String str2=currentDatabase+"\\"+list.get(2)+".xsd ";
				String n=str1;
				if(list.get(1).equalsIgnoreCase("into"))
				{
					for(int j=0;j<tablesname.size();j++)
					{
						
						if(list.get(2).equalsIgnoreCase(tablesname.get(j)))
								{
							        b++;
							        break;
								}
						
					}
					int i=3;
					while(!list.get(i).equalsIgnoreCase("values"))
					{
						if(!list.get(i).equalsIgnoreCase("coulmn"))
						{
							b++;
							break;
						}
						i++;
					}
					
				}
				if(b<2) {
					
				
				String str=currentDatabase+"\\"+list.get(2)+".xml ";
				for (int i = 3; i < list.size(); i++) {
					str=str+list.get(i)+" ";
				}
				str=str+"insert";
				d.executeUpdateQuery(str);
				}
				else if(list.get(4).equalsIgnoreCase("values"))  {
					System.out.println("the input is not correct please check again");
				}
				else {
					System.out.println("the input is not correct please check again");
					
				}
				d.createxsd(s+n,s+str2);
				
				
			}
			
			else if(list.get(0).equalsIgnoreCase("update")){            //update
				if(list.size()!=8) {
					frame.label.setText("Syntax Error");
					return;
				}
				
				int x=d.executeUpdateQuery(query+" "+currentDatabase+" ");
				if(x==1) {
					frame.label.setText("Done Successfully!");
				}
				else if(x==0) {
					frame.label.setText("Syntax Error");
				}else {
					frame.label.setText("Not Found");
				}
			}
			
			else if(list.get(0).equalsIgnoreCase("delete")) {        //delete
				if(list.size()!=5) {
					frame.label.setText("Syntax Error");
					return;
				}
				if(d.executeUpdateQuery(query+" "+currentDatabase+" ")==1) {
					frame.label.setText("Done Successfully!");
				}else {
					frame.label.setText("Syntax Error");
				}
			}else {
				frame.label.setText("Syntax Error");
			}
				
		} catch (Exception e) {
			frame.label.setText("Syntax Error");
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> regexchecker(String regex ,String str) {
		ArrayList<String> list=new ArrayList<String>();
		Pattern checkregex=Pattern.compile(regex);
		Matcher regexmatcher=checkregex.matcher(str);
		while(regexmatcher.find()) {
			if(regexmatcher.group().length() !=0 ) {
				list.add(regexmatcher.group());
			}
		}
		//System.out.println(list.size());
		return list;
	}

}