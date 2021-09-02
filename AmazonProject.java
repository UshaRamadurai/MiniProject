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

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonProject {

	public static void main(String[] args) throws IOException, InterruptedException {

		// Importing the driver files
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// maximizing the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Load the URL https://www.amazon.in/
		driver.get("https://www.amazon.in/");

		// search as oneplus 9 pro
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro \n");

		// Get the price of the first product
		String price = driver.findElement(By.className("a-price-whole")).getText();
		System.out.println("The price of first item is Rs." + price);
		price = price.replaceAll("\\D", "");
		int count1 = Integer.parseInt(price);

		// Print the number of customer ratings for the first displayed product
		String totalReviews = driver.findElement(By.xpath("//span[@class='a-size-base']")).getText();
		System.out.println("The number of Total ratings on first item is " + totalReviews);

		Thread.sleep(5000);

		// Click on the stars
		WebElement star = driver.findElement(By.xpath("//i[@class='a-icon a-icon-popover']"));
		star.click();
		Thread.sleep(3000);

		// Get the percentage of ratings for the 5 star
		String fiveStar = driver.findElement(By.xpath("//a[@class='a-link-normal 5star']")).getAttribute("title");
		System.out.println("The percentage of Five Star rating on first item is " + fiveStar);

		// Click the first text link of the first image
		driver.findElement(By.xpath("//a[@class='a-link-normal a-text-normal']")).click();

		// Move to new tab opened
		Set<String> winHandleSet = driver.getWindowHandles();
		List<String> winHandleList = new ArrayList<String>(winHandleSet);
		driver.switchTo().window(winHandleList.get(1));

		// Take a screen shot of the product displayed
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./snaps/mobile.png");
		FileUtils.copyFile(srcFile, destFile);

		// Click 'Add to Cart' button
		driver.findElement(By.id("add-to-cart-button")).click();

		Thread.sleep(5000);

		// Get the cart subtotal and verify if it is correct.
		String subTotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		subTotal = subTotal.replaceAll("[^1-9]", "");
		int count2 = Integer.parseInt(subTotal);
		if (count1 == count2)
			System.out.println("Verification Pass!! both the rates are same");
		else
			System.out.println("Verification Fail!! both the rates are different");

		// Close the windows
		driver.quit();

	}

}
