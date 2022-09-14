package d13_09_2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak3 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		Zadatak
//		Napisati program koji vrsi dodavanje 5 reda u tabelu. 
//		Maksimizirati prozor
		driver.manage().window().maximize();
//		Ucitati stranicu https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php
		driver.navigate().to("https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php");
//		Dodati 5 redova sa istim podacima.Jedan red u jednoj iteraciji 
//		Klik na dugme Add New
//		Unesite name,departmant i phone (uvek iste vrednost)
//		Trazenje po name atributu
//		Kliknite na zeleno Add dugme. 
//		PAZNJA: Pogledajte strukturu stranice i videcete da se u svakom redu poslednje kolone javljaju 
//		dugmici edit, add, delete ali zbog prirode reda neki dugmici se vide a neki ne. 
//		Morate da dohvatite uvek Add dugme iz poslednjeg reda tabele. Mozete koristeci index iz petlje, 
//		a mozete koristeci i last() fukncionalnost za xpath. Koristan link last mehnizma
		for (int i = 0; i < 5; i++) {
			driver.findElement(By.xpath("//button[contains(@class, 'btn btn-info add-new')]")).click();
			driver.findElement(By.name("name")).sendKeys("Katarina");
			driver.findElement(By.name("department")).sendKeys("Markovic");
			driver.findElement(By.name("phone")).sendKeys("061225605");
			driver.findElement(
					By.xpath("//table[contains(@class, 'table table-bordered')]//tr[last()]/td[last()]/a[1]"))
					.click();
			;
			Thread.sleep(500);
		}
//		Cekanje od 0.5s
//		Na kraju programa ugasite pretrazivac.
		Thread.sleep(500);
		driver.quit();

	}

}
