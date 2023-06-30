package com.selenium.mercadolibre.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FilterPage extends BasePage{

    By memoryCapacityFilter = By.xpath("//*[contains(@class, 'ui-search-filter-container')]//*[contains(text(), \"iPhone\")]");

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    public void filterByMemory() {
        click(memoryCapacityFilter);
    }







}
