package com.example.jpedretti.kotlintraining.java.test

import io.appium.java_client.MobileBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidKeyCode
import junit.framework.Test
import junit.framework.TestSuite
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class AppTestKotlin(testName: String) : BaseDriver(testName) {

    fun testOpenApp() {
        assertNotNull(driver.context)
        val greetingTextView = driver.findElementById("greeting")
        assertEquals("when 900 years old you reach look as good you will not", greetingTextView.text)

        val controllerResponseTextView = driver.findElementById("baseControllerResult")
        assertEquals("BaseControllerStuff: stuff done: Go to Dagoba", controllerResponseTextView.text)

        val goToDIButton = driver.findElement(byText("GOTO DI"))
        assertTrue(goToDIButton.isDisplayed && goToDIButton.isEnabled)
    }

    fun testGoToDi_DoServiceStuff() {
        goToDiActivity()

        val appNameText = driver.findElementById("app_name").text
        assertEquals("KotlinTraining", appNameText)

        driver.findElementById("do_service_stuff_by_view_model").click()

        val viewModelResult = driver.findElementById("view_model_result")
        assertTrue(viewModelResult.isDisplayed)
        val wait = WebDriverWait(driver, 5)
        val resultShown = wait.until(ExpectedConditions.textToBePresentInElement(viewModelResult, "finished doing service stuff"))
        assertTrue(resultShown)
    }

    fun testGoToDi_PressBack() {
        goToDiActivity()
        (driver as AndroidDriver<WebElement>).pressKeyCode(AndroidKeyCode.BACK)
        val activity = (driver as AndroidDriver<WebElement>).currentActivity()
        assertEquals(".activities.MainActivity", activity)
    }

    private fun goToDiActivity() {
        driver.findElement(byText("goto DI")).click()
        val activity = (driver as AndroidDriver<WebElement>).currentActivity()
        assertEquals(".activities.DiAndBindingActivity", activity)
    }

    companion object {
        /**
         * @return the suite of tests being tested
         */
        fun suite(): Test {
            return TestSuite(AppTestKotlin::class.java)
        }

        internal fun byText(text: String): By =
                MobileBy.ByAndroidUIAutomator("new UiSelector().textContains(\"$text\")")
    }
}
