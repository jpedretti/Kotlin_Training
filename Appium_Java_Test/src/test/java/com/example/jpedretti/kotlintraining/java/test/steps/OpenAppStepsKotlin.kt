package com.example.jpedretti.kotlintraining.java.test.steps

import com.example.jpedretti.kotlintraining.java.test.AppTestKotlin
import com.example.jpedretti.kotlintraining.java.test.Capabilities
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then

class OpenAppStepsKotlin : Capabilities() {

    @Given("^The app is opened$")
    fun appIsOpened() {
        setDeviceCapabilities()
        AppTestKotlin.checkAppIsOpened(driver)
    }

    @Then("^The greeting message should be displayed$")
    fun theGreetingMessageShouldBeDisplayed() =
        AppTestKotlin.checkGreeting(driver)

    @And("^The Controller response message should be displayed$")
    fun theControllerResponseMessageShouldBeDisplayed() =
        AppTestKotlin.checkControllerResponse(driver)

    @And("^The goto di button should be displayed and enabled$")
    fun theGoToDiButtonShouldBeDisplayedAndEnabled() =
        AppTestKotlin.checkGoToDiButton(driver)
}
