using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Remote;
using System;
using TechTalk.SpecFlow;

namespace KotlinTrainingTestProject
{
    public abstract class Capabilities
    {
        protected static AppiumDriver<IWebElement> driver;

        [BeforeScenario]
        [TestInitialize]
        public void Initialyze()
        {
            GetCapabilities();
        }

        [TestCleanup]
        [AfterScenario]
        public void AfterTestRun()
        {
            driver.Quit();
        }

        protected static void GetCapabilities()
        {
            var cap = new DesiredCapabilities();
            cap.SetCapability("deviceName", "pixel_api_25");
            cap.SetCapability("appActivity", "activities.MainActivity");
            cap.SetCapability("appPackage", "com.example.jpedretti.kotlintraining");
            cap.SetCapability("clearSystemFiles", true);

            driver = new AndroidDriver<IWebElement>(new Uri("http://127.0.0.1:4723/wd/hub"), cap);
        }

        protected static By ByText(string text) => new ByAndroidUIAutomator($"new UiSelector().textContains(\"{text}\")");
    }
}
