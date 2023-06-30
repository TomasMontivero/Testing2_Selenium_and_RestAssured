package com.selenium.mercadolibre.extentReports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {

    public static ExtentReports getInstance() {
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("Selenium Version", "4.1.4");
        extent.setSystemInfo("OS", "Windows");
        return extent;
    }

}
