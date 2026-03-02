package org.example.testing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FormTest {

    private static final Logger logger = LogManager.getLogger(FormTest.class);
    public static WebDriver driver;
    public static String Url="https://demoqa.com/";
    public static String FIRST_NAME="Anatolie";
    public static String LAST_NAME="Sneg";
    public static String EMAIL="email@example.com";
    public static String GENDER ="Male";
    public static String NUMBER = "0068333666";
    public static String SUB = "informatica";
    public static String HOBBIES = "Music";
    public static String STATE ="Rajasthan";
    public static String CITY ="Jaipur";






    @BeforeMethod
    public void beforeMethod(){
        driver = Driver.getAutoLocalDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void  studentFormTest(){


        logger.info("Start test");
        driver.get(Url);

        FormPom formPom = new FormPom(driver);
        formPom.removeAds();
        formPom.pause(1000);
        formPom.clickForms();
        formPom.pause(1000);
        formPom.clickPracticeForm();
        formPom.setFirstName(FIRST_NAME);
        logger.info("Set first name");

        formPom.setLastName(LAST_NAME);
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        logger.info("Set gender");
        formPom.setNumber(NUMBER);
        formPom.setDateOfBirth("11", "July", "2006");
        formPom.setSubject(SUB);
        formPom.setHobbies(HOBBIES);
        formPom.setState(STATE);
        logger.info("Set state");
        formPom.setCity(CITY);




        System.out.println("Finish Test");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
