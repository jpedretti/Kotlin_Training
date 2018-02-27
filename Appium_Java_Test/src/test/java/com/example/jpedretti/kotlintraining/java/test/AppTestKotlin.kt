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

    fun testGoToDi_DoServiceStuff() {
        val driver = AppiumController.getInstance().driver
        goToDiActivity(driver)
        checkAppName(driver)
        clickDoStuffButton(driver)
        checkDoStuffResult(driver)
    }

    companion object {
        /**
         * @return the suite of tests being tested
         */
        fun suite(): Test =
            TestSuite(AppTestKotlin::class.java)

        private fun byText(text: String): By =
                MobileBy.ByAndroidUIAutomator("new UiSelector().textContains(\"$text\")")

        fun goToDiActivity(driver: AppiumDriver<WebElement>) {
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
            val resultShown = wait.until(ExpectedConditions.textToBePresentInElement(viewModelResult, "May the force be with you!"))
            assertTrue(resultShown)
        }
    }
}
