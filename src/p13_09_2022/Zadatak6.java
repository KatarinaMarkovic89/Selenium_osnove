package p13_09_2022;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.netty.handler.codec.spdy.SpdyWindowUpdateFrame;

public class Zadatak6 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//6.Zadatak
//Napisti program koji:
//Ucitava stranicu https://s.bootsnipp.com/iframe/z80en
		driver.navigate().to("https://s.bootsnipp.com/iframe/z80en");
//Hvata sve elemente iz tabele i stampa tekst svakog elementa. 
//Kako da od nekog elementa procitamo tekst imate na sledecem linku 
//Ceka 1s
		List<WebElement> row = driver.findElement(By.xpath("//*[@id='lorem']")).findElements(By.tagName("tr"));

		for (int i = 0; i < row.size(); i++) {
			List<WebElement> cell = row.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < cell.size(); j++) {
				System.out.print(cell.get(j).getText() + " ");
				Thread.sleep(1000);
			}
			System.out.println();
			Thread.sleep(1000);
		}
//Stampa tekst svakog elementa 
//Ceka 5s
//Zatvara pretrazivac
//
//Stampa treba da bude kao u primeru:
//John	Doe	john@example.com
//Mary	Moe	mary@example.com
//July	Dooley	july@example.com

		driver.quit();
	}

}
