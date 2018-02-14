package com.example.jpedretti.kotlintraining.java.test

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src\\test\\java\\com\\example\\jpedretti\\kotlintraining\\java\\test\\feature\\Start.feature",
                    "src\\test\\java\\com\\example\\jpedretti\\kotlintraining\\java\\test\\feature\\di.feature"],
        glue = ["com.example.jpedretti.kotlintraining.java.test"],
        format = ["html:C:\\Users\\jpedretti\\Source\\Repos\\Kotlin_Training\\Appium_Java_Test\\cucumberOutput"])
class AppTestCucumberKotlin
