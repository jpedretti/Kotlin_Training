package com.example.jpedretti.kotlintraining.java.test.hook

import com.example.jpedretti.kotlintraining.java.test.AppiumController
import cucumber.api.java.After
import cucumber.api.java.Before

class CucumberHook {

    @Before
    fun init() {
        val platform = System.getenv("PLATFORM")
        when (platform) {
            "ios" -> AppiumController.executionOS = AppiumController.OS.IOS
            "android" -> AppiumController.executionOS = AppiumController.OS.ANDROID
            else -> throw Exception("Unable to read device platform.")
        }
        AppiumController.getInstance().start()
    }

    @After
    fun tearDown() {
        AppiumController.getInstance().tearDown()
    }
}