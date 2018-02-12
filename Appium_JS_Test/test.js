/* jshint node: true */
"use strict";

require("./helpers/setup");

var wd = require("wd");
var packageName = "com.example.jpedretti.kotlintraining";

describe("Test Kotlin Training App", function () {
    this.timeout(300000);
    var driver;
    var opts = {
        platformName: "Android",
        //platformVersion: "8.0",
        deviceName: "emulator-5554",
        appActivity: ".activities.MainActivity",
        appPackage: packageName,
        clearSystemFiles: true,
        //app: "/path/to/the/downloaded/ApiDemos.apk",
        automationName: "UiAutomator2"
    };

    var serverConfig = {
        host: 'localhost',
        port: 4723
    };

    before(function () {
        driver = wd.promiseChainRemote(serverConfig);
        return require("./helpers/logging").configure(driver);
    });

    beforeEach(function () {
        return driver
            .init(opts)
            .setImplicitWaitTimeout(5000);
    });

    afterEach(function () {
        return driver.quit();
    });

    it("should go to DI and press back", function () {
        return driver
            .elementByAndroidUIAutomator(byText("GOTO DI"))
            .should.eventually.exist
            .click()
            .getCurrentActivity().should.eventually.equal(".activities.DiAndBindingActivity")
            .back()
            .getCurrentActivity().should.eventually.equal(".activities.MainActivity");
    });

    it("should go to DI and and do service stuff", function () {
        var asserters = wd.asserters;
        return driver
            .elementByAndroidUIAutomator(byText("GOTO DI"))
            .should.eventually.exist
            .click()
            .getCurrentActivity().should.eventually.equal(".activities.DiAndBindingActivity")
            .elementByAndroidUIAutomator(byId("app_name")).should.eventually.exist
            .text().should.eventually.equal('KotlinTraining')
            .elementByAndroidUIAutomator(byId("do_service_stuff_by_view_model")).should.eventually.exist
            .click()
            .waitForElementById("view_model_result", asserters.nonEmptyText, 6000, 100)
            .then(function (element) {
                return element.text().should.eventually.equal('finished doing service stuff');
            });

        //outra possibilidade
        // var until = webdriver.until;
        // var by = webdriver.By;
        // driver.wait(until.elementLocated(by.id('elementappearschild')), 10000, 'Could not locate the child element within the time specified');
        // driver.findElement(by.id('elementappearschild')).getText().then(function (text) {
        //     assert.equal(text, 'Boo!');
        // });
    });

    it("should open app", function () {
        return driver
            .elementByAndroidUIAutomator(byId("greeting"))
            .should.eventually.exist
            .text().should.eventually.equal('when 900 years old you reach look as good you will not');
    });
});

function byId(id) {
    return 'new UiSelector().resourceId("'.concat(packageName, ":id/", id, '")');
}

function byText(text) {
    return 'new UiSelector().text("'.concat(text, '")');
}