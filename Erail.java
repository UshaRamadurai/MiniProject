package week4.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Erail {

	public static void main(String[] args) {

		// Importing the setup files for browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://erail.in/");

		// Enter the from station as ms
		WebElement src = driver.findElement(By.id("txtStationFrom"));
		src.clear();
		src.sendKeys("ms \n");

		// Enter the To station as mdu
		WebElement dest = driver.findElement(By.id("txtStationTo"));
		dest.clear();
		dest.sendKeys("mdu \n");

		// Uncheck the Sort By date
		driver.findElement(By.id("chkSelectDateOnly")).click();

		// Count the number of trains available
		List<WebElement> rowList = driver
				.findElements(By.xpath("//table[@class ='DataTable TrainList TrainListHeader']//tr"));
		System.out.println("Train count :" + rowList.size());

		// Get the names of all the train and store it in a list
		List<String> trainName = new ArrayList<String>();
		System.out.println("Train names");
		for (int i = 0; i < rowList.size(); i++) {
			WebElement elet = rowList.get(i);
			List<WebElement> columnList = elet.findElements(By.tagName("td"));
			String text = columnList.get(1).getText();
			trainName.add(text);
		}

		// Sort the train names
		Collections.sort(trainName);
		for (String string : trainName) {
			System.out.println(string);

		}

	}

}
