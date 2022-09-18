package d16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		Zadatak
//		Napisati program koji ima:
//		Podesava:
//		implicitno cekanje za trazenje elemenata od 10s
//		implicitno cekanje za ucitavanje stranice od 10s
//		eksplicitno cekanje podeseno na 10s
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//		Podaci:
//		Potrebno je u projektu ukljuciti 4 slike.
//		Imenovanje slika neka bude po pravilu pozicija_ime_prezime_polaznika.ekstenzija
//		Npr: front_milan_jovanovic.jpg, left_milan_jovanovic.jpg …
//		Koraci:
//		Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
		driver.navigate().to("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
//		Maksimizuje prozor
		driver.manage().window().maximize();
		ArrayList<File> slike = new ArrayList<File>();
		slike.add(new File("img/front_katarina_markovic.jpg"));
		slike.add(new File("img/left_katarina_markovic.jpg"));
		slike.add(new File("img/back_katarina_markovic.jpg"));
		slike.add(new File("img/right_katarina_markovic.jpg"));

		for (int i = 0; i < slike.size(); i++) {
//			Selektuje Image - Front klikom na tu karticu u dnu ekrana
			driver.findElement(By.xpath("//img[@alt='image " + (i + 1) + "']")).click();
//			Klik na add photo iz levog navigacionog menia
			driver.findElement(By.xpath("//img[@alt= '+ Add photo']")).click();
//			Upload slike. Upload preko File objekta koristeci getAbsolutePath
			driver.findElement(By.id("imageUpload")).sendKeys(slike.get(i).getAbsolutePath());
//			Sacekati da broj prikazanih slika u donjem uglu navigacije bude za 1 veca
			wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//img[@loading = 'lazy']"), i + 1));
//			Kliknuti na poslednje dodatu sliku kako bi bila izabrana za postavljanje
			driver.findElement(By.xpath("//img[@loading = 'lazy']")).click();
//			Ceka da dijalog bude vidljiv
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Use One Side Only']")));
//			Klik na Use One Side Only dugme
			driver.findElement(By.xpath("//button[text()='Use One Side Only']")).click();

//			Ceka da se pojavi dijalog sa slikom
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid ='container']")));
//			Klik na Done
			driver.findElement(By.xpath("//button[text()= 'Done']")).click();
		}

//		Ponoviti proces za Left, Right i Back
//		Zatim iz desnog gornjeg ugla izabrati random jedan od ponudjenih konfeta
		Random r = new Random();
		driver.findElement(By.name("" + r.nextInt(5) + "")).click();
//		Kliknuti na Add To Cart dugme
		driver.findElement(By.xpath("//button[text() = 'Add to cart ']")).click();
//		Verifikovati postojanje greske Oops! It looks like you`ve not added an text to this field, please add one before continuing.
//		Xpath: //*[@action='error']
		boolean exists = true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@action = 'error']")));

		} catch (Exception e) {
			exists = false;
		}

		if (exists) {
			System.out.println("Greska se pojavila.");
		} else {
			System.out.println("Greska se nije pojavila.");
		}
//		Zatvorite pretrazivac
		Thread.sleep(2000);
		driver.quit();
	}

}
