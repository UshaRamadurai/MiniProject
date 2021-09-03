package week4.day2.project;
//Goal : Code to automate Nykaa website

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		
		// Importing the driver files
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// maximizing the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Open https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");

		// Mouse hover on Brands
		WebElement brands = driver.findElement(By.xpath("//div[@id='brand_arrowUp']/.."));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();

		// Mouse hover on Popular
		WebElement popular = driver.findElement(By.xpath("//a[@class='brandHeadingbox current_active']"));
		builder.moveToElement(popular).perform();

		// Click L'Oreal Paris
		driver.findElement(By.xpath("(//div[@id='brandCont_Popular']//li)[5]")).click();

		// Move on to newly opened window
		Set<String> winHandleSet = driver.getWindowHandles();
		List<String> winHandleList = new ArrayList<String>(winHandleSet);
		driver.switchTo().window(winHandleList.get(1));

		// check the title contains L'Oreal Paris
		String title = driver.getTitle();
		if (title.startsWith("L'Oreal Paris"))
			System.out.println("You are in L'Oreal Paris Page");
		else
			System.out.println("Wrong page");

		// Click Sort By
		driver.findElement(By.xpath("//span[text()='Sort By : ']")).click();

		// Click Customer top rated
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();

		Thread.sleep(3000);

		// Click Category
		driver.findElement(By.xpath("//div[text()='Category']")).click();

		// Click Hair
		driver.findElement(By.xpath("//span[text() ='Hair']")).click();

		// Click Hair Care
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();

		// Click Shampoo
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();

		// check whether the Filter is applied with Shampoo
		String shampoo = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
		if (shampoo.startsWith("Shampoo"))
			System.out.println("Shampoo is present in filter");
		else
			System.out.println("Shampoo not in filter");

		// Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//span[contains(text(),' Paris Colour Protect Shampoo')]")).click();

		// Move on to newly opened window
		Set<String> winHandleSet1 = driver.getWindowHandles();
		List<String> winHandleList1 = new ArrayList<String>(winHandleSet1);
		driver.switchTo().window(winHandleList1.get(2));

		// Print the MRP of the product
		String price = driver.findElement(By.xpath("(//div[@class='price-info']/span)[2]/span")).getText();
		System.out.println("The Price of the product is :" + price);

		// Click on ADD to BAG
		// driver.findElement(By.xpath("//span[@class='mkr-New-Shopping-Bag
		// font-mkr']")).click();
		driver.findElement(By.xpath(
				"//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  ']"))
				.click();

		Thread.sleep(5000);
		// Go to Shopping Bag
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();

		// Print the Grand Total amount
		String grandTotal = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		System.out.println("The Grand total is :" + grandTotal);

		// Click the x(close) in below notification
		WebElement frame = driver.findElement(By.id("__ta_notif_frame_2"));
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//div[@class='close']")).click();

		// Click Proceed
		driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']")).click();

		// Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();

		// Verifying the grand total
		String finalGrandTotal = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
		System.out.println("The final Grand total is :" + finalGrandTotal);

		if (finalGrandTotal.equals(grandTotal))
			System.out.println("The grand total is same");
		else
			System.out.println("The grand total is different");

		//Close the window
		driver.quit();
	}

}
