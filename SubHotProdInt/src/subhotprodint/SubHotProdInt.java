package subhotprodint;

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

public class SubHotProdInt {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        WebDriver driver= new FirefoxDriver();
        try{
                openHome(driver);
            }catch (InterruptedException | IOException h){
                System.out.println("Falha preenchimento Home");
                ScreenShotErro(driver);
                driver.navigate().refresh();
                openHome(driver);
            }
        try{
            masterPrice(driver);
            }catch (InterruptedException | IOException m){
            System.out.println("Erro na MasterPrice");
            ScreenShotErro(driver);
            driver.navigate().refresh();
            masterPrice(driver);
            }
        try{
        checkout(driver);
        }catch (InterruptedException | IOException k){
            System.out.println("Erro na checkout");
            ScreenShotErro(driver);
            driver.navigate().refresh();
            checkout(driver);
    }
        Thread.sleep(1000); 
        driver.quit();
    }
    //Metodo Home
    private static void openHome(WebDriver driver) throws InterruptedException, IOException {
    driver.get("http://www.submarinoviagens.com.br/index.aspx");
    driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/ul/li[3]")).click();//Hotel Int
    driver.findElement(By.id("txtLocation")).clear();
    driver.findElement(By.id("txtLocation")).sendKeys("miami");
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
        driver.findElement(By.id("btnSearch")).click();//Buscar
    }
    //Metodo MasterPrice
    private static void masterPrice(WebDriver driver) throws InterruptedException, IOException {
        ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[4]/div/div[1]/strong");//Aguarda aparecer "Hotéis encontrados para:"
        driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div[1]/div[2]/div[1]/div/ul/li[1]/div[2]/div/a[2]")).click();//Ver detalhes
        driver.getWindowHandles().stream().forEach((handle) -> {
            driver.switchTo().window(handle);
        });
        Thread.sleep(3000);
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/div/div[3]/div[1]/div/div[2]/div[2]/div[4]/a")).click();//Comprar
        }
    //Metodo CheckOut
    private static void checkout(WebDriver driver) throws InterruptedException, IOException {
       ExplicitWait(driver, "html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/h4");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[1]/label[1]/input")).sendKeys("Qamylla");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[1]/label[2]/input")).sendKeys("Munizz");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[2]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[2]/div/label[2]/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[2]/div[1]/label[1]/input")).sendKeys("Antonyu");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[2]/div[1]/label[2]/input")).sendKeys("Munizz");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[2]/div[2]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[2]/div[2]/div/label[1]/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[3]/div[1]/div[2]/label[1]/input")).sendKeys("4242 4242 4242 4242");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[3]/div[1]/div[2]/div/select[1]")).sendKeys("12");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[3]/div[1]/div[2]/div/select[2]")).sendKeys("23");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[3]/div[1]/div[2]/label[2]/input")).sendKeys("Qamylla Munizz");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[3]/div[2]/div[2]/label/input")).sendKeys("111");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[4]/div[2]/div[1]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[4]/div[2]/div[1]/div/label[1]/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[4]/div[2]/div[2]/label/input")).sendKeys("20826135935");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[4]/div[2]/div[2]/div/input[1]")).sendKeys("11");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[4]/div[2]/div[2]/div/input[2]")).sendKeys("67549878");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[4]/div[2]/div[3]/input")).sendKeys("06210100");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[4]/div[2]/div[4]/div[1]/div[2]/label[1]/input")).sendKeys("100");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div/div[2]/div[4]/div[2]/div[4]/div[2]/div[2]/label/input")).sendKeys("Altino");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[2]/div/div/label[1]/input")).sendKeys("timesite@cvc.com.br");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[2]/div/div/label[2]/input")).sendKeys("timesite@cvc.com.br");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[3]/label/input")).click();
            Thread.sleep(3000);//Aguarda preenchimento da rua.
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[5]/button")).click();//clica em comprar
        ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a");//aguarda reserva aparecer
        ScreenShot (driver);//print da tela
        reservasTxt(driver);
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
       String HostName = System.getProperty("user.name");
       FileUtils.copyFile(print, new File("C:\\Users\\"+HostName+"\\Pictures\\"+data+"-SubHotProdInt.jpg"));
   }
    public static void ScreenShotErro(WebDriver driver) throws IOException{
       System.out.println("automationsubprod.AutomationSubProd.ScreenShot()");
       File print =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       Calendar calendar = new GregorianCalendar();
       Date x = new Date();
       SimpleDateFormat formataData = new SimpleDateFormat("HHmmss");
       calendar.setTime(x);
       String data = formataData.format(x);
       String HostName = System.getProperty("user.name");
       FileUtils.copyFile(print, new File("C:\\Users\\"+HostName+"\\Pictures\\"+data+"-SubHotProdInt_Erro.jpg"));
   }
    public static void reservasTxt(WebDriver driver) throws IOException {
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat out = new SimpleDateFormat("HHmmss");
    Date date = new Date();
    String hora = out.format(date);
    calendar.setTime(date);
    System.out.println(out.format(calendar.getTime()));
    String HostName = System.getProperty("user.name");
        try (FileWriter arq = new FileWriter("C:\\Users\\"+HostName+"\\Documents\\"+hora+"-SubHotProdInt.txt")) {
            PrintWriter gravarArq = new PrintWriter(arq);
            WebElement elemento = driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a"));
            gravarArq.printf(elemento.getText());
        }
}
}
 

