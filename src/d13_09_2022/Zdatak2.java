package d13_09_2022;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zdatak2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//Zadatak 
//Maksimizirati prozor
		driver.manage().window().maximize();
//Ucitati stranicu https://s.bootsnipp.com/iframe/WaXlr
		driver.navigate().to("https://s.bootsnipp.com/iframe/WaXlr");

//Dohvatite dugmice za rejting kao listu. XPath za trazenje treba 
//da bude preko id atributa i za ovo trebace vam contains u xpath-u
		
		List<WebElement> nizZvezdi = driver.findElements(By.xpath("//button[contains(@id,'rating-star')]"));
		
		
//Od korisnika ucitati broj (preko scannera) na koju zvezdicu je potrebno kliknuti (u rasponu od 1 do 5)
		Scanner s = new Scanner(System.in);
		System.out.print("Unesite broj zvezdica(1-5): ");
		int zvezda = s.nextInt();
		s.close();
//I izvrsite akciju klik na odgovarajuci element preko indeksa
//Na kraju programa ugasite pretrazivac.
		
		nizZvezdi.get(zvezda - 1).click();
		Thread.sleep(5000);
		driver.quit();

	}

}
