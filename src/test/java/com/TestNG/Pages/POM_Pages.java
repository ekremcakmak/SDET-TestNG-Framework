package com.TestNG.Pages;

import com.TestNG.Utilities.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class POM_Pages {

    public POM_Pages(){
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }


    @FindBy (xpath = "/html//input[@id='twotabsearchtextbox']")
    public WebElement inputSearchBox;

    @FindBy (xpath = "/html//input[@id='nav-search-submit-button']")
    public WebElement buttonSearch;

    @FindBy (xpath = "//div[@class='a-section a-spacing-medium']//div[@class='a-row a-size-small']//span[2]")
    public WebElement TopRatedRate;

    @FindBy (xpath = "//div[@class='a-section a-spacing-medium']//div[@class='a-row a-size-base a-color-base']//span")
    public WebElement TopRatedPrice;

    @FindBy (xpath = "//div[@class='a-section a-spacing-medium']//div[@class='a-row a-size-small']")
    public WebElement LinkTopRatedProduct;

    @FindBy (xpath = "//*[@id='bylineInfo']")
    public WebElement TopRatedSellerStore;

    @FindBy(how = How.NAME, using = "q")
    @CacheLookup
     public WebElement inputSearchBoxGoogle;

    @FindBy(how = How.NAME, using = "btnK")
    @CacheLookup
    public WebElement buttonSearchGoogle;

}
