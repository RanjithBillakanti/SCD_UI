package PageScript;

import Base.BaseUtill;

import Reports.ExtentReportManager;
import Utilities.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.messages.internal.com.google.protobuf.ByteString;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

import static Reports.ExtentReportManager.logger;

public class LoginPage {

    private WebDriver driver;
    private ExtentTest logger = ExtentReportManager.logger;
    private ExtentReports extent = ExtentReportManager.extent;

    @BeforeTest
    public void setupLoginSteps() {
        System.out.println("Before-login-test");
        BaseSteps baseSteps = new BaseSteps();
        baseSteps.setupCucumber();
    }

  /*  @Test(priority = 1)

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
            //   logger.log(Status.PASS, "Test Case finally passed last time");
            driver.findElement(By.xpath("//span[contains(text(),'Your Amazon Business Account')]")).click();

        } catch (Exception e) {
            System.out.println(e);

            //    String scr= ExtentReportManager.captureScreenShot(driver);
            logger.fail(" failed again");//+logger.addScreenCaptureFromPath("C:\\Users\\Nagaraj\\IdeaProjects\\SCD_UI\\target\\Screenshots\\07022021100235161.png"));

            logger.addScreenCaptureFromPath(ExtentReportManager.captureScreenShot(driver));

        }
    }*/

    @Test
    public void check() throws IOException {
        BaseUtill baseUtil = new BaseUtill();
        driver = baseUtil.GetDriver();
        String filepath = "C:\\Users\\Nagaraj\\IdeaProjects\\SCD_UI\\target\\Screenshots\\hanuman.jpg";
        logger = extent.createTest("Verification of application");
        try {
            driver.get(baseUtil.LoadProperties().getProperty("Serverurl"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            Thread.sleep(10);
            driver.findElement(By.xpath("//span[contains(text(),'Your Amazon Business Account')]")).click();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @AfterMethod
    public void aftermethod(ITestResult iTestResult) {
        String methodname = iTestResult.getMethod().getMethodName();
        System.out.println("Printing method name :"+methodname);
        if (iTestResult.getStatus() == iTestResult.FAILURE) {
            String exceptionmessage = Arrays.toString(iTestResult.getThrowable().getStackTrace());
            logger.fail("Failure" + exceptionmessage.replaceAll(",", "<br>"));

            String path = takescreenshot(iTestResult.getMethod().getMethodName());
            try {
                logger.fail("Screenshot of failure" + MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            } catch (Exception e) {
                logger.fail("Test failed, cannot attach screenshot");
            }
            String logtext = "<br>Test Method" + methodname + "failed</br>";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        } else if (iTestResult.getStatus() == iTestResult.SUCCESS) {

            String pathe = takescreenshot(iTestResult.getMethod().getMethodName());
            try {
                logger.pass("Screenshot of pass:", MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot(methodname)).build());
            } catch (Exception e) {
                logger.pass("Test pass,  attached screenshot");
            }
            String logtexts = "<br>Test Method :" + methodname + " Passed</br>";
            Markup m = MarkupHelper.createLabel(logtexts, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (iTestResult.getStatus() == iTestResult.SKIP) {
            String logtext = "<br>Test Method" + methodname + "Skipped</br>";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
            logger.log(Status.SKIP, m);
        }
    }

    public String takescreenshot(String methodName) {
        String fileName = getScreenshotName(methodName);
        String directory = System.getProperty("user.dir")+"\\target\\Screenshots\\";
        System.out.println(directory);
       new File(directory).mkdirs();
        String paths = directory + fileName;
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(paths));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paths;
    }

    public static String getScreenshotName(String methodName) {
        Date d = new Date();
        String fileName = methodName + "_" + d.toString().replace(":", "_").replace("", "_") + ".png";
        return fileName;
    }

   // @AfterClass
    public void tearDown() {
        Log.info("Tests are ending");
        driver.quit();
    }
}
