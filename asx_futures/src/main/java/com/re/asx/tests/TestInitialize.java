package com.re.asx.tests;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.re.asx.config.ConfigReader;
import com.re.asx.config.Settings;
import com.re.asx.utilities.LogUtil;


public class TestInitialize  {
	
	
	
	 @BeforeTest
	  
	  public void initialize(ITestContext test) throws Exception{	  
		 //Initialize Configuration Settings
		  ConfigReader.PopulateSettings();
		  
		//Logging 
		  Settings.Logs = new LogUtil();
		  Settings.Logs.CreateLogFile();
		  Settings.Logs.Write("DO language plpgsql $$");
		  Settings.Logs.Write("BEGIN");
	  
	 }

	
	@AfterTest
	  public void tearDown() throws Exception{
		 try{ 
			 Settings.Logs.Write("raise notice '% SUCCESS: Inserted records in asxtable',TO_CHAR(TIMEOFDAY()::timestamp,'YYYY-MM-DD HH24:MI:SS');");
			 Settings.Logs.Write("EXCEPTION WHEN others THEN");
			 Settings.Logs.Write("  raise warning '% ERROR: Inserting record in asxtable :: %',TO_CHAR(TIMEOFDAY()::timestamp,'YYYY-MM-DD HH24:MI:SS'), SQLERRM;");
			 Settings.Logs.Write("END;");
			 Settings.Logs.Write("$$");
		
		  
		 }catch(Exception e){
			
		 }
		 finally{
		
		 
	  }
	}
	 


}
