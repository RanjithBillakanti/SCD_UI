package PageScript;

import Base.BaseUtill;

import Reports.ExtentReportManager;
import Utilities.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private ExtentTest logger=ExtentReportManager.logger;
    private ExtentReports extent=ExtentReportManager.extent;

    @BeforeTest
    public void setupLoginSteps() {
        System.out.println("Before-login-test");
        BaseSteps baseSteps = new BaseSteps();
        baseSteps.setupCucumber();
    }

    @Test(priority = 1)

    public void clickonLogin() throws IOException {

        Log.info("Test Started");
        BaseUtill baseUtil = new BaseUtill();
        driver = baseUtil.GetDriver();
        try {
            logger = extent.createTest("Verification of application");
            logger = logger.createNode("Login page check").assignAuthor("Nagaraj");

            System.out.println("hr");
            driver.get(baseUtil.LoadProperties().getProperty("Serverurl"));
            //remove this code and add in generics
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            logger.log(Status.PASS, "Test Case finally passed last time");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterClass
    public void tearDown() {
        Log.info("Tests are ending");
        driver.quit();
    }
}
