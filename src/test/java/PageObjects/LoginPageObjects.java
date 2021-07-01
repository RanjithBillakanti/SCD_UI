package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {

    private WebDriver driver;
    public LoginPageObjects(WebDriver driver) {

        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    }


