package automationsubprod;

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

public class AutomationSubProd {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        WebDriver driver= new FirefoxDriver();
        //Home Aereo Nac
        openHome(driver);
        masterPrice(driver);
        checkout(driver);
       
        Thread.sleep(1000); 
        //driver.quit();apresenta poupup de erro
        driver.close();
    }
   //Metodo Home Aereo
    private static void openHome(WebDriver driver) throws InterruptedException, IOException {
        driver.get("http://www.submarinoviagens.com.br/index.aspx"); 
        driver.findElement(By.xpath(".//*[@id='searchEngine']/ul/li[2]")).click();//Passagens Aereas
        driver.findElement(By.xpath(".//*[@id='txtOrigin']")).clear();
        driver.findElement(By.xpath(".//*[@id='txtOrigin']")).sendKeys("sao");
                Thread.sleep(3000);      
        driver.findElement(By.id("txtDestination")).clear();
        driver.findElement(By.id("txtDestination")).sendKeys("rio");    
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
        driver.findElement(By.id("txtOutboundPackage")).sendKeys(ida);//Data ida
        driver.findElement(By.id("txtInboundPackage")).sendKeys(volta);//Data volta
        driver.findElement(By.id("drpAdultsQtd")).sendKeys("1");//Quantidade de ADT
        driver.findElement(By.id("btnSearch")).click();//Comprar
        try{
            driver.findElement(By.id("dialogModal")).isDisplayed();//Poup up erro no motor de Busca?
            System.out.println("Apresentado poupup de erro no motor de busca");
            GrabScreenShotErro(driver);//Print de Erro.
            Thread.sleep(1000); 
            driver.quit();
        }catch(Exception a){
            GrabScreenShot (driver);//Print da tela Home.  
            System.out.println("Efetuado print de Sucesso "+a);
        }
    }
   private static void masterPrice(WebDriver driver) throws InterruptedException, IOException {
       try{
           ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div/div[2]/div[3]/div/div/ul/li[1]/a");//Aguarda Matriz de Preço
       } catch(Exception a){
           try{
               driver.navigate().refresh();
                ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div/div[2]/div[3]/div/div/ul/li[1]/a");//Aguarda Matriz de Preço
           }catch(Exception b){
               System.out.println("Erro "+b);
           }
                System.out.println("Erro "+a);
                driver.findElement(By.className("error1")).isDisplayed();//Sem disponibilidade
                GrabScreenShotErro(driver);
                    System.out.println("Feito print de erro.");
                    Thread.sleep(1000); 
                    driver.quit();
       }
            driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div/ul/li[5]/ul/li[4]/input")).click();//desabilita checkbox Gol
            driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div/ul/li[7]/div/a[1]")).click();//Aplica filtro
            Thread.sleep(5000);
            driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div[5]/div[3]/ul/li[1]/div[1]/div/a[1]")).click();//comprar 
            GrabScreenShot (driver);//print da tela
        }
   private static void checkout(WebDriver driver) throws InterruptedException, IOException {
       try{
           ExplicitWait(driver, "html/body/div[1]/div[2]/div[4]/aside/div/section[2]/div[2]");
       }catch(Exception z){
           try{
              System.out.println("Erro "+z);
                driver.navigate().refresh();
                ExplicitWait(driver, "html/body/div[1]/div[2]/div[4]/aside/div/section[2]/div[2]"); 
           }catch(Exception m){
               System.out.println("Erro timeout "+m);
                Thread.sleep(1000); 
                driver.close();//Fecha navegador.
           }
       }
       try{
           //Valida o poupup de tarifas
       driver.findElement(By.id("retarifacaoAir")).isDisplayed();
       GrabScreenShot (driver);//print da tela
       driver.findElement(By.xpath("html/body/div[2]/div[2]/div[3]/div[2]/button[2]")).click();
       }catch (Exception g){
           System.out.println("Continuar "+g);
       }
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
        try{
            ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a");//Aguarda reserva aparecer
        }catch(Exception s){
            System.out.println("Timeout reserva "+s);
        }
        try{
            Thread.sleep(5000); 
            driver.findElement(By.id("WLS_smartButtonTitle")).isDisplayed();
            for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            }
            driver.findElement(By.xpath("html/body/div[2]/div/a")).click();
        }catch(Exception f){
            System.out.println("Não apresentou poupUp de Prêmio "+f);
        }    
        GrabScreenShot (driver);//print da tela
        reservasTxt(driver);
   }
    public static void reservasTxt(WebDriver driver) throws IOException {
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat out = new SimpleDateFormat("HHmmss");
    Date date = new Date();
    String hora = out.format(date);
    calendar.setTime(date);
    System.out.println(out.format(calendar.getTime()));
    String HostName = System.getProperty("user.name");
        try (FileWriter arq = new FileWriter("C:\\Users\\"+HostName+"\\Documents\\"+hora+"-SubAir_ProdNac.txt")) {
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
       FileUtils.copyFile(print, new File("C:\\Users\\"+HostName+"\\Pictures\\"+data+"-SubAir_ProdNac.jpg"));
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
       FileUtils.copyFile(print, new File("C:\\Users\\"+HostName+"\\Pictures\\"+data+"-SubAir_ProdNac_Erro.jpg"));
   }
}
