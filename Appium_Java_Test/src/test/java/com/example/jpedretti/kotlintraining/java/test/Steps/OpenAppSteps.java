package com.example.jpedretti.kotlintraining.java.test.Steps;

import com.example.jpedretti.kotlintraining.java.test.AppTestKotlin;
import com.example.jpedretti.kotlintraining.java.test.Capabilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class OpenAppSteps extends Capabilities{

    @Given("^The app is opened$")
    public void appIsOpened() {
        setDeviceCapabilities();
        AppTestKotlin.Companion.checkAppIsOpened(driver);
    }

    @Then("^The greeting message should be displayed$")
    public void theGreetingMessageShouldBeDisplayed() {
        AppTestKotlin.Companion.checkGreeting(driver);
    }

    @And("^The Controller response message should be displayed$")
    public void theControllerResponseMessageShouldBeDisplayed() {
        AppTestKotlin.Companion.checkControllerResponse(driver);
    }

    @And("^The goto di button should be displayed and enabled$")
    public void theGoToDiButtonShouldBeDisplayedAndEnabled() {
        AppTestKotlin.Companion.checkGoToDiButton(driver);
    }
}
