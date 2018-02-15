package com.example.jpedretti.kotlintraining.java.test.screens

import com.example.jpedretti.kotlintraining.java.test.AppiumController
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import junit.framework.TestCase
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class DiScreenAndroid(driver: AppiumDriver<WebElement>) : DiScreen(driver) {

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    private val androidScreenObjects = BaseAndroidScreenObjects()

    override fun checkDiScreenOpened() {
        val activity =
                (AppiumController.getInstance().driver as AndroidDriver<WebElement>)
                        .currentActivity()
        TestCase.assertEquals(".activities.DiAndBindingActivity", activity)
    }

    override fun clickBack() {
        androidScreenObjects.clickBackButton()
    }
}