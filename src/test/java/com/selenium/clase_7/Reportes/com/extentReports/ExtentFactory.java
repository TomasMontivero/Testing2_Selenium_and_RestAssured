package com.selenium.clase_7.Reportes.com.extentReports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
     public static ExtentReports getInstance() {
          ExtentReports extent = new ExtentReports();
          extent.setSystemInfo("Selenium Version", "4.9.1");
          extent.setSystemInfo("OS", "Windows");
          extent.setSystemInfo("Navegador", "Chrome");
          extent.setSystemInfo("ENV", "QA");
          return extent;
     }
}