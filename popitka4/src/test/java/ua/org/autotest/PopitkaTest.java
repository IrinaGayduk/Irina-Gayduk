package ua.org.autotest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import java.io.File;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.fail;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;

public  class PopitkaTest {
    private static String baseUrl;
    private StringBuffer SearchErrors = new StringBuffer();
    public static WebDriver driver;

    public static void TakeScreenShot(String inputWord, int number) {

        try {
            String targetUrl = driver.getCurrentUrl();
            String targetImg= "GoogleTest1_page" + Integer.toString(number) + inputWord+ ".png";
            String command = "D:\\КПИ\\phantomjs-2.1.1-windows\\bin\\phantomjs D:\\КПИ\\phantomjs-2.1.1-windows\\examples\\rasterize.js "+targetUrl + " " +targetImg;
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ScreenAllPages (String inputWord, int lastPageNumber)
    {
        int pageNumber = lastPageNumber;
        while (pageNumber>=1)
        {
            try{
                TakeScreenShot(inputWord ,pageNumber);
                Thread.sleep(3000);
                driver.findElement(By.xpath("//*[@id='pnprev']/span[1]")).click();
                driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

                pageNumber--;

            }
            catch(NoSuchElementException e){
                break;
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @BeforeClass
    public static void setProperty() {
        System.setProperty("webdriver.chrome.driver", "D:\\КПИ\\3 курс\\Тестировка\\chromedriver.exe");
    }

    @Before
    public void setUpTest() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com.ua";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @Test
    public void testSearch() throws Throwable {
        String inputWord = "Книга";
        String [] searchTarget = {"Эксмо","Kniga.biz.ua","Lamborghini"};
        int pageNumber;

        for (int i=0; i<searchTarget.length; i++){
            pageNumber = 1;
            try {
                driver.get(baseUrl);
                WebElement searchField = driver.findElement(By.xpath("/html/body/div/div[3]/form/div[2]/div/div[1]/div/div[1]/input"));
                driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

                searchField.clear();
                if(searchField.isEnabled())
                    searchField.sendKeys(inputWord);
                //Thread.sleep(1000);
                searchField.submit();
                driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            }catch (Throwable e)
            {
                SearchErrors.append(e.toString());
                return;
            }
            boolean elementFound = true;

            while (elementFound)
            {
                try{
                    driver.findElement(By.partialLinkText(searchTarget[i]));
                    TakeScreenShot(searchTarget[i] ,pageNumber);
                    break;
                }
                catch(NoSuchElementException e){
                    try {
                        driver.findElement(By.xpath("//a[@id='pnnext']")).click();
                        pageNumber++;
                    }
                    catch (NoSuchElementException e1){
                        System.out.println("The last page, target item wasn't found.");
                        ScreenAllPages(searchTarget[i],pageNumber);
                        elementFound = false;
                    }
                }
            }
            if (elementFound) System.out.println("Item "+searchTarget[i]+" was found on page number "+pageNumber);
            else System.out.println("Pages doesn't have this item, check screenshots");
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String SearchErrorString = SearchErrors.toString();
        if (!"".equals(SearchErrorString)) {
            fail(SearchErrorString);
        }
    }
}

