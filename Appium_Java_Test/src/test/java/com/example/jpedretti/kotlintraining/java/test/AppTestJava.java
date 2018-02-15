package com.example.jpedretti.kotlintraining.java.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTestJava extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTestJava(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTestJava.class );
    }

    @Before
    public void setUp() {
        AppiumController.Companion.setExecutionOS(AppiumController.OS.ANDROID);
        AppiumController.Companion.getInstance().start();
    }

    @After
    public void tearDown() {
        AppiumController.Companion.getInstance().tearDown();
    }

    public void testOpenApp()
    {
        final AppiumDriver<WebElement> driver = AppiumController.Companion.getInstance().driver;
        assertNotNull(driver.getContext());
        WebElement greetingTextView = driver.findElementById("greeting");
        assertEquals("when 900 years old you reach look as good you will not", greetingTextView.getText());

        WebElement controllerResponseTextView = driver.findElementById("baseControllerResult");
        assertEquals("BaseControllerStuff: stuff done: Go to Dagoba", controllerResponseTextView.getText());

        WebElement goToDIButton = driver.findElement(byText("GOTO DI"));
        assertTrue(goToDIButton.isDisplayed() && goToDIButton.isEnabled());
    }

    public void testGoToDi_DoServiceStuff()
    {
        final AppiumDriver<WebElement> driver = AppiumController.Companion.getInstance().driver;

        goToDiActivity();

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
        final AppiumDriver<WebElement> driver = AppiumController.Companion.getInstance().driver;

        goToDiActivity();
        ((AndroidDriver<WebElement>)driver).pressKeyCode(AndroidKeyCode.BACK);
        String activity = ((AndroidDriver<WebElement>)driver).currentActivity();
        assertEquals(".activities.MainActivity", activity);
    }

    private void goToDiActivity()
    {
        final AppiumDriver<WebElement> driver = AppiumController.Companion.getInstance().driver;

        driver.findElement(byText("goto DI")).click();
        String activity = ((AndroidDriver<WebElement>)driver).currentActivity();
        assertEquals(".activities.DiAndBindingActivity", activity);
    }


    private static By byText(String text){
        String query = String.format("new UiSelector().textContains(\"%s\")", text);
        return new MobileBy.ByAndroidUIAutomator(query);
    }
}
