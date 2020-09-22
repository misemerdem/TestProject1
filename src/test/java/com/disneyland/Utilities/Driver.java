package com.disneyland.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
public class Driver {
    private static WebDriver driver;
    private Driver(){ }
    public static WebDriver getDriver (){
        if(driver==null){
            String browser= ConfigurationReader.getProperty("browser");
            if ("chrome".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if ("Chrome".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if ("chrome-headless".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
            } else if ("firefox-headless".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
            } else if ("Firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if ("edge".equals(browser)) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else if ("ie".equals(browser)) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            } else if ("chrome-remote".equals(browser)) {
                try {
                    ChromeOptions chromeOptionsptions = new ChromeOptions();
                    URL url = new URL("http://100.26.155.48:4444/wd/hub");
                    driver = new RemoteWebDriver(url, chromeOptionsptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                throw new RuntimeException("No Such Browser!!" + browser);
            } else {
                throw new RuntimeException("No Such Browser!!" + browser);
            }
        }
        return driver;
    }
    public static void closeDriver(){
        if(driver!=null){
            driver.close();
            driver=null;
        }
    }

}
