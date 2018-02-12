package com.example.jpedretti.kotlintraining.java.test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseDriver
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testOpenApp()
    {
        assertNotNull(driver.getContext());
        WebElement greetingTextView = driver.findElementById("greeting");
        assertEquals("when 900 years old you reach look as good you will not", greetingTextView.getText());

        WebElement controllerResponseTextView = driver.findElementById("baseControllerResult");
        assertEquals("BaseControllerStuff: stuff done: Go to Dagoba", controllerResponseTextView.getText());

        WebElement goToDIButton = driver.findElement(ByText("GOTO DI"));
        assertTrue(goToDIButton.isDisplayed() && goToDIButton.isEnabled());
    }

    public void testGoToDi_DoServiceStuff()
    {
        GoToDiActivity();

        String appName_text = driver.findElementById("app_name").getText();
        assertEquals("KotlinTraining", appName_text);

        driver.findElementById("do_service_stuff_by_view_model").click();

        WebElement viewModelResult = driver.findElementById("view_model_result");
        assertTrue(viewModelResult.isDisplayed());
        WebDriverWait wait = new WebDriverWait(driver, 5);
        boolean resultShown = wait.until(ExpectedConditions.textToBePresentInElement(viewModelResult, "finished doing service stuff"));
        assertTrue(resultShown);
    }

    public void testGoToDi_PressBack()
    {
        GoToDiActivity();
        ((AndroidDriver<WebElement>)driver).pressKeyCode(AndroidKeyCode.BACK);
        String activity = ((AndroidDriver<WebElement>)driver).currentActivity();
        assertEquals(".activities.MainActivity", activity);
    }

    void GoToDiActivity()
    {
        driver.findElement(ByText("goto DI")).click();
        String activity = ((AndroidDriver<WebElement>)driver).currentActivity();
        assertEquals(".activities.DiAndBindingActivity", activity);
    }


    static By ByText(String text){
        String query = String.format("new UiSelector().textContains(\"%s\")", text);
        return new MobileBy.ByAndroidUIAutomator(query);
    }
}
