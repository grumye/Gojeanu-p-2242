package org.example.testing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FormTest {

    private static final Logger logger = LogManager.getLogger(FormTest.class);

    public WebDriver driver;

    public static String Url = "https://demoqa.com/";
    public static String FIRST_NAME = "Valeri";
    public static String LAST_NAME = "Gojeanu";
    public static String EMAIL = "gojeanuvaleri@gmail.com";
    public static String GENDER = "Male";
    public static String NUMBER = "6839182200";   // 10 цифр!
    public static String SUB = "en";
    public static String HOBBIES = "Music";
    public static String STATE = "Rajasthan";
    public static String CITY = "Jaipur";

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.getAutoLocalDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void studentFormTest() {

        logger.info("Start test");
        driver.get(Url);

        FormPom formPom = new FormPom(driver);

        formPom.removeAds();
        formPom.clickForms();
        formPom.clickPracticeForm();

        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setNumber(NUMBER);
        formPom.setDateOfBirth("11", "July", "2006");
        formPom.setSubject(SUB);
        formPom.setHobbies(HOBBIES);
        formPom.setState(STATE);
        formPom.setCity(CITY);

        formPom.clickSubmit();
        formPom.waitForModal();   // ждём появление модального окна

        logger.info("Form submitted and modal opened");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                formPom.getFinalData("Student Name"),
                FIRST_NAME + " " + LAST_NAME,
                "Student Name mismatch"
        );

        softAssert.assertEquals(
                formPom.getFinalData("Student Email"),
                EMAIL,
                "Email mismatch"
        );

        softAssert.assertEquals(
                formPom.getFinalData("Gender"),
                GENDER,
                "Gender mismatch"
        );

        softAssert.assertEquals(
                formPom.getFinalData("Mobile"),
                NUMBER,
                "Mobile mismatch"
        );

        softAssert.assertTrue(
                formPom.getFinalData("Date of Birth")
                        .contains("11 July,2006"),
                "Date of Birth mismatch"
        );

        softAssert.assertTrue(
                formPom.getFinalData("Subjects")
                        .toLowerCase()
                        .contains("english"),
                "Subject mismatch"
        );

        softAssert.assertEquals(
                formPom.getFinalData("Hobbies"),
                HOBBIES,
                "Hobbies mismatch"
        );

        softAssert.assertEquals(
                formPom.getFinalData("State and City"),
                STATE + " " + CITY,
                "State and City mismatch"
        );

        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}