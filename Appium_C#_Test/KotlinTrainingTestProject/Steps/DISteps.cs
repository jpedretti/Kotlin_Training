using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Support.UI;
using System;
using TechTalk.SpecFlow;

namespace KotlinTrainingTestProject
{
    [Binding]
    public class DISteps : Capabilities
    {
        [Given(@"I'm on the Di screen")]
        public void GivenIMOnTheDiScreen()
        {
            GoToDiActivity();
        }

        [When(@"I click the do service stuff button")]
        public void WhenIClickTheDoServiceStuffButton()
        {
            driver.FindElementById("do_service_stuff_by_view_model").Click();
        }

        [When(@"I press the back button")]
        public void WhenIPressTheBackButton()
        {
            ((AndroidDriver<IWebElement>)driver).PressKeyCode(AndroidKeyCode.Back);
        }

        [Then(@"The app name is displayed")]
        public void ThenTheAppNameIsDisplayed()
        {
            var appName_text = driver.FindElementById("app_name").Text;
            Assert.AreEqual("KotlinTraining", appName_text);
        }

        [Then(@"The service stuff result is displayed")]
        public void ThenTheServiceStuffResultIsDisplayed()
        {
            var viewModelResult = driver.FindElementById("view_model_result");
            Assert.IsTrue(viewModelResult.Displayed);
            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
            var resultShown = wait.Until(ExpectedConditions.TextToBePresentInElement(viewModelResult, "finished doing service stuff"));
            Assert.IsTrue(resultShown);
        }

        [Then(@"I should back to Main screen")]
        public void ThenIShouldBackToMainScreen()
        {
            var activity = ((AndroidDriver<IWebElement>)driver).CurrentActivity;
            Assert.AreEqual(".activities.MainActivity", activity);
        }

        void GoToDiActivity()
        {
            driver.FindElement(ByText("goto DI")).Click();
            var activity = ((AndroidDriver<IWebElement>)driver).CurrentActivity;
            Assert.AreEqual(".activities.DiAndBindingActivity", activity);
        }
    }
}
