package ua.org.autotest;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;
public class Rozetka7 {

        private static String baseUrl;
        private StringBuffer RozetkaErrors = new StringBuffer();
        public static WebDriver driver;

        public static void TakeScreenShot() {

            try {
                String targetUrl = driver.getCurrentUrl();
                String targetImg= "Rozetka_page";
                String command = "D:\\КПИ\\phantomjs-2.1.1-windows\\bin\\phantomjs D:\\КПИ\\phantomjs-2.1.1-windows\\examples\\rasterize.js "+targetUrl + " " +targetImg;
                Process p = Runtime.getRuntime().exec(command);
                p.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @BeforeClass
        public static void setProperty() {
            System.setProperty("webdriver.chrome.driver", "D:\\КПИ\\3 курс\\Тестировка\\chromedriver.exe");
        }

        @Before
        public void setUpTest() throws Exception {
            driver = new ChromeDriver();
            baseUrl = "https://www.rozetka.com.ua";
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        }

        @Test
        public void testFilter() throws Throwable {
            String minPrice = "1";
            try {
                driver.get(baseUrl);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Thread.sleep(1000);
                WebElement searchLine = driver.findElement(By.cssSelector("body > app-root > div > div > rz-header > header > div > div.header-bottomline > div.header-search.js-app-search > form > div > div > input"));
                searchLine.clear();
                if (searchLine.isEnabled())
                    searchLine.sendKeys("Видеорегистратор");
                searchLine.submit();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Thread.sleep(1000);
                driver.findElement(By.cssSelector("body > app-root > div > div > rz-header > header > div > div.header-bottomline > div.header-search.js-app-search > form > div > div > div > ul > li:nth-child(3)")).click();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Thread.sleep(1000);
                WebElement minField = driver.findElement(By.cssSelector("#price\\5b min\\5d"));
                WebElement okButton = driver.findElement(By.xpath("//*[@id='submitprice']"));
                Thread.sleep(1000);
                minField.clear();
                driver.findElement(By.xpath("//*[@id=\"price[min]\"]")).clear();
                Thread.sleep(1000);
                if (minField.isEnabled()) {
                    minField.clear();
                    Thread.sleep(1000);
                    minField.click();
                    minField.clear();
                    minField.sendKeys(minPrice);
                }
                Thread.sleep(1000);
                okButton.click();
                driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

               // if (driver.findElement(By.xpath("//*[@id='price[min]']")).getAttribute("value").compareTo((minPrice+790)) != 0) {
                //    RozetkaErrors.append("Minimum price field doesn't match the input.");
               //     throw new Throwable();
              //  }

            } catch (Throwable e) {
                RozetkaErrors.append(e.toString());
                return;
            }

            List<WebElement> prices = driver.findElements(By.cssSelector(".g-price-uah"));
            int count = 0;
            for (WebElement price : prices) {
                if (Integer.valueOf(price.getText().replaceAll("[^0-9]", "")) < Integer.parseInt(minPrice)) {
                    RozetkaErrors.append("\nElement " + (count + 1) + " has price lower then filter;");
                    count++;
                }
            }
            System.out.println("\n\n" + count + " of " + prices.size() + " elements failed test.");
            Thread.sleep(5000);
            TakeScreenShot();
        }

        @After
        public void tearDown() throws Exception {
            driver.quit();
            String RozetkaErrorString = RozetkaErrors.toString();
            if (!"".equals(RozetkaErrors.toString())) {
                fail(RozetkaErrorString);
            }
        }
    }
