package subhotprod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubHotProd {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        WebDriver driver= new FirefoxDriver();
        openHome(driver);
        masterPrice(driver);
    }
    //Metodo Preencher Motor de Busca Carros - Home
    private static void openHome(WebDriver driver) throws InterruptedException, IOException {
    driver.get("http://www.submarinoviagens.com.br/index.aspx");
    driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/ul/li[3]")).click();//passagens aereas
    driver.findElement(By.id("txtLocation")).clear();
    driver.findElement(By.id("txtLocation")).sendKeys("sao");
    Thread.sleep(3000);  
    
        Date x = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(x);
        c.add(Calendar.DATE, +30);
        c.add(Calendar.DAY_OF_MONTH, +30);
        c.add(Calendar.DAY_OF_MONTH, +28);
        x = c.getTime();
        //Pegar a data atual (volta)e acrescentar 90 dias e converter para String.
        Date y = new Date();
        c.setTime(y);
        c.add(Calendar.DATE, +30);
        c.add(Calendar.DAY_OF_MONTH, +30);
        c.add(Calendar.DAY_OF_MONTH, +30);
        y = c.getTime();
        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
        String ida = formataData.format(x);
        String volta = formataData.format(y); 
        driver.findElement(By.id("txtCheckIn")).sendKeys(ida);//Entrada
        driver.findElement(By.id("txtCheckOut")).sendKeys(volta);//Saida
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div/div/a")).click();
        
    }
    //Metodo MAsterPrice
    private static void masterPrice(WebDriver driver) throws InterruptedException, IOException {
        ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[4]/div/div[1]/strong");
        driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div[1]/div[2]/div[1]/div/ul/li[1]/div[2]/div/a[2]")).click();//Ver detalhes
        driver.close();
        //Thread.sleep(7000);
        driver.getCurrentUrl();
        System.out.println("Erro url ");
        Thread.sleep(3000);
        //ExplicitWait(driver, "html/body/form/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/div/div[3]/div[1]/div/div[2]/div[2]/div[4]/a");
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/div/div[3]/div[1]/div/div[2]/div[2]/div[4]/a")).click();//Comprar
        /*ExplicitWait(driver, "html/body/div[12]/div/div/div[1]");
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[12]/div/div/div[1]/div[2]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[12]/div/div/div[2]/div[2]/button")).click();//Confirmar
        ScreenShot (driver);//print da tela*/
        }
    public static void ExplicitWait (WebDriver driver, String text){
        (new WebDriverWait (driver, 60)).until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
    }
    public static void ScreenShot(WebDriver driver) throws IOException{
       System.out.println("automationsubprod.AutomationSubProd.ScreenShot()");
       File print =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       Calendar calendar = new GregorianCalendar();
       Date x = new Date();
       SimpleDateFormat formataData = new SimpleDateFormat("HHmmss");
       calendar.setTime(x);
       String data = formataData.format(x);
       FileUtils.copyFile(print, new File("C:\\Users\\subt000079\\Pictures\\"+data+"-Print_Auto.jpg"));
       
   }
    public static void reservasTxt(WebDriver driver) throws IOException {
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat out = new SimpleDateFormat("HHmmss");
    Date date = new Date();
    String hora = out.format(date);
    calendar.setTime(date);
    System.out.println(out.format(calendar.getTime()));
        try (FileWriter arq = new FileWriter("C:\\Users\\subt000079\\Documents\\Reservas\\"+hora+"-reservasCarNacSubProd.txt")) {
            PrintWriter gravarArq = new PrintWriter(arq);
            WebElement elemento = driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a"));
            gravarArq.printf(elemento.getText());
        }
}
}
