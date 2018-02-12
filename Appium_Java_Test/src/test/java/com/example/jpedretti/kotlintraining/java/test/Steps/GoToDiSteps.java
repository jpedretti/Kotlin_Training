package com.example.jpedretti.kotlintraining.java.test.Steps;

import com.example.jpedretti.kotlintraining.java.test.AppTestKotlin;
import com.example.jpedretti.kotlintraining.java.test.Capabilities;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoToDiSteps extends Capabilities{
    @Given("^I'm on the Di screen$")
    public void iMOnTheDiScreen() throws Throwable {
        setDeviceCapabilities();
        AppTestKotlin.Companion.goToDiActivity(driver);
    }

    @Then("^The app name is displayed$")
    public void theAppNameIsDisplayed() {
        AppTestKotlin.Companion.checkAppName(driver);
    }

    @When("^I click the do service stuff button$")
    public void iClickTheDoServiceStuffButton() {
        AppTestKotlin.Companion.clickDoStuffButton(driver);
    }

    @Then("^The service stuff result is displayed$")
    public void theServiceStuffResultIsDisplayed() {
        AppTestKotlin.Companion.checkDoStuffResult(driver);
    }

    @When("^I press the back button$")
    public void iPressTheBackButton() {
        AppTestKotlin.Companion.clickBackButton(driver);
    }

    @Then("^I should back to Main screen$")
    public void iShouldBackToMainScreen() {
        AppTestKotlin.Companion.checkMainActivityIsOpened(driver);
    }
}
