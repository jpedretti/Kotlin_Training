package com.example.jpedretti.kotlintraining.java.test

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import org.junit.After
import org.junit.Before
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import junit.framework.TestCase
import java.net.URL

open class BaseDriver(testName: String) : TestCase(testName) {
    lateinit var driver: AppiumDriver<WebElement>

    @Before
    public override fun setUp() {
        // File classpathRoot = new File(System.getProperty("user.dir"));
        // File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
        // File app = new File(appDir.getCanonicalPath(), "ApiDemos-debug.apk");
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("deviceName", "pixel_api_25")
        // capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.jpedretti.kotlintraining")
        capabilities.setCapability("appActivity", ".activities.MainActivity")
        capabilities.setCapability("clearSystemFiles", true)
        driver = AndroidDriver(URL("http://localhost:4723/wd/hub"), capabilities)
    }

    @After
    public override fun tearDown() {
        driver.quit()
    }
}