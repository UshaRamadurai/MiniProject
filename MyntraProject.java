package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyntraProject {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Importing the driver files
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		//maximizing the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Open https://www.myntra.com/
		driver.get("https://www.myntra.com/");

		//Mouse hover on MeN 
		WebElement men = driver.findElement(By.xpath("//a[text()='Men']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();

		//Click Jackets 
		driver.findElement(By.xpath("//a[text()='Jackets']")).click();
		
		//Find the total count of item
		WebElement count = driver.findElement(By.xpath("//span[@class='title-count']"));
		String mainCount = count.getText();
		mainCount = mainCount.replaceAll("\\D", "");
		int count1 = Integer.parseInt(mainCount);
		System.out.println("Total count of items :" + count1);

		//Get the numbers from Category
		String text1 = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String text2 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();

		//Converting the count from String to int
		text1 = text1.replaceAll("\\D", "");
		int count2 = Integer.parseInt(text1);
		
		text2 = text2.replaceAll("\\D", "");
		int count3 = Integer.parseInt(text2);
		
		//Validate the sum of categories count matches
		if (count1 == (count2 + count3))
			System.out.println("The total count equals the category counts!!!");
		else
			System.out.println("The total count not equals the category counts!!!");

		//Check jackets
		driver.findElement(By.xpath("//div[@class='common-checkboxIndicator']")).click();
		
		//Click + More option under BRAND
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();

		//Type Duke and click checkbox
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke");
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[11]")).click();

		//Close the pop-up x
		driver.findElement(By.xpath("//span[@class ='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();

		//Confirm all the Coats are of brand Duke
		int dukeTrue = 0, dukeFalse = 0;
		Thread.sleep(5000);
		
		//Get all the brands in list
		List<WebElement> brandName = driver.findElements(By.xpath("//h3[@class='product-brand']"));

		for (WebElement eachName : brandName) {
			if ((eachName.getText()).equals("Duke"))
				dukeTrue++;
			else
				dukeFalse++;
		}

		//Comparison of each item to "Duke"
		if (dukeTrue == brandName.size())
			System.out.println("All the " + dukeTrue + " items are of Duke brand");
		else
			System.out.println("The number of items not of Duke are :" + dukeFalse);

		//Mouse Hover on Sort by 
		WebElement sort = driver.findElement(By.className("sort-sortBy"));
		builder.moveToElement(sort).perform();

		//Click on Better Discount
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();

		//Find the price of first displayed item
		String firstPrice = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("The price of first displayed item is :" + firstPrice);

		//Click on the first product
		driver.findElement(By.xpath("//img[@class='img-responsive']")).click();
		
		//Move to new tab opened
		Set<String> winHandleSet = driver.getWindowHandles();
		List<String> winHandleList = new ArrayList<String>(winHandleSet); 
		driver.switchTo().window(winHandleList.get(1));
		
		//Take a screen shot
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./snaps/Jacket.png");
		FileUtils.copyFile(srcFile, destFile);

		//Click on WishList
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		
		//Close Browser
		driver.quit();

	}

}
