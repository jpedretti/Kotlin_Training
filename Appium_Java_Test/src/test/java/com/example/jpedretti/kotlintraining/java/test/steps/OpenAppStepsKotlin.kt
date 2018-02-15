package com.example.jpedretti.kotlintraining.java.test.steps

import com.example.jpedretti.kotlintraining.java.test.AppiumController
import com.example.jpedretti.kotlintraining.java.test.screens.MainScreen
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then

class OpenAppStepsKotlin {

    @Given("^The app is opened$")
    fun appIsOpened() =
        MainScreen(AppiumController.getInstance().driver).checkAppIsOpened()

    @Then("^The greeting message should be displayed$")
    fun theGreetingMessageShouldBeDisplayed() =
        MainScreen(AppiumController.getInstance().driver).checkGreeting()

    @And("^The Controller response message should be displayed$")
    fun theControllerResponseMessageShouldBeDisplayed() =
            MainScreen(AppiumController.getInstance().driver).checkControllerResponse()

    @And("^The goto di button should be displayed and enabled$")
    fun theGoToDiButtonShouldBeDisplayedAndEnabled() =
            MainScreen(AppiumController.getInstance().driver).checkGoToDiButton()
}
