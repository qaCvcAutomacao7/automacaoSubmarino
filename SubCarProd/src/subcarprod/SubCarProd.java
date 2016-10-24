package subcarprod;

//import com.sun.jna.platform.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.GregorianCalendar;

//import static org.apache.xalan.lib.ExsltDatetime.date;
public class SubCarProd {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        WebDriver driver= new FirefoxDriver();

        openHome(driver);
        masterPrice(driver);
        checkout(driver);
    }
        //Metodo Preencher Motor de Busca Carros - Home
        private static void openHome(WebDriver driver) throws InterruptedException, IOException {
        driver.get("http://www.submarinoviagens.com.br/index.aspx"); 
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/ul/li[4]")).click();//Carros
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/div/div/div/label[1]/input")).sendKeys("sao paulo");
        Thread.sleep(3000);
        //Pega a data atual (ida) e acrescentar 88 dias e converter para String.
        Date x = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(x);
        c.add(Calendar.DATE, +30);
        c.add(Calendar.DATE, +30);
        c.add(Calendar.DATE, +28);
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
        
        driver.findElement(By.id("carStartDate")).sendKeys(ida);//data ida
        driver.findElement(By.id("carEndDate")).sendKeys(volta);//data volta
        ScreenShot (driver);//print da tela
        driver.findElement(By.id("btnSearch")).click();//comprar
        
    }
        //Método Master Price
        private static void masterPrice(WebDriver driver) throws InterruptedException, IOException {
        ExplicitWait(driver, "html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/table/thead/tr/th[1]");
        driver.findElement(By.xpath("html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[1]/a")).click();//Movida
        ExplicitWait(driver, "html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[3]/a");
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[3]/div[3]/div/div/div[2]/div[2]/div[2]/div[6]/div[2]/div/div/div[2]/div[3]/a")).click();//Botão Alugar
        ExplicitWait(driver, "html/body/div[12]/div/div/div[1]");
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[12]/div/div/div[1]/div[2]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[12]/div/div/div[2]/div[2]/button")).click();//Confirmar
        ScreenShot (driver);//print da tela
        }
        //Metodo Checkout
        private static void checkout(WebDriver driver) throws InterruptedException, IOException {
       ExplicitWait(driver, "html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/ul/li/label");//Aguarda carregar preço
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[1]/div/div[1]/label[1]/input")).sendKeys("Qamila");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[1]/div/div[1]/label[2]/input")).sendKeys("Munizz");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[1]/div/div[2]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[1]/div/div[3]/label/input")).sendKeys("12/12/2009");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[1]/div/div[4]/input[1]")).sendKeys("11");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[1]/div/div[4]/input[2]")).sendKeys("78946548");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[2]/div/div[1]/label/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[2]/div/div[2]/label/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[2]/div/div[3]/label/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[2]/div/div[4]/label/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section[2]/div/div[5]/label/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/label[1]/input")).sendKeys("4242 4242 4242 4242");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div/select[1]")).sendKeys("12");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div/select[2]")).sendKeys("23");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/label[2]/input")).sendKeys("Qamila Munizz");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[2]/div[2]/label/input")).sendKeys("111");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[1]/label/input")).sendKeys("12/12/1990");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[1]/div/label[1]/input")).click();
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/label/input")).sendKeys("86385788733");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/div/input[1]")).sendKeys("11");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/div/input[2]")).sendKeys("67549878");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[3]/input")).sendKeys("06210100");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[4]/div[1]/div[2]/label[1]/input")).sendKeys("99");
        driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[4]/div[2]/div[2]/label/input")).sendKeys("Altino");
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
