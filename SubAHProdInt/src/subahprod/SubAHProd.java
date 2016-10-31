package subahprod;

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

public class SubAHProd {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        WebDriver driver= new FirefoxDriver();
        //Home A+H Int
        openHome(driver);
        masterPrice(driver);
        checkout(driver);
            Thread.sleep(1000);
            //driver.quit();apresenta poupup de erro
            driver.close();
    }
    //Home A+H Int - 2ADT
    private static void openHome(WebDriver driver) throws InterruptedException, IOException {
        driver.manage().deleteAllCookies();
        driver.get("http://www.submarinoviagens.com.br/index.aspx"); 
        driver.findElement(By.xpath("html/body/form/div/div[1]/div[2]/div[2]/ul/li[1]")).click();//A+H Int
        driver.findElement(By.id("txtOrigin")).clear();
        driver.findElement(By.id("txtOrigin")).sendKeys("sao");
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
        driver.findElement(By.id("txtOutboundDate")).sendKeys(ida);//Data ida
        driver.findElement(By.id("txtInboundDate")).sendKeys(volta);//Data volta
        driver.findElement(By.id("btnSearch")).click();//Comprar
        try{
            driver.findElement(By.id("dialogModal")).isDisplayed();//Poup up no motor de Busca?
            System.out.println("Apresentado poupup de erro no motor de busca");
            GrabScreenShotErro(driver);//Print de Erro.
            Thread.sleep(1000); 
            driver.quit();
        }catch(Exception a){
            GrabScreenShot (driver);//Print da tela Home.  
            System.out.println("Efetuado print Home com Sucesso "+a);
        }
    }
    private static void masterPrice(WebDriver driver) throws InterruptedException, IOException {
       try{
       ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div[2]/div[3]/div/div[1]/h2");//Aguarda 60s preencher Resultado Busca.
       } catch (Exception b){
           try{
               driver.navigate().refresh();
               ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div[2]/div[3]/div/div[1]/h2");//Aguarda preencher Resultado Busca.
           }catch (Exception j){
               System.out.println("Erro "+j);
           }
                System.out.println("Erro TimeOut resultado da busca "+b);
                driver.findElement(By.className("error1")).isDisplayed();//Não tem disponibilidade.
                System.out.println("Não tem disponibilidade.");
                GrabScreenShotErro(driver);//Print de Erro
                System.out.println("Feito print de erro.");
                Thread.sleep(1000); 
                driver.quit();//Fecha navegador.
       }
                driver.findElement(By.id("packageBuy")).click();//Clica em Comprar.
        try{
                Thread.sleep(3000); 
                driver.findElement(By.xpath("html/body/div[18]/div/div/div/button")).isDisplayed();//Valida preenchimento Tipo de Quarto.
                driver.findElement(By.xpath("html/body/div[18]/div/div/div/button")).click();//Seleciona um quarto.
        }catch(Exception c){
                System.out.println("Não precisou selecionar quarto "+c);
        }
                GrabScreenShot (driver);//Print da tela MasterPrice.
        }
    private static void checkout(WebDriver driver) throws InterruptedException, IOException {
       try{
                ExplicitWait(driver, "html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/h4");//Aguarda preencher Formas de Pagamento.
       }catch (Exception d){
           try{
                driver.navigate().refresh();
                ExplicitWait(driver, "html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/h4");//Aguarda preencher Formas de Pagamento.   
           }catch(Exception k){
                System.out.println("Erro timeout "+k);
           }
                System.out.println("TimeOut para carregar formas de pagamento "+d);
                Thread.sleep(1000); 
                driver.close();//Fecha navegador.
            }
       try{
           driver.findElement(By.id("retarifacaoAir")).isDisplayed();//Valida o poupup de tarifas.
           GrabScreenShot (driver);//Print da tela.
           driver.findElement(By.xpath("html/body/div[2]/div[2]/div[3]/div[2]/button[2]")).click();//Clica em Continuar.
       }catch (Exception g){
           System.out.println("Continuar "+g);
            }
            driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/section/div[1]/ul/li[1]/div[1]/label[1]/input")).sendKeys("Qamyle");
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
            driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/label[2]/input")).sendKeys("Qamile Munizz");
            driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[3]/div[2]/div[2]/label/input")).sendKeys("111");
            driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[1]/label/input")).sendKeys("12/12/1990");
            driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[1]/div/label[2]/input")).click();
            driver.findElement(By.xpath("html/body/div[2]/div[2]/div[4]/form/div/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/label/input")).sendKeys("63771523028");
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
        try{
            ExplicitWait(driver, "html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a");//Aguarda reserva ser apresentada.
        }catch (Exception h){
            System.out.println("Timeout reserva "+h);
        }
        GrabScreenShot (driver);//print da tela
            reservasTxt(driver);
        try{
            Thread.sleep(7000); 
            //ExplicitWait(driver, "WLS_smartButtonTitle");
            driver.findElement(By.id("WLS_smartButtonTitle")).isDisplayed();//Verifica se o poupUp é apresentado.
            for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            }
            driver.findElement(By.xpath("html/body/div[2]/div/a")).click();//Fecha o PoupUp.
        }catch(Exception f){
            System.out.println("Não apresentou poupUp de Prêmio "+f);
        }
            
        }
    public static void reservasTxt(WebDriver driver) throws IOException {
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat out = new SimpleDateFormat("HHmmss");
            Date date = new Date();
            String hora = out.format(date);
            calendar.setTime(date);
            System.out.println(out.format(calendar.getTime()));
                String HostName = System.getProperty("user.name");
        try (FileWriter arq = new FileWriter("C:\\Users\\"+HostName+"\\Documents\\"+hora+"-SubAeH_ProdInt.txt")) {
            PrintWriter gravarArq = new PrintWriter(arq);
            WebElement elemento = driver.findElement(By.xpath("html/body/form/div/div/div/div[2]/div/div/div[1]/p/span/strong/a"));
            gravarArq.printf(elemento.getText());
        }
        System.out.println("Realizado Print");
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
              System.out.println (HostName);
            FileUtils.copyFile(print, new File("C:\\Users\\"+HostName+"\\Pictures\\"+data+"-SubAeH_ProdInt.jpg"));
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
              System.out.println (HostName);
            FileUtils.copyFile(print, new File("C:\\Users\\"+HostName+"\\Pictures\\"+data+"-SubAeH_ProdInt_Erro.jpg"));
    }
}
