package subairprodint;
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

public class SubAirProdInt {
    public static void main(String[] args) throws InterruptedException, IOException {
System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        WebDriver driver= new FirefoxDriver();
        //Home Aereo
        try{
                openHome(driver);
            }catch (InterruptedException | IOException h){
                System.out.println("Falha preenchimento Home");
                driver.navigate().refresh();
                openHomeAlternative(driver);
            }
        //MasterPrice
        try{
            masterPrice(driver);
            }catch (InterruptedException | IOException m){
            System.out.println("Erro na MasterPrice");
            driver.navigate().refresh();
            masterPrice(driver);
            }
        //CheckOut
        try{
        checkout(driver);
        }catch (InterruptedException | IOException k){
            System.out.println("Erro na checkout");
            driver.navigate().refresh();
            checkout(driver);
    }
}
   private static void openHome(WebDriver driver) throws InterruptedException, IOException {
        driver.get("http://www.submarinoviagens.com.br/index.aspx"); 
        driver.findElement(By.xpath(".//*[@id='searchEngine']/ul/li[2]")).click();//passagens aereas
        driver.findElement(By.xpath(".//*[@id='txtOrigin']")).clear();
        driver.findElement(By.xpath(".//*[@id='txtOrigin']")).sendKeys("sao");
                Thread.sleep(3000);      
        driver.findElement(By.id("txtDestination")).clear();
        driver.findElement(By.id("txtDestination")).sendKeys("bue");    
                Thread.sleep(4000);
          //Pega a data atual (ida) e acrescentar 88 dias e converter para String.
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
        driver.findElement(By.id("txtOutboundPackage")).sendKeys(ida);//data ida
        driver.findElement(By.id("txtInboundPackage")).sendKeys(volta);//data volta
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/select")).click();
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div/div[2]/a")).click();
        GrabScreenShot (driver);//print da tela
    }
   private static void openHomeAlternative(WebDriver driver) throws InterruptedException, IOException {
        driver.get("http://www.submarinoviagens.com.br/index.aspx"); 
        driver.findElement(By.xpath(".//*[@id='searchEngine']/ul/li[2]")).click();//passagens aereas
        driver.findElement(By.xpath(".//*[@id='txtOrigin']")).clear();
        driver.findElement(By.xpath(".//*[@id='txtOrigin']")).sendKeys("sao");
                Thread.sleep(3000);      
        driver.findElement(By.id("txtDestination")).clear();
        driver.findElement(By.id("txtDestination")).sendKeys("pdp");    
                Thread.sleep(4000);
          //Pega a data atual (ida) e acrescentar 88 dias e converter para String.
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
        driver.findElement(By.id("txtOutboundPackage")).sendKeys(ida);//data ida
        driver.findElement(By.id("txtInboundPackage")).sendKeys(volta);//data volta
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/select")).click();
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div/div[2]/a")).click();
        GrabScreenShot (driver);//print da tela
    }
   private static void masterPrice(WebDriver driver) throws InterruptedException, IOException {
        ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div/div[2]/div[3]/div/div/ul/li[1]/a");
        //driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div/ul/li[5]/ul/li[4]/input")).click();//desabilita checkbox Gol
        //driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div/ul/li[7]/div/a[1]")).click();//Aplica filtro
        //Thread.sleep(5000);
        driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div[5]/div[3]/ul/li[1]/div[1]/div/a[1]")).click();//comprar 
        GrabScreenShot (driver);//print da tela
        }
   private static void checkout(WebDriver driver) throws InterruptedException, IOException {
       ExplicitWait(driver, "html/body/div[2]/div[2]/div[4]/form/div/div/div[1]/div[2]/h4");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/section/div[1]/ul/li/div[1]/label[1]/input")).sendKeys("Qamila");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/section/div[1]/ul/li/div[1]/label[2]/input")).sendKeys("Munizz");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/section/div[1]/ul/li/div[2]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/section/div[1]/ul/li/div[3]/label[2]/input")).click();
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[1]/div/div/label[1]/input")).sendKeys("timesite@cvc.com.br");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[1]/div/div/label[2]/input")).sendKeys("timesite@cvc.com.br");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/label[1]/input")).sendKeys("4242 4242 4242 4242");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div/select[1]")).sendKeys("12");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div/select[2]")).sendKeys("23");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/label[2]/input")).sendKeys("Qamila Munizz");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/label/input")).sendKeys("111");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[3]/div/div[2]/label[1]/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[3]/div/div[2]/div[1]/label[2]/input")).click();
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[3]/div/div[2]/label[2]/input")).sendKeys("86385788733");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[3]/div/div[2]/div[2]/input[1]")).sendKeys("11");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[3]/div/div[2]/div[2]/input[2]")).sendKeys("67549878");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[3]/div/div[2]/div[3]/input")).sendKeys("06210100");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[3]/div/div[2]/div[4]/label[2]/input")).sendKeys("99");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[2]/div[3]/div/div[2]/div[5]/label[3]/input")).sendKeys("Altino");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[3]/label/input")).click();
            Thread.sleep(3000);//Aguarda preenchimento da rua.
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[4]/form/div/div/div[5]/button")).click();//clica em comprar
        Thread.sleep(2000);//Aguarda preenchimento da rua.
        ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a");//aguarda reserva aparecer
        GrabScreenShot (driver);//print da tela
        reservasTxt(driver);
   }
    /**
     *
     * @param driver
     * @throws IOException
     */
    public static void reservasTxt(WebDriver driver) throws IOException {

    FileWriter arq = new FileWriter("C:\\Users\\subt000079\\Documents\\Reservas\\reservasAirNacSubProd.txt");
    PrintWriter gravarArq = new PrintWriter(arq);
    WebElement elemento = driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a"));
    gravarArq.printf(elemento.getText());
    arq.close();
    }
    public static void ExplicitWait (WebDriver driver, String text){
        (new WebDriverWait (driver, 60)).until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
}
public static void GrabScreenShot(WebDriver driver) throws IOException{
       System.out.println("Realizado print");
       File print =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       Calendar calendar = new GregorianCalendar();
       Date x = new Date();
       SimpleDateFormat formataData = new SimpleDateFormat("HHmmss");
       calendar.setTime(x);
       String data = formataData.format(x);
       FileUtils.copyFile(print, new File("C:\\Users\\subt000079\\Pictures\\"+data+"-Print_Auto.jpg"));
       
   }
}