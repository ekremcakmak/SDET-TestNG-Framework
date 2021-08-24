package com.TestNG.Tests;

import com.TestNG.Pages.POM_Pages;
import com.TestNG.Utilities.DriverSetup;
import com.TestNG.Utilities.ReadConfig;
import com.TestNG.Utilities.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AmazonSearch_POM {

    // Sample framework to SDET - using Java TestNG with OOP
    //
    // Objective: If a top-rated product listed on Amazon does not appear on the
    // first page of google search, competition can be achived with google advertisement.
    //
    // STEPS:
    // Search amazon: search product name on amazon.com - search box.
    // Order search results : Av. Costumer Review
    // Get first product details: name, rate count, store name, price and basic content
    // Navigate to google.com
    // Search product and seller name on google.com
    // Check results for store name
    // Report results

    POM_Pages pages = new POM_Pages();

    @Test
    public void SearchBox()
    {

        // Page Object Model - TestNG Sample
        System.out.println(ReadConfig.getConfig("framework") + " " + ReadConfig.getConfig("version") + " - POM version - SDET: " + ReadConfig.getConfig("SDET") + " " );
        System.out.println("Project Name: " + ReadConfig.getConfig("ProjectName") + " - Selected Browser: " + ReadConfig.getConfig("browser") );


        WebDriver driver = DriverSetup.getDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.amazon.com/");
        Tools.waitForPageToLoad(5000);

        String Product = ReadConfig.getConfig("product");

        pages.inputSearchBox.sendKeys(Product);
        pages.buttonSearch.click();

        Select SortReviewRank = new Select(driver.findElement(By.id("s-result-sort-select")));
        SortReviewRank.selectByValue("review-rank");

        Tools.sleep(2000);

        String TopRatedRate = pages.TopRatedRate.getText();
        String TopRatedPrice = pages.TopRatedPrice.getText();
        System.out.println(
                "# Product Name: " + Product +
                        " # Top Rated Product Price: " + TopRatedPrice +
                        " # Top Rated Product Rate :" + TopRatedRate
                        );

        pages.LinkTopRatedProduct.click();
        String StoreName = pages.TopRatedSellerStore.getText().replaceAll("Visit the ","").replaceAll("Store","");;
        System.out.println("Amazon Store Name :" + StoreName);
        System.out.println("Product Link :" + driver.getCurrentUrl() );

        driver.navigate().to("http://www.google.com");
        pages.inputSearchBoxGoogle.sendKeys(ReadConfig.getConfig("product") + " " + StoreName);
        pages.buttonSearchGoogle.submit();

        System.out.println("Google Search :" + Product + " " + StoreName);
        System.out.println("Google Search Link :" + driver.getCurrentUrl() );
        if(driver.getPageSource().contains("amazon"))
        {
            System.out.println("RESULT: Top seller of this product on amazon, at the first page on google search");
        }
        else
        {
            System.out.println("RESULT: Top seller of this product on amazon, no result found on google search");
        }

        Tools.sleep(2000);
        DriverSetup.closeDriver();

    }

}
