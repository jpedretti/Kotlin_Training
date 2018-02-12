package com.example.jpedretti.kotlintraining.java.test.Steps

import com.example.jpedretti.kotlintraining.java.test.AppTestKotlin
import com.example.jpedretti.kotlintraining.java.test.Capabilities
import cucumber.api.PendingException
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class GoToDiSteps : Capabilities() {
    @Given("^I'm on the Di screen$")
    @Throws(Throwable::class)
    fun iMOnTheDiScreen() {
        setDeviceCapabilities()
        AppTestKotlin.goToDiActivity(driver)
    }

    @Then("^The app name is displayed$")
    fun theAppNameIsDisplayed() =
        AppTestKotlin.checkAppName(driver)

    @When("^I click the do service stuff button$")
    fun iClickTheDoServiceStuffButton() =
        AppTestKotlin.clickDoStuffButton(driver)

    @Then("^The service stuff result is displayed$")
    fun theServiceStuffResultIsDisplayed() =
        AppTestKotlin.checkDoStuffResult(driver)

    @When("^I press the back button$")
    fun iPressTheBackButton() =
        AppTestKotlin.clickBackButton(driver)

    @Then("^I should back to Main screen$")
    fun iShouldBackToMainScreen() =
        AppTestKotlin.checkMainActivityIsOpened(driver)
}
