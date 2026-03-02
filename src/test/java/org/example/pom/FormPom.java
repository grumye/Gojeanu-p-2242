package org.example.pom;

import net.bytebuddy.asm.Advice;
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
    @FindBy(id  = "lastName")
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


    @FindBy(id ="subjectsInput")
    WebElement subjectsInput;

    @FindBy(id = "state")
    WebElement state;

    @FindBy(id = "city")
    WebElement city;




    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        PageFactory.initElements(driverParam, this);
    }

    public void setGender(String genderParam) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement gender = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']")
        ));
        gender.click();
    }

    public void setHobbies(String hobbiesParam){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement hobbies = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbiesParam + "']")
        ));
        hobbies.click();
    }


    public void setFirstName(String firstNameParam){
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }


    public void setLastName(String lastNameParam){
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setEmail(String emailParam){
        userEmail.clear();
        userEmail.sendKeys(emailParam);
    }

    public void setNumber(String numberParam){
        userNumber.clear();
        userNumber.sendKeys(numberParam);
    }

    public void setDateOfBirth(String day, String month, String year) {

        dateOfBirthInput.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("react-datepicker")));

        Select yearDropdown = new Select(yearSelect);
        yearDropdown.selectByVisibleText(year);

        Select monthDropdown = new Select(monthSelect);
        monthDropdown.selectByVisibleText(month);

        driver.findElement(By.xpath(
                "//div[contains(@class,'react-datepicker__day') and text()='" + day + "']"
        )).click();
    }

    public void setSubject(String subjectParam){
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
    }
    public String getFinalData(String labelParam) {

        By valueLocator = By.xpath(
                "//td[normalize-space()='" + labelParam + "']/following-sibling::td"
        );

        return utils.explicitWait
                .until(ExpectedConditions.visibilityOfElementLocated(valueLocator))
                .getText();
    }


    public void setState(String stateParam) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", state);

        wait.until(ExpectedConditions.elementToBeClickable(state)).click();
        WebElement stateOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + stateParam + "']")
        ));
        stateOption.click();
    }

    public void setCity(String cityParam) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", city);

        wait.until(ExpectedConditions.elementToBeClickable(city)).click();
        WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + cityParam + "']")
        ));
        cityOption.click();
    }


    public void clickPracticeForm(){
        practiceForm.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
    }

    public void clickForms() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickableForm = wait.until(ExpectedConditions.elementToBeClickable(form));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickableForm);

        try {
            clickableForm.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableForm);
        }
    }

    public void closeAdvert() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}

    }


    public void removeAds() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("document.querySelector('#fixedban')?.remove();");
        js.executeScript("document.querySelector('footer')?.remove();");
    }

    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
