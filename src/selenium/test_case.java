package selenium;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.WebElement;
import org.testng.Assert;





public class test_case {
	

		static WebDriver driver= new ChromeDriver();
		static Actions action = new Actions(driver);
		static LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);

	public static void anasayfa() {
		
		try {
			
			driver.get("https://www.n11.com/");
			String sonuc = driver.getTitle();
			String sonuc1 = "n11.com - Al��veri�in U�urlu Adresi";
			Assert.assertEquals(sonuc1, sonuc);
			System.out.println("�uan burdas�n: "+driver.getTitle());
			
		}
		catch (AssertionError a){
			System.out.println("Anasayfa a��lamad�");
			
			driver.quit();
		}
		}
	public static void login() throws InterruptedException {
		driver.findElement(By.className("btnSignIn")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("email")).sendKeys("buseaalp@gmail.com");
		driver.findElement(By.id("password")).sendKeys("1996bu");
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(5000);
		
		try {
			driver.findElement(By.cssSelector("#header > div > div > div.hMedMenu > div.customMenu > div.myAccountHolder.customMenuItem.hasMenu.withLocalization > div.myAccount > a.menuLink.user"));
			System.out.println("Ba�ar�yla '"+driver.findElement(By.cssSelector("#header > div > div > div.hMedMenu > div.customMenu > div.myAccountHolder.customMenuItem.hasMenu.withLocalization > div.myAccount > a.menuLink.user")).getText() +"' hesab�na giri� yap�ld�");
			
		} catch (NoSuchElementException e ) {
			System.out.println("Giri� Yap�lamad�");
			driver.quit();
		}
	}
	public static void search() throws InterruptedException {
		try {
		driver.findElement(By.id("searchData")).sendKeys("Iphone");
		driver.findElement(By.className("searchBtn")).click();
		Thread.sleep(2000);
		String sonuc = driver.getTitle();
		String sonuc1 ="Iphone - n11.com";
		Assert.assertEquals(sonuc1, sonuc);
		System.out.println("Arat�lan kelime "+driver.getTitle().split("-")[0]);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#contentListing > div > div > div.productArea > div.pagination > a:nth-child(2)")).click();;
		
		}
		catch(AssertionError a) {
			System.out.println("Arama yap�lamad�");
		}
		
	}
	public static void favorite() throws InterruptedException {
		List<WebElement> a =driver.findElement(By.cssSelector("#contentListing > div > div > div.productArea > section.group.listingGroup.resultListGroup")).findElements(By.className("followBtn"));
		a.get(2).click();

		
	}
	public static void favoritecheck() throws InterruptedException {
		
       WebElement element = driver.findElement(By.linkText("Hesab�m"));
        action.moveToElement(element).build().perform();
        driver.findElement(By.linkText("�stek Listem / Favorilerim")).click();
        Thread.sleep(5000);
		try {
			String sonuc=driver.getTitle();
			String sonuc1="�stek Listelerim - n11.com";
			Assert.assertEquals(sonuc1, sonuc);
			System.out.println("Favorilerim a��ld�");
		}
		catch (AssertionError a) {
			System.out.println("Favorilerim a��lamad�");
		}
	}
	public static void favoritedelete() throws InterruptedException {
		driver.findElement(By.className("listItemTitle")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("deleteProFromFavorites")).click();
		System.out.println("Silinen �r�n: "+driver.findElement(By.className("productName")).getText());
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("body > div.lightBox > div > div > span")).click();
		
		try {
			driver.findElement(By.className("column wishListColumn "));
			}
			catch (NoSuchElementException e) {
				System.out.println("�r�n Ba�ar�yla Silindi");
				cikis();
				
			}
	}
	public static void cikis() throws InterruptedException {
			driver.get("https://www.n11.com/");
	        action.moveToElement(driver.findElement(By.linkText("Hesab�m"))).moveToElement(driver.findElement(By.className("logoutBtn"))).click().build().perform();
	        Thread.sleep(2000);
	        
	}
	public static void log() throws FileNotFoundException {
        File dosya = new File("log.txt");
        PrintWriter yaz = new PrintWriter(dosya);
        Logs logs = driver.manage().logs();
        LogEntries logEntries = logs.get(LogType.BROWSER);
        for(LogEntry logEntry :logEntries)
        {
             if (logEntry.getMessage().toLowerCase().contains("error")) {
                yaz.println(new Date(logEntry.getTimestamp())+" Hata Mesaji: "+logEntry.getMessage());
            } else if (logEntry.getMessage().toLowerCase().contains("warning")){
               yaz.println(new Date(logEntry.getTimestamp())+" Uyar� Mesaji: "+logEntry.getMessage());
            }else{
                yaz.println(new Date(logEntry.getTimestamp())+" Bilgi Mesaj�: "+logEntry.getMessage());
            }
        }
        yaz.close();
        driver.quit();
        System.out.println("Log dosyas� kaydedildi " +dosya.getAbsolutePath());
	}
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		anasayfa();
		login();
		search();
		favorite();
		favoritecheck();
		favoritedelete();
		log();
	}

}
