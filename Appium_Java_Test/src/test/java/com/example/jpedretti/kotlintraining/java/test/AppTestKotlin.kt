package com.example.jpedretti.kotlintraining.java.test

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidKeyCode
import junit.framework.Test
import junit.framework.TestCase
import junit.framework.TestSuite
import org.junit.After
import org.junit.Before
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class AppTestKotlin(testName: String) : TestCase(testName) {

    @Before
    public override fun setUp() {
        AppiumController.executionOS = AppiumController.OS.ANDROID
        AppiumController.getInstance().start()
    }

    @After
    public override fun tearDown() {
        AppiumController.getInstance().tearDown()
    }

    fun testOpenApp() {
        val driver = AppiumController.getInstance().driver
        checkAppIsOpened(driver)
        checkGreeting(driver)
        checkControllerResponse(driver)
        checkGoToDiButton(driver)
    }

    fun testGoToDi_DoServiceStuff() {
        val driver = AppiumController.getInstance().driver
        goToDiActivity(driver)
        checkAppName(driver)
        clickDoStuffButton(driver)
        checkDoStuffResult(driver)
    }

    fun testGoToDi_PressBack() {
        val driver = AppiumController.getInstance().driver
        goToDiActivity(driver)
        clickBackButton(driver)
        checkMainActivityIsOpened(driver)
    }

    companion object {
        /**
         * @return the suite of tests being tested
         */
        fun suite(): Test =
            TestSuite(AppTestKotlin::class.java)

        private fun byText(text: String): By =
                MobileBy.ByAndroidUIAutomator("new UiSelector().textContains(\"$text\")")

        fun checkAppIsOpened(driver: AppiumDriver<WebElement>) =
            assertNotNull(driver.context)

        fun checkGreeting(driver: AppiumDriver<WebElement>) {
            val greetingTextView = driver.findElementById("greeting")
            assertEquals("when 900 years old you reach look as good you will not", greetingTextView.text)
        }

        fun checkControllerResponse(driver: AppiumDriver<WebElement>) {
            val controllerResponseTextView = driver.findElementById("baseControllerResult")
            assertEquals("BaseControllerStuff: stuff done: Go to Dagoba", controllerResponseTextView.text)
        }

        fun checkGoToDiButton(driver: AppiumDriver<WebElement>) {
            val goToDIButton = driver.findElement(byText("GOTO DI"))
            assertTrue(goToDIButton.isDisplayed && goToDIButton.isEnabled)
        }

        fun goToDiActivity(driver: AppiumDriver<WebElement>) {
            driver.findElement(byText("goto DI")).click()
            val activity = (driver as AndroidDriver<WebElement>).currentActivity()
            assertEquals(".activities.DiAndBindingActivity", activity)
        }

        fun checkAppName(driver: AppiumDriver<WebElement>) {
            val appNameText = driver.findElementById("app_name").text
            assertEquals("KotlinTraining", appNameText)
        }

        fun clickDoStuffButton(driver: AppiumDriver<WebElement>) =
            driver.findElementById("do_service_stuff_by_view_model").click()

        fun checkDoStuffResult(driver: AppiumDriver<WebElement>) {
            val viewModelResult = driver.findElementById("view_model_result")
            assertTrue(viewModelResult.isDisplayed)
            val wait = WebDriverWait(driver, 5)
            val resultShown = wait.until(ExpectedConditions.textToBePresentInElement(viewModelResult, "finished doing service stuff"))
            assertTrue(resultShown)
        }

        fun clickBackButton(driver: AppiumDriver<WebElement>) =
            (driver as AndroidDriver<WebElement>).pressKeyCode(AndroidKeyCode.BACK)

        fun checkMainActivityIsOpened(driver: AppiumDriver<WebElement>) {
            val activity = (driver as AndroidDriver<WebElement>).currentActivity()
            assertEquals(".activities.MainActivity", activity)
        }
    }
}
