package com.re.asx.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigReader {

   
    public static  void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
       
        
    }

    private void ReadProperty() throws IOException {
        //Create Property Object
        Properties p = new Properties();
        //Load the Property file available in same package
        //p.load(getClass().getResourceAsStream("GlobalConfig.properties"));
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path;
        File file = new File(classLoader.getResource("Config/GlobalConfig.properties").getFile());
        if(!(file.exists() && !file.isDirectory())) { 
        	path = System.getProperty("user.dir") + "/classes/Config/GlobalConfig.properties";
    	}
        else
        	path=file.toString();
        
        try{
        	p.load(new FileInputStream(path));
        }catch(Exception e){}
        
                       
               
        Settings.AUT = p.getProperty("AUT");
   
        //TestData File Path
        Settings.TestDataSheet=p.getProperty("TestDataSheet");
          
        //DB connection
        Settings.BackDbUrl = p.getProperty("BackDbUrl");
        Settings.BackDbName = p.getProperty("BackDbName");
        Settings.BackDbUsername = p.getProperty("BackDbUsername");
        Settings.BackDbPassword = p.getProperty("BackDbPassword");
       
               
    }

}
