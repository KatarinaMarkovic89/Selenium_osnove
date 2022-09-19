package d19_09_2022;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BootstrapTableTests {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "https://s.bootsnipp.com";

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.navigate().to(baseUrl);
	}

	@Test(priority = 10)
	public void editRow() {
//		Podaci:
//			First Name: ime polaznika
//			Last Name: prezime polaznika
//			Middle Name: srednje ime polanzika
//			Koraci:
//			Ucitati stranu /iframe/K5yrx
		driver.navigate().to(baseUrl + "/iframe/K5yrx");
//			Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com

		String actualTitle = driver.getTitle();
		String expectedTitle = "Table with Edit and Update Data - Bootsnipp.com";
		Assert.assertEquals(actualTitle, expectedTitle, "Error: unexpected title");
//			Klik na Edit dugme prvog reda
		driver.findElement(By.xpath("//*[@data-uid = '1']")).click();
		;
//			Sacekati da dijalog za Editovanje bude vidljiv
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit")));
//			Popuniti formu podacima. 
//			Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
		driver.findElement(By.id("fn")).clear();
		driver.findElement(By.id("fn")).sendKeys("Katarina");
		driver.findElement(By.id("ln")).clear();
		driver.findElement(By.id("ln")).sendKeys("Markovic");
		driver.findElement(By.id("mn")).clear();
		driver.findElement(By.id("mn")).sendKeys("Olivera");
		// Klik na Update dugme
		driver.findElement(By.id("up")).click();
//			Sacekati da dijalog za Editovanje postane nevidljiv
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("edit")));
//			Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
		String actualFirst = driver.findElement(By.id("f1")).getText();
		String expectedFirst = "Katarina";
		Assert.assertEquals(actualFirst, expectedFirst, "Error: actual first name is not Katarina");
//			Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
		String actualLast = driver.findElement(By.id("l1")).getText();
		String expectedLast = "Markovic";
		Assert.assertEquals(actualLast, expectedLast, "Error: actual last name is not Markovic");
//			Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
		String actualMiddle = driver.findElement(By.id("m1")).getText();
		String expectedMiddle = "Olivera";
		Assert.assertEquals(actualMiddle, expectedMiddle, "Error: actual middle name is not Olivera");
//			Za sve validacije ispisati odgovarajuce poruke u slucaju greske

	}

	@Test(priority = 20)
	public void DeleteRow() {
//		Test #2: Delete Row
//		Podaci:
//		First Name: ime polaznika
//		Last Name: prezime polaznika
//		Middle Name: srednje ime polanzika
//		Koraci:
//		Ucitati stranu /iframe/K5yrx
		driver.navigate().to(baseUrl + "/iframe/K5yrx");
//		Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
		String actualTitle = driver.getTitle();
		String expectedTitle = "Table with Edit and Update Data - Bootsnipp.com";
		Assert.assertEquals(actualTitle, expectedTitle, "Error: unexpected title");
//		Klik na Delete dugme prvog reda
		driver.findElement(By.xpath("//*[@data-target = '#delete']")).click();
		;
//		Sacekati da dijalog za brisanje bude vidljiv
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete")));
//		Klik na Delete dugme iz dijaloga 
		driver.findElement(By.id("del")).click();
//		Sacekati da dijalog za Editovanje postane nevidljiv
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("delete")));
//		Verifikovati da je broj redova u tabeli za jedan manji

		Assert.assertFalse(driver.findElement(By.xpath("//tbody/tr")).isDisplayed(),
				"Error: number of rows is not decreased");

//		Za sve validacije ispisati odgovarajuce poruke u slucaju greske

	}

	@Test(priority = 30)
	public void TakeAScreenshot() throws IOException {
//
//Test #3: Take a Screenshot
//Koraci:
//Ucitati stranu  /iframe/K5yrx

		driver.navigate().to(baseUrl + "/iframe/K5yrx");
//Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
		String actualTitle = driver.getTitle();
		String expectedTitle = "Table with Edit and Update Data - Bootsnipp.com";
		Assert.assertEquals(actualTitle, expectedTitle, "Error: unexpected title");
//Kreirati screenshot stranice. Koristan link https://www.guru99.com/take-screenshot-selenium-webdriver.html
//Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: src/paket_za_domaci/nazivslike.png	

		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File DestFile = new File("img/screenshot1.png");
		com.google.common.io.Files.copy(SourceFile, DestFile);

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
