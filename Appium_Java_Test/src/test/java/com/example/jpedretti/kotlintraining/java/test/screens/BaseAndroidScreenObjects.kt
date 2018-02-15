package com.example.jpedretti.kotlintraining.java.test.screens

import com.example.jpedretti.kotlintraining.java.test.AppiumController
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidKeyCode
import org.openqa.selenium.WebElement

class BaseAndroidScreenObjects {
    fun clickBackButton() {
        val driver = AppiumController.getInstance().driver
        (driver as AndroidDriver<WebElement>).pressKeyCode(AndroidKeyCode.BACK)
    }
}