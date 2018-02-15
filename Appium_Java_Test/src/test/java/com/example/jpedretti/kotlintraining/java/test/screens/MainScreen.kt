package com.example.jpedretti.kotlintraining.java.test.screens

import com.example.jpedretti.kotlintraining.java.test.AppiumController
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import io.appium.java_client.pagefactory.iOSFindBy
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class MainScreen(private val driver: AppiumDriver<WebElement>) {

    @iOSFindBy(accessibility = "")
    @AndroidFindBy(id = "greeting")
    private lateinit var greetingView: WebElement

    @iOSFindBy(xpath = "")
    @AndroidFindBy(id = "baseControllerResult")
    private lateinit var controllerResponseView: WebElement

    @iOSFindBy(uiAutomator = "")
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"GOTO DI\")")
    private lateinit var goToDiButton: WebElement

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    fun checkAppIsOpened() {
        assertNotNull(driver.context)
    }

    fun checkGreeting() {
        assertEquals("when 900 years old you reach look as good you will not",
                greetingView.text)
    }

    fun checkControllerResponse() {
        assertEquals("BaseControllerStuff: stuff done: Go to Dagoba",
                controllerResponseView.text)
    }

    fun checkGoToDiButton() {
        TestCase.assertTrue(goToDiButton.isDisplayed && goToDiButton.isEnabled)
    }

    fun clickGoToDiButton() {
        goToDiButton.click()
    }

    fun checkMainScreenIsShowing() =
        if (AppiumController.isAndroid) {
            val activity = (driver as AndroidDriver<WebElement>).currentActivity()
            assertEquals(".activities.MainActivity", activity)
        } else {
            //DO iOS stuff
        }
}