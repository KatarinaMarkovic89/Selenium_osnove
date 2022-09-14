package p13_09_2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		2.Zadatak
//		Napisati program koji:
//		Maksimizuje prozor
//		Ucitava stranicu https://demoqa.com/login 
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/login");
//		Za username unosi itbootcamp. Xpath za trazivnje ovog elementa treba da bude preko id atributa.
		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("itbootcamp");
//		Za lozinku unosi ITBootcamp2021!  Xpath za trazenje ovog elementa treba da bude preko 
//		placeholder atributa.
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("ITBootcamp2021!");
//		Klikce na dugme Login. Xpath ovog elementa treba da bude tako da se prvo dohvati form 
//		element i da se od njega spusti do dugmeta
		driver.findElement(By.xpath("//form[@id='userForm']//button[@id='login']")).click();
//		Ceka 5sekundi
		Thread.sleep(5000);

//		Klikce na dugme Log out.Xpath ovog elementa treba da bude po tekstu elementa. 
		driver.findElement(By.xpath("//*[text()='Log out']")).click();
//		Zatvara pretrazivac
		driver.quit();

	}

}
