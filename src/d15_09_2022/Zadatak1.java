package d15_09_2022;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		1.Zad
//		Napisati program koji:
//		Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
		driver.navigate().to("https://s.bootsnipp.com/iframe/Dq2X");
		List<WebElement> number = driver.findElements(By.className("close"));
//		Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element 
//		obrisao sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//		POMOC: Brisite elemente odozdo.
		boolean exists = true;
		for (int i = number.size() - 1; i >= 0; i--) {
			number.get(i).click();
			try {
				number.get(i).click();
			} catch (Exception e) {
				exists = false;
			}

			if (!exists) {
				System.out.println("Element je obrisan");
			} else {
				System.out.println("Element nije obrisan");
			}

		}
		Thread.sleep(2000);
		driver.quit();

	}

}
