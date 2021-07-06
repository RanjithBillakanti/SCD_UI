package Listeners;

import Report.ExtentReportManager;
import Utilities.Log;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends ExtentReportManager implements ITestListener {

    private WebDriver driver;
    ExtentReportManager ext;
    private static ExtentReports extent;
    public static ExtentTest logger=ExtentReportManager.logger;

    public TestListener() throws IOException {

    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    public static void setExtent(ExtentReports extent) {
        TestListener.extent = extent;
    }
    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
        extent = ExtentReportManager.report();


    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

       /* String methodname = iTestResult.getMethod().getMethodName();

        if (iTestResult.getStatus() == iTestResult.FAILURE) {
            String exceptionmessage = Arrays.toString(iTestResult.getThrowable().getStackTrace());
            
            logger.fail("Failure" + exceptionmessage.replaceAll(",", "<br>"));
           *//* String path = takescreenshot(iTestResult.getMethod().getMethodName());
            System.out.println("Printing path :" + path);*//*
            try {
                String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                //   logger.fail(MediaEntityBuilder.createScreenCaptureFromPath(base64Screenshot).build());
                logger.log(Status.FAIL, "Test Failed", logger.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
            } catch (Exception e) {
                logger.fail("Test failed, cannot attach screenshot");
            }
            *//*Object testClass = iTestResult.getInstance();
            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            logger.log(Status.FAIL, "Test Failed", logger.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));*//*
            String logtext = "<br>Test Method" + methodname + "failed</br>";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        } else if (iTestResult.getStatus() == iTestResult.SUCCESS) {
            String logtexts = "<br>Test Method :" + methodname + " Passed</br>";
            Markup m = MarkupHelper.createLabel(logtexts, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (iTestResult.getStatus() == iTestResult.SKIP) {
            String logtext = "<br>Test Method" + methodname + "Skipped</br>";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
            logger.log(Status.SKIP, m);
        }*/
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
//        logger.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }



}