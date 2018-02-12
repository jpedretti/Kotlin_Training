package com.example.jpedretti.kotlintraining.java.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import junit.framework.TestCase;
import java.net.URL;

public class BaseDriver extends TestCase {
    public AppiumDriver<WebElement> driver;

    public BaseDriver( String testName )
    {
        super( testName );
    }

    @Before
    public void setUp() throws Exception {
        // File classpathRoot = new File(System.getProperty("user.dir"));
        // File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
        // File app = new File(appDir.getCanonicalPath(), "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","pixel_api_25");
        // capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.jpedretti.kotlintraining");
        capabilities.setCapability("appActivity", ".activities.MainActivity");
        capabilities.setCapability("clearSystemFiles", true);
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}