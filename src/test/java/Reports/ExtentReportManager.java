package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;

import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;

public class ExtentReportManager {


    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    public static ExtentTest logger;
    public static ExtentReports extent = new ExtentReports();
  //  static String dest;

    @BeforeClass
    @SuppressWarnings("Duplicates")
    public static ExtentReports report() {
        String projectName = "PAW9";
        try {

            ExtentKlovReporter klovReporter = new ExtentKlovReporter(projectName);
            klovReporter.initMongoDbConnection("localhost", 27017);
            klovReporter.initKlovServerConnection("http://localhost");
            String css = ".r-img {width: 50%;}";
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Architecture", System.getProperty("os.arch"));
            extent.attachReporter(klovReporter);

        } catch (Exception e) {
            System.out.println(e);
        }
        return extent;
    }

    public static String captureScreenShot(WebDriver driver) throws IOException {

        TakesScreenshot screen = (TakesScreenshot) driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir");
        String dest = path + "\\target\\Screenshots\\" + getcurrentdateandtime() + ".png";
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }

    public static String takeScreenShot( WebDriver driver) throws Exception
    {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage img = ImageIO.read(screen);
        File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
        ImageIO.write(img, "png", new File(filetest + "\\Screenshots\\" + " - "  + ".png"));
        logger.info("Details of " +MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "\\Screenshots\\"  + " - "  + ".png").build());

        return null;
    }

    private static String getcurrentdateandtime() {
        String str = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
            Date date = new Date();
            str = dateFormat.format(date);
            str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}