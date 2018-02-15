package com.example.jpedretti.kotlintraining.java.test

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.concurrent.TimeUnit

open class AppiumController {

    lateinit var driver: AppiumDriver<WebElement>

    fun start() {
        driver = when(executionOS){
            OS.ANDROID -> {
                val capabilities = createAndroidCapability()
                AndroidDriver(URL(serverUrl), capabilities)
            }
            OS.IOS -> {
                val capabilities = createIosCapability()
                IOSDriver(URL(serverUrl), capabilities)
            }
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)
    }

    private fun createIosCapability(): Capabilities {
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("platformName", "ios")
        capabilities.setCapability("deviceName", "=iPhone 5s")
        capabilities.setCapability("app", "app path")
        return capabilities
    }

    private fun createAndroidCapability() : Capabilities {
        // File classpathRoot = new File(System.getProperty("user.dir"));
        // File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
        // File app = new File(appDir.getCanonicalPath(), "ApiDemos-debug.apk");
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("deviceName", "pixel_api_25")
        // capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.jpedretti.kotlintraining")
        capabilities.setCapability("appActivity", ".activities.MainActivity")
        capabilities.setCapability("clearSystemFiles", true)
        return capabilities
        //driver = AndroidDriver(URL("http://localhost:4723/wd/hub"), capabilities)
    }

    fun tearDown() {
        driver.quit()
    }

    companion object {
        private var me : AppiumController? = null
        private const val serverUrl = "http://localhost:4723/wd/hub"

        var executionOS = OS.ANDROID
        fun getInstance() : AppiumController {
            if (me == null) {
                me = AppiumController()
            }

            return me!!
        }

        var isAndroid = executionOS == OS.ANDROID
    }

    enum class OS {
        ANDROID,
        IOS
    }
}