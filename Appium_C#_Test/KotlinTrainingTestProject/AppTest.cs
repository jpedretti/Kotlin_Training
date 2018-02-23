using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Support.UI;

namespace KotlinTrainingTestProject
{
    [TestClass]
    public class AppTest : Capabilities
    {
        [TestMethod]
        public void GoToDi_DoServiceStuff()
        {
            var activity = ((AndroidDriver<IWebElement>)driver).CurrentActivity;
            Assert.AreEqual(".activities.DiAndBindingActivity", activity);

            var appName_text = driver.FindElementById("app_name").Text;
            Assert.AreEqual("KotlinTraining", appName_text);

            driver.FindElementById("do_service_stuff_by_view_model").Click();

            var viewModelResult = driver.FindElementById("view_model_result");
            Assert.IsTrue(viewModelResult.Displayed);
            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
            var resultShown = wait.Until(ExpectedConditions.TextToBePresentInElement(viewModelResult, "May the force be with you!"));
            Assert.IsTrue(resultShown);
        }
    }
}