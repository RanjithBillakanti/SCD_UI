/*


package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
//import org.testng.annotations.Parameters;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import Base.BaseUtill;

public class Hookss {
    private static BaseUtill baseUtil;
    private static WebDriver driver;

    public Hookss(BaseUtill baseUtil, WebDriver driver) {
        this.baseUtil = baseUtil;
        this.driver = driver;
    }

    //@BeforeTest
    public static WebDriver setup() throws IOException {
        driver = baseUtil.GetDriver();
        String browser = LoadProperties().getProperty("browsertype");

        if (baseUtil.GetDriver() == null) {
            String path = System.getProperty("user.dir");

            if (browser.contains("Chrome")) {
                System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
                ChromeOptions chromeOptions = (ChromeOptions) new ChromeOptions().setAcceptInsecureCerts(true);
                //  baseUtil.setDriver(new ChromeDriver());
                driver = new ChromeDriver(chromeOptions);


            } else if (browser.contains("firefox")) {
                System.setProperty("webdriver.gecko.driver", path + "\\Drivers\\geckodriver.exe");
                FirefoxOptions firefoxOptions = (FirefoxOptions) new FirefoxOptions().setAcceptInsecureCerts(true);
                baseUtil.setDriver(new FirefoxDriver());
                driver = new FirefoxDriver(firefoxOptions);

            } else if (browser.contains("IE")) {
                System.setProperty("webdriver.ie.driver", path + "\\Drivers\\IEDriverServer.exe");
                InternetExplorerOptions explorerOptions = new InternetExplorerOptions().setAcceptInsecureCerts(true);
                //   baseUtil.setDriver(new InternetExplorerDriver());
                driver = new InternetExplorerDriver(explorerOptions);
            }
        }

        return baseUtil.GetDriver();
    }

    public static Properties LoadProperties() {
        String path = System.getProperty("user.dir");
        //InputStream inStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Device Configuration properties file cannot be found");
        }
        return prop;
    }
}

*/
