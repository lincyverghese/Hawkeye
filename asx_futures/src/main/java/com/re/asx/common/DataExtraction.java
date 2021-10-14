package com.re.asx.common;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DataExtraction {

	
public  static ArrayList<FuturesData> getAUdata(String region_name ,String strurl) throws InterruptedException, IOException 
	
	
	{
		
		ArrayList<FuturesData> list=new ArrayList<FuturesData>();
		
			URL url=null;
			
		
		if(region_name.equals("NewSouthWales")){
			url = new URL(strurl);
			System.out.println("entered");}
		else if(region_name.equals("Queensland")){
			url = new URL(strurl);
		}
		else if(region_name.equals("Victoria")){
			
			url = new URL(strurl);
		}
		else if(region_name.equals("SouthAustralia")) {
			url = new URL(strurl);
		}
		
		
			 URLConnection con = url.openConnection();
		        java.io.InputStream is =con.getInputStream();

		        

					
			
		        BufferedReader br = new BufferedReader(new InputStreamReader(is));

		        String line = null;

		        String htmlString="";
		        
		        
		        while ((line = br.readLine()) != null) {

		            htmlString=htmlString+"\n"+line;
		        }
		        String filename="/home/user/Desktop/sggg.txt";
		        
		        try{
		        	@SuppressWarnings("resource")
					PrintWriter outputStream=new PrintWriter(filename);
		        	outputStream.println(htmlString);
		        }
		        catch(FileNotFoundException e){
		        	
		        	e.printStackTrace();
		        	
		        	
		        }
			
		        Document doc = Jsoup.parse(htmlString);
		
		
		
		int nooftbodys=doc.getElementsByTag("table").get(0).getElementsByTag("tbody").size();
		int nooftds	=doc.getElementsByTag("table").get(0).getElementsByTag("tbody").get(0).getElementsByTag("tr").get(0).getElementsByTag("td").size();
		for (int i=0;i<nooftbodys;i++){
		int nooftrs	=doc.getElementsByTag("table").get(0).getElementsByTag("tbody").get(i).getElementsByTag("tr").size();
		for(int j=0;j<nooftrs;j++){
			
		
			FuturesData object=new FuturesData();
		object.read_date=java.time.LocalDate.now();
		object.market="aus";
		object.region=region_name;
		object.contract_type=doc.getElementsByTag("table").get(0).getElementsByTag("thead").get(i).getElementsByTag("tr").get(0).getElementsByTag("td").get(0).text();
			
			for(int k=0;k<nooftds;k++){
				String str=doc.getElementsByTag("table").get(0).getElementsByTag("tbody").get(i).getElementsByTag("tr").get(j).getElementsByTag("td").get(k).text();
		if(k!=0){		
				boolean numeric=true;
			
				try{
					double num=Double.parseDouble(str);
				}
				catch(NumberFormatException e)
				{
					numeric =false;
				}
			if(numeric){
				;
			}
			else{
				str="0";
			}}
				switch(k)
				{
				case 0: object.contract_period=str;break;
				case 1: object.bid_size=str;break;
				case 2: object.bid=str;break;
				case 3: object.ask=str;break;
				case 4: object.ask_size=str;break;
				case 5: object.high=str;break;
				case 6: object.low=str;break;
				case 7: object.last=str;break;
				case 8: object.delta=str;break;
				case 9: object.vol=str;break;
				case 10: object.openint=str;break;
				case 11: object.openint_delta=str;break;
				case 12: object.settle=str;break;
				
				}}
			
			
			list.add(object);

		}
		
		
		}
		
		return list;
		
	}
	
	
	
	
public  static ArrayList<FuturesData> getNZdata(String region_name,String strurl ) throws InterruptedException, IOException 
	
	
	{int p=0;
		
		ArrayList<FuturesData> list1=new ArrayList<FuturesData>();

		
		
		URL url=new URL(strurl);
		URLConnection con = url.openConnection();
		java.io.InputStream is =con.getInputStream();
			
		        BufferedReader br = new BufferedReader(new InputStreamReader(is));

		        String line = null;

		        String htmlString="";
		   
		        while ((line = br.readLine()) != null) {

		            htmlString=htmlString+"\n"+line;
		        }
		       String filename="/home/user/Desktop/ts.txt";
		        
		        Document doc = Jsoup.parse(htmlString);
	
		int firstTables=doc.getElementsByAttributeValue("class","generator").get(0).getElementsByTag("table").size();
		int secondTables=doc.getElementsByAttributeValue("class","generator").get(1).getElementsByTag("table").size();
		int Tables;
		if(region_name=="Otahuhu"){
			p=0;
		}else if(region_name=="Benmore"){
			
			p=1;
		}
		
		
		if(p==0) {
		Tables=firstTables;
		}
		else
		{
			Tables=secondTables;
		}

		for (int i=0;i<Tables;i++) {
			int nooftrs	=doc.getElementsByAttributeValue("class","generator").get(0).getElementsByTag("table").get(i).getElementsByTag("tbody").get(0).getElementsByTag("tr").size();
		
			 int nooftds= doc.getElementsByAttributeValue("class","generator").get(0).getElementsByTag("table").get(i).getElementsByTag("tbody").get(0).getElementsByTag("tr").get(0).getElementsByTag("td").size();
			for(int j=0;j<nooftrs;j++){
			
			FuturesData object=new FuturesData();
		object.read_date=java.time.LocalDate.now();
		object.market="nz";
		object.region=region_name;
		object.contract_type=doc.getElementsByAttributeValue("class","generator").get(0).getElementsByTag("table").get(i).getElementsByTag("thead").get(0).getElementsByTag("tr").get(0).getElementsByTag("td").get(0).text();
			
			for(int k=0;k<nooftds;k++){
				String str=doc.getElementsByAttributeValue("class","generator").get(p).getElementsByTag("table").get(i).getElementsByTag("tbody").get(0).getElementsByTag("tr").get(j).getElementsByTag("td").get(k).text();
				
			
				if(k!=0){		
					boolean numeric=true;
				
					try{
						double num=Double.parseDouble(str);
					}
					catch(NumberFormatException e)
					{
						numeric =false;
					}
				if(numeric){
					;
				}
				else{
					str="0";
				}}
				
				
				switch(k)
				{
				case 0: object.contract_period=str;break;
				case 1: object.bid_size=str;break;
				case 2: object.bid=str;break;
				case 3: object.ask=str;break;
				case 4: object.ask_size=str;break;
				case 5: object.high=str;break;
				case 6: object.low=str;break;
				case 7: object.last=str;break;
				case 8: object.delta=str;break;
				case 9: object.vol=str;break;
				case 10: object.openint=str;break;
				case 11: object.openint_delta=str;break;
				case 12: object.settle=str;break;
				
				}
				}
			
			list1.add(object);

		}
		
		
		}
		
		return list1;
		
	}
	
	}

