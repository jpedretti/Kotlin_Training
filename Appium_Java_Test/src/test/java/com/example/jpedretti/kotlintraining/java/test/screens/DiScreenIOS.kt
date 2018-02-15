package com.example.jpedretti.kotlintraining.java.test.screens

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class DiScreenIOS(driver: AppiumDriver<WebElement>) : DiScreen(driver) {

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver), this)
    }

    override fun clickBack() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkDiScreenOpened() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun checkAppName() {
//        //locate view using iOS parameters
//        appNameText = AppiumController.getInstance().driver.findElementById("app_name")
//        super.checkAppName()
//    }
}