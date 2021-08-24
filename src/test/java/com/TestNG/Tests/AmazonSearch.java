package com.TestNG.Tests;

import com.TestNG.Utilities.DriverSetup;
import com.TestNG.Utilities.ReadConfig;
import com.TestNG.Utilities.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonSearch {

    // Sample framework to SDET - using Java TestNG
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

    @Test
    public void SearchBox()
    {
        System.out.println(ReadConfig.getConfig("framework") + " " + ReadConfig.getConfig("version") + " - SDET: " + ReadConfig.getConfig("SDET") + " " );
        System.out.println("Project Name: " + ReadConfig.getConfig("ProjectName") + " - Selected Browser: " + ReadConfig.getConfig("browser") + " "  );

        WebDriver driver = DriverSetup.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.com/");


        Tools.sleep(2000);

//        Locators Helper
//        driver.findElement(new By.id("ID"));
//        driver.findElement(new By.name("Name"));
//        driver.findElement(new By.className("ClassName"));
//        driver.findElement(new By.tagName("Name"));
//        driver.findElement(new By.partialLinkText("Text"));
//        driver.findElement(new By.xPath("XPath"));
//        driver.findElement(new By.cssSelector(".CssName"));
//        driver.findElement(new By.cssSelector("#CssID"));
//        cssSelector List: https://www.w3schools.com/cssref/css_selectors.asp



        String Product = ReadConfig.getConfig("product");

        WebElement AmazonSearchBox = driver.findElement(By.id("twotabsearchtextbox"));
        AmazonSearchBox.sendKeys(Product);

        driver.findElement(By.id("nav-search-submit-button")).submit();
        Tools.sleep(2000);

        Select SortReviewRank = new Select(driver.findElement(By.id("s-result-sort-select")));
        SortReviewRank.selectByValue("review-rank");

        Tools.sleep(2000);

        String TopRatedRate =  driver.findElement(By.xpath("//div[@class='a-section a-spacing-medium']//div[@class='a-row a-size-small']//span[2]")).getText();
        String TopRatedPrice =  driver.findElement(By.xpath("//div[@class='a-section a-spacing-medium']//div[@class='a-row a-size-base a-color-base']//span")).getText();

         List <WebElement> SearchList = driver.findElements(By.cssSelector("a-section a-spacing-medium"));
        int countOfProductAtFirstPage = SearchList.size();

        String amazonTitle =  driver.getTitle();
        String amazonUrl =  driver.getCurrentUrl();

        System.out.println(
                "# Product Name: " + Product +
                " # Top Rated Product Price: " + TopRatedPrice +
                " # Top Rated Product Rate :" + TopRatedRate +
                " # Product count at first page: " + countOfProductAtFirstPage +
                " # Amazon List : " + amazonUrl);

        driver.findElement(By.xpath("//div[@class='a-section a-spacing-medium']//div[@class='a-row a-size-small']")).click();

        String TopRatedSellerStore =  driver.findElement(By.xpath("//*[@id='bylineInfo']")).getText().replaceAll("Visit the ","").replaceAll("Store","");
        System.out.println("Amazon Store Name :" + TopRatedSellerStore );
        System.out.println("Product Link :" + driver.getCurrentUrl() );

        // Navigation Helper
        // driver.findElement(By.xpath("")).Submit();
        // driver.findElement(By.xpath("")).Click();
        // driver.navigate().to("url")
        // driver.navigate().back("url")
        // driver.navigate().forward("url")
        // driver.navigate().refresh()


        driver.navigate().to("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys(Product + " " + TopRatedSellerStore);
        driver.findElement(By.name("btnK")).submit();

        // Thread alternative:
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        System.out.println("Google Search :" + Product + " " + TopRatedSellerStore);
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
