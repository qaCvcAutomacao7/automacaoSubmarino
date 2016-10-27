package subpacprodint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class SubPacProdInt {
    public static void main(String[] args) throws IOException, InterruptedException {
System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        WebDriver driver= new FirefoxDriver();
        //Home Pacote
        try{
                openHome(driver);
            }catch (InterruptedException | IOException h){
                System.out.println("Falha preenchimento Home");
                GrabScreenShotErro(driver);
                driver.navigate().refresh();
                openHome(driver);
            }
        //MasterPrice
        try{
            masterPrice(driver);
            }catch (InterruptedException | IOException m){
            GrabScreenShotErro(driver);
            System.out.println("Erro na MasterPrice");
            driver.navigate().refresh();
            masterPrice(driver);
            }
        //CheckOut
        try{
        checkout(driver);
        }catch (InterruptedException | IOException k){
            System.out.println("Erro na checkout");
            GrabScreenShotErro(driver);
            driver.navigate().refresh();
            checkout(driver);
    }
        Thread.sleep(1000);
        driver.quit();
    }
    private static void openHome(WebDriver driver) throws InterruptedException, IOException {
        driver.get("http://www.submarinoviagens.com.br/index.aspx"); 
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().deleteAllCookies();
        //Actions actions = new Actions(driver);
        //actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/ul/li[1]")).click();//Pacote Nac
        driver.findElement(By.id("cvcPackageRadio")).click();//Pacote
        driver.findElement(By.id("txtPackageCVCDeparture")).click();//Pacote
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/div/div[2]/label")).click();
        Thread.sleep(3000);
            WebElement origem = driver.findElement(By.id("txtPackageCVCDeparture"));
            origem.sendKeys("Sao Paulo");
        Thread.sleep(3000);
            origem.sendKeys(Keys.DOWN);
        //Thread.sleep(3000);
            origem.sendKeys(Keys.TAB);
        //Thread.sleep(1000);
            WebElement destino = driver.findElement(By.id("txtPackageCVCDestination"));
            destino.sendKeys("Buenos");
        //Thread.sleep(10000);
            destino.sendKeys(Keys.DOWN);
        //Thread.sleep(3000);
            destino.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        
        driver.findElement(By.id("searchPackageCVC")).click();//Comprar
        GrabScreenShot (driver);//print da tela
    }
   
   private static void masterPrice(WebDriver driver) throws InterruptedException, IOException {
       //Aguarda o preenchimento do Resultado da Busca 
       ExplicitWait(driver, "html/body/form/div/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div[5]/table/thead/tr/th[1]/strong");
        //Clicar "Ver Detalhes"
        driver.findElement(By.xpath("html/body/form/div/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div[5]/table/tbody/tr[1]/td[2]/div/a")).click();
        driver.getWindowHandles().stream().forEach((handle) -> {
            driver.switchTo().window(handle);
        });
        Thread.sleep(3000);
        String url = driver.getCurrentUrl();
        System.out.println(url);
        ExplicitWait(driver,"packageBuy");
        driver.findElement(By.id("packageBuy")).click();//Clica em Comprar
        GrabScreenShot (driver);//print da tela
        }
   private static void checkout(WebDriver driver) throws InterruptedException, IOException {
       ExplicitWait(driver, "html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/h4");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[1]/label[1]/input")).sendKeys("Qamyla");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[1]/label[2]/input")).sendKeys("Munizz");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[2]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[2]/div/label[2]/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[2]/div[1]/label[1]/input")).sendKeys("Antonyo");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[2]/div[1]/label[2]/input")).sendKeys("Munizz");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[2]/div[2]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[2]/div[2]/div/label[1]/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/label[1]/input")).sendKeys("4242 4242 4242 4242");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div/select[1]")).sendKeys("12");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div/select[2]")).sendKeys("23");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/label[2]/input")).sendKeys("Qamila Munizz");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[2]/div[2]/label/input")).sendKeys("111");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[1]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[1]/div/label[2]/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/label/input")).sendKeys("86385788733");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/div/input[1]")).sendKeys("11");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/div/input[2]")).sendKeys("67549878");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[3]/input")).sendKeys("06210100");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[4]/div[1]/div[2]/label[1]/input")).sendKeys("100");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[4]/div[2]/div[2]/label/input")).sendKeys("Altino");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[2]/div/div/label[1]/input")).sendKeys("timesite@cvc.com.br");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[2]/div/div/label[2]/input")).sendKeys("timesite@cvc.com.br");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[3]/label/input")).click();
            Thread.sleep(3000);//Aguarda preenchimento da rua.
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[5]/button")).click();//clica em comprar
        ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a");//aguarda reserva aparecer
        GrabScreenShot (driver);//print da tela e salva dentro da pasta Imagens
        reservasTxt(driver);//Grava reserva em um txt dentro da pasta Documentos
   }
    public static void reservasTxt(WebDriver driver) throws IOException {
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat out = new SimpleDateFormat("HHmmss");
    Date date = new Date();
    String hora = out.format(date);
    calendar.setTime(date);
    System.out.println(out.format(calendar.getTime()));
    String HostName = System.getProperty("user.name");
        try (FileWriter arq = new FileWriter("C:\\Users\\"+HostName+"\\Documents\\"+hora+"-SubPacProdInt.txt")) {
            PrintWriter gravarArq = new PrintWriter(arq);
            WebElement elemento = driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a"));
            gravarArq.printf(elemento.getText());
        }
  }
    public static void ExplicitWait (WebDriver driver, String text){
        (new WebDriverWait (driver, 60)).until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
    }
     public static void GrabScreenShot(WebDriver driver) throws IOException{
       System.out.println("automationsubprod.AutomationSubProd.ScreenShot()");
       File print =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       Calendar calendar = new GregorianCalendar();
       Date x = new Date();
       SimpleDateFormat formataData = new SimpleDateFormat("HHmmss");
       calendar.setTime(x);
       String data = formataData.format(x);
       String HostName = System.getProperty("user.name");
       FileUtils.copyFile(print, new File("C:\\Users\\"+HostName+"\\Pictures\\"+data+"-SubPacProdInt.jpg"));
   }
     public static void GrabScreenShotErro(WebDriver driver) throws IOException{
       System.out.println("automationsubprod.AutomationSubProd.ScreenShot()");
       File print =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       Calendar calendar = new GregorianCalendar();
       Date x = new Date();
       SimpleDateFormat formataData = new SimpleDateFormat("HHmmss");
       calendar.setTime(x);
       String data = formataData.format(x);
       String HostName = System.getProperty("user.name");
       FileUtils.copyFile(print, new File("C:\\Users\\"+HostName+"\\Pictures\\"+data+"-SubPacProdInt.jpg"));
   }
}

    
    

