package com.re.asx.testrunner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class RunTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 TestNG tng = new TestNG();
         

         XmlSuite emePricingSuite = new XmlSuite();
         emePricingSuite.setName("EME Capture Pricing");

             XmlTest eme_test = new XmlTest();
             eme_test.setName("EME");
             eme_test.setSuite(emePricingSuite);

         List<XmlClass> eme_classes = new ArrayList<XmlClass>();
         eme_classes.add(new XmlClass("com.re.eme.tests.EMEPricing"));
         eme_test.setXmlClasses(eme_classes);
         
         emePricingSuite.addTest(eme_test);
            

         List<XmlSuite> suites = new ArrayList<XmlSuite>();
         suites.add(emePricingSuite);
         tng.setXmlSuites(suites);
         System.out.println ("Printing TestNG Suite Xml");
         System.out.println (((XmlSuite) emePricingSuite).toXml());
         
         tng.run();

	}

}
