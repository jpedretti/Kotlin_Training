using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Support.UI;

namespace UnitTestProject1
{
    [TestClass]
    public class AppTest
    {
        private AppiumDriver<IWebElement> driver;

        [TestInitialize]
        public void Initialyze()
        {
            var cap = new DesiredCapabilities();
            cap.SetCapability("deviceName", "pixel_api_25");
            cap.SetCapability("appActivity", "activities.MainActivity");
            cap.SetCapability("appPackage", "com.example.jpedretti.kotlintraining");
            cap.SetCapability("clearSystemFiles", true);

            driver = new AndroidDriver<IWebElement>(new Uri("http://127.0.0.1:4723/wd/hub"), cap);
        }

        [TestCleanup]
        public void CleanUp()
        {
            driver.CloseApp();
        }

        [TestMethod]
        public void OpenApp()
        {
            Assert.IsNotNull(driver.Context);

            var greetingTextView = driver.FindElementById("greeting");
            Assert.AreEqual("when 900 years old you reach look as good you will not", greetingTextView.Text);

            var controllerResponseTextView = driver.FindElementById("baseControllerResult");
            Assert.AreEqual("BaseControllerStuff: stuff done: Go to Dagoba", controllerResponseTextView.Text);

            var goToDIButton = driver.FindElement(ByText("goto DI"));
            Assert.IsTrue(goToDIButton.Displayed && goToDIButton.Enabled);

            //IReadOnlyCollection<IWebElement> elements = driver.FindElementById("recycler_view")
            //    .FindElements(By.ClassName("android.widget.RelativeLayout"));
        }

        [TestMethod]
        public void GoToDi_DoServiceStuff()
        {
            GoToDiActivity();

            var appName_text = driver.FindElementById("app_name").Text;
            Assert.AreEqual("KotlinTraining", appName_text);

            driver.FindElementById("do_service_stuff_by_view_model").Click();

            var viewModelResult = driver.FindElementById("view_model_result");
            Assert.IsTrue(viewModelResult.Displayed);
            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
            var resultShown = wait.Until(ExpectedConditions.TextToBePresentInElement(viewModelResult, "finished doing service stuff"));
            Assert.IsTrue(resultShown);
        }

        [TestMethod]
        public void GoToDi_PressBack()
        {
            GoToDiActivity();
            ((AndroidDriver<IWebElement>)driver).PressKeyCode(AndroidKeyCode.Back);
            var activity = ((AndroidDriver<IWebElement>)driver).CurrentActivity;
            Assert.AreEqual(".activities.MainActivity", activity);
        }

        void GoToDiActivity()
        {
            driver.FindElement(ByText("goto DI")).Click();
            var activity = ((AndroidDriver<IWebElement>)driver).CurrentActivity;
            Assert.AreEqual(".activities.DiAndBindingActivity", activity);
        }

        static By ByText(string text) => new ByAndroidUIAutomator($"new UiSelector().textContains(\"{text}\")");
    }
}