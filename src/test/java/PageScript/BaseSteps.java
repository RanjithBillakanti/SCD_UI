package PageScript;

import Base.BaseUtill;
import PageObjects.LoginPageObjects;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseSteps  {


    BaseUtill baseUtil;

    protected LoginPageObjects loginPageObjects = null;

    protected WebDriverWait wait;

    public BaseSteps() {

    }

    //Screen Classes Initialization
    protected void setupCucumber() {
        System.out.println("Cucumber Base Test Before-login-test-cucumber");
        //wait = new WebDriverWait(ExpectedConditions.visibilityOf());

        loginPageObjects = new LoginPageObjects(baseUtil.GetDriver());

    }


}
