package com.vehere.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverClass {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\2601061199\\eclipse-workspace\\RestAPI\\test\\chromedriver.exe");
		WebDriver driver;
		driver = new ChromeDriver();
        
        //Using get() method to open a webpage
        driver.get("https://192.168.2.118:5601/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        
        driver.findElement(By.id("username")).sendKeys("vnfsadmin");
        Thread.sleep(5000);
        driver.findElement(By.id("password")).sendKeys("vnfsadmin");
        Thread.sleep(5000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);
        
        //Closing the browser
        driver.quit();
		
	}

}
