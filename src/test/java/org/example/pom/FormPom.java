package org.example.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.utils.Utils;

import java.time.Duration;

public class FormPom {

    private WebDriver driver;
    private Utils utils;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement form;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userNumber")
    WebElement userNumber;

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    @FindBy(className = "react-datepicker__month-select")
    WebElement monthSelect;

    @FindBy(className = "react-datepicker__year-select")
    WebElement yearSelect;

    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;

    @FindBy(id = "state")
    WebElement state;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "submit")
    WebElement submitButton;

    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        utils = new Utils(driverParam);
        PageFactory.initElements(driverParam, this);
    }

    public void clickForms() {
        utils.explicitWait.until(ExpectedConditions.elementToBeClickable(form));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", form);
    }

    public void clickPracticeForm() {
        utils.explicitWait.until(ExpectedConditions.elementToBeClickable(practiceForm));
        practiceForm.click();
        utils.explicitWait.until(ExpectedConditions.visibilityOf(firstName));
    }

    public void setFirstName(String value) {
        firstName.clear();
        firstName.sendKeys(value);
    }

    public void setLastName(String value) {
        lastName.clear();
        lastName.sendKeys(value);
    }

    public void setEmail(String value) {
        userEmail.clear();
        userEmail.sendKeys(value);
    }

    public void setGender(String genderParam) {
        WebElement gender = utils.explicitWait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']")
                )
        );
        gender.click();
    }

    public void setNumber(String value) {
        userNumber.clear();
        userNumber.sendKeys(value);
    }

    public void setDateOfBirth(String day, String month, String year) {

        dateOfBirthInput.click();

        utils.explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker"))
        );

        new Select(yearSelect).selectByVisibleText(year);
        new Select(monthSelect).selectByVisibleText(month);

        driver.findElement(By.xpath(
                "//div[contains(@class,'react-datepicker__day') and text()='" + day + "']"
        )).click();
    }

    public void setSubject(String twoLetters) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(subjectsInput));

        subjectsInput.click();
        subjectsInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        subjectsInput.sendKeys(Keys.BACK_SPACE);

        subjectsInput.sendKeys(twoLetters);


        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".subjects-auto-complete__menu")
        ));

        subjectsInput.sendKeys(Keys.ENTER);
    }

    public void setHobbies(String hobbiesParam) {

        WebElement hobbies = utils.explicitWait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbiesParam + "']")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", hobbies);

        utils.explicitWait.until(ExpectedConditions.elementToBeClickable(hobbies));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", hobbies);
    }

    public void setState(String stateParam) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", state);
        state.click();

        WebElement option = utils.explicitWait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='" + stateParam + "']")
                )
        );
        option.click();
    }

    public void setCity(String cityParam) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", city);
        city.click();

        WebElement option = utils.explicitWait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='" + cityParam + "']")
                )
        );
        option.click();
    }
    public void waitForModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("example-modal-sizes-title-lg")
        ));
    }

    public void clickSubmit() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", submitButton);

        wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", submitButton);
    }
    public String getFinalData(String label) {
        return utils.explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//td[normalize-space()='" + label + "']/following-sibling::td")
                )
        ).getText();
    }

    public void removeAds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#fixedban')?.remove();");
        js.executeScript("document.querySelector('footer')?.remove();");
    }
}