using NUnit.Framework;
using TechTalk.SpecFlow;

namespace KotlinTrainingTestProject
{
    [Binding]
    public class OpenAppSteps : Capabilities
    {
        [Given(@"The app is opened")]
        public void GivenTheAppIsOpened()
        {
            Assert.IsNotNull(driver.Context);
        }

        [Then(@"The greeting message should be displayed")]
        public void ThenTheGreetingMessageShouldBeDisplayed()
        {
            var greetingTextView = driver.FindElementById("greeting");
            Assert.AreEqual("when 900 years old you reach look as good you will not", greetingTextView.Text);
        }

        [Then(@"The Controller response message should be displayed")]
        public void ThenTheControllerResponseMessageShouldBeDisplayed()
        {
            var controllerResponseTextView = driver.FindElementById("baseControllerResult");
            Assert.AreEqual("BaseControllerStuff: stuff done: Go to Dagoba", controllerResponseTextView.Text);
        }

        [Then(@"The goto di button should be displayed and enabled")]
        public void ThenTheGotoDiButtonShouldBeDisplayedAndEnabled()
        {
            var goToDIButton = driver.FindElement(ByText("goto DI"));
            Assert.IsTrue(goToDIButton.Displayed && goToDIButton.Enabled);
        }
    }
}
