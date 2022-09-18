package d15_09_2022;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Zadatak3 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		3.Zadatak (Za vezbanje)
//		Napisati program koji 
//		Ucitava https://seeds.sproutsocial.com/components/loader-button/
		driver.navigate().to("https://seeds.sproutsocial.com/components/loader-button/");
//		Odskrola do Loader buttons are used to display a loading indicator inside of a button. paragrafa. Koristan link
		new Actions (driver)
		.scrollToElement(driver.findElement(By.xpath("//*[@id='content']/div/p")))
		.perform();
		//DA MOGU DA VIDIM DUGME
		new Actions (driver)
		.scrollToElement(driver.findElement(By.xpath("//button[contains(@class, 'dzjEcK')]")))
		.perform();
				
//		Klikce ne dugme 
		driver.findElement(By.xpath("//button[contains(@class, 'dzjEcK')]")).click();
//		Ceka da dugme zavrsi sa loadingom 
		String load = driver.findElement(By.xpath("//button[contains(@class, 'dzjEcK')]"))
				.getAttribute("data-qa-button-isloading");
		//Ispisati poruku na ekranu
		if(load.equals("false")) {
			System.out.println("Ne ucitava se.");
		} else {
			System.out.println("Ucitava se.");
		}
//		Zatvoriti pretrazivac
//		HINT: Koristite data-qa-button-isloading  atribut elementa za cekanje odredjenog stanja tog elementa
		driver.quit();

	}

}
