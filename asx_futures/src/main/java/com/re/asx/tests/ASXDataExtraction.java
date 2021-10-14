package com.re.asx.tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.re.asx.common.DataExtraction;
import com.re.asx.common.DatabaseMethods;
import com.re.asx.common.FuturesData;
import com.re.asx.config.Settings;
import com.re.asx.utilities.Xls_Reader;


public class ASXDataExtraction extends TestInitialize {
  @Test
  public void dataExtraction() throws Exception {
	  ArrayList<FuturesData> fd=new ArrayList<FuturesData>();
	  ArrayList<FuturesData> FD=new ArrayList<FuturesData>();
	  Xls_Reader reader=new Xls_Reader(System.getProperty("user.dir") + Settings.TestDataSheet);
	  int noOfRows = reader.getRowCount("ASX");
	  HashMap < String, String > TestData = null;
	  for (int i = 1; i < noOfRows; i++) {
          TestData = loadTestData(String.valueOf(i), "ASX");
          
          
          if(TestData.get("IsRun").equals("Y")){
          if(TestData.get("Market").equals("AUS")){
        	  
        	  fd=DataExtraction.getAUdata(TestData.get("Region"), TestData.get("URL"));
          }
          else if(TestData.get("Market").equals("NZ")){
        	  
        	  fd=DataExtraction.getNZdata(TestData.get("Region"), TestData.get("URL"));
          }
          }
          else{
        	  continue;
          }
          
          
          FD.addAll(fd);
          
          
          
	  }
	  DatabaseMethods.writeIntoDB(FD);
	  
  }
  public static HashMap < String, String > loadTestData(String tcId, String sheetName) throws Exception{
      HashMap < String, String > testContext = new HashMap < String, String > ();
      Xls_Reader testData;
      try {

          String filepath = System.getProperty("user.dir") + Settings.TestDataSheet;
          testData = new Xls_Reader(filepath);

          int startIndex = testData.getCellRowNum(sheetName, "SI_NO", tcId);
          String colNames = "";
          String colValues = "";

          System.out.println(startIndex + " " + testData.getRowCount(sheetName));

          for (int j = 0; !(testData.getCellData(sheetName, j, 1).isEmpty()); j++) {
              colNames = colNames + "^" + testData.getCellData(sheetName, j, 1);
          }

          if (startIndex <= testData.getRowCount(sheetName)) {
              for (int j = 0; !(testData.getCellData(sheetName, j, startIndex).isEmpty()); j++) {
                  colValues = colValues + "^" + testData.getCellData(sheetName, j, startIndex);
              }
          }
          String[] Keys = colNames.split("\\^");
          String[] Values = colValues.split("\\^");

          for (int i = 1; i < Values.length; i++) {
              testContext.put(Keys[i].trim(), Values[i].trim());
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return testContext;
  }
  
  
  
}
