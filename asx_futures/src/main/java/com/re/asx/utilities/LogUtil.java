package com.re.asx.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class LogUtil {

    //File format for the log name
    ZonedDateTime date = ZonedDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
    String fileNameFormat = date.format(formatter);

    private BufferedWriter bufferedWriter = null;


    //Create Log file
    public void CreateLogFile() throws IOException {
        try {
            File dir = new File("/home/"+System.getProperty("user.name")+"/ASX/");
            if (!dir.exists())
                dir.mkdir();
            //Create file
            File logFile = new File(dir + "/ASXFutures" + fileNameFormat + ".sql");

            FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);

        } catch (Exception e) {
        	
        	System.out.println(e.getMessage());

        }
    }


    //Write message within the log
    public void Write(String message) {
        try {
        	bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();

        }
    }

}
