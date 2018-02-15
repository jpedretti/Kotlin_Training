package com.example.jpedretti.kotlintraining.java.test.screens

import com.example.jpedretti.kotlintraining.java.test.AppiumController
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSFindBy
import junit.framework.TestCase
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

abstract class DiScreen(private val driver: AppiumDriver<WebElement>) {

    @CacheLookup
    @iOSFindBy(accessibility = "")
    @AndroidFindBy(id = "app_name")
    private lateinit var appNameTextElement: WebElement

    @iOSFindBy(accessibility = "")
    @AndroidFindBy(id = "do_service_stuff_by_view_model")
    private lateinit var doServiceStuffButton: WebElement

    abstract fun checkDiScreenOpened()

    abstract fun clickBack()

    fun checkAppName() {
        TestCase.assertEquals("KotlinTraining", appNameTextElement.text)
    }

    fun clickDoServiceStuffButton() {
        doServiceStuffButton.click()
    }

    fun checkDoStuffResult() {
        val doStuffResult = if (AppiumController.isAndroid) {
            driver.findElementById("view_model_result")
        } else  {
            driver.findElementByXPath("")
        }

        TestCase.assertTrue(doStuffResult.isDisplayed)
        val wait = WebDriverWait(driver, 5)
        val resultShown = wait.until(ExpectedConditions.textToBePresentInElement(
                doStuffResult, "finished doing service stuff"))
        TestCase.assertTrue(resultShown)
    }
}