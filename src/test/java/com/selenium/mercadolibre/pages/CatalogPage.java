package com.selenium.mercadolibre.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    By catalogProduct = By.className("ui-search-item__title");
    By buyButton = By.className("andes-button--loud");

    public void selectProduct() {
        click(catalogProduct);
    }

    public void buy() {
        click(buyButton);
    }



}
