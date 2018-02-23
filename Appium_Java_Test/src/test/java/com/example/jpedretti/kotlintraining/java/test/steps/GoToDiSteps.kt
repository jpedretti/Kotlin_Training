package com.example.jpedretti.kotlintraining.java.test.steps

import com.example.jpedretti.kotlintraining.java.test.AppiumController
import com.example.jpedretti.kotlintraining.java.test.screens.DiScreenAndroid
import com.example.jpedretti.kotlintraining.java.test.screens.DiScreenIOS
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class GoToDiSteps  {

    @Given("^I'm on the Di screen$")
    @Throws(Throwable::class)
    fun iMOnTheDiScreen() {
        getDiScreen().checkDiScreenOpened()
    }

    @Then("^The app name is displayed$")
    fun theAppNameIsDisplayed() =
        getDiScreen().checkAppName()

    @When("^I click the do service stuff button$")
    fun iClickTheDoServiceStuffButton() =
        getDiScreen().clickDoServiceStuffButton()

    @Then("^The service stuff result is displayed$")
    fun theServiceStuffResultIsDisplayed() =
        getDiScreen().checkDoStuffResult()

    private fun getDiScreen() =
            if (AppiumController.isAndroid) {
                DiScreenAndroid(AppiumController.getInstance().driver)
            } else {
                DiScreenIOS(AppiumController.getInstance().driver)
            }
}
