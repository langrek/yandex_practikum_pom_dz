package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {

    private WebDriver driver;
    private String url = "https://qa-scooter.praktikum-services.ru/";

    // Надпись "Вопросы о важном"
    private By questionsText = By.xpath(".//div[text()='Вопросы о важном']");
    // Вопросы
    private By questionsDropDown = By.xpath(".//div[contains(@class, 'accordion__item')]/div/div");
    // Ответы
    private By answersDropDown = By.xpath(".//div[contains(@class, 'accordion__item')]/div/p");
    // Кнопка заказать из хедера
    private By orderHeaderButton = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[text()='Заказать']");
    // Кнопка заказать внизу страницы
    private By orderHomeButton = By.xpath(".//div[contains(@class, 'Home_Finish')]/button[text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }
    // Сделать заказ через кнопку из хедера
    public void makeOrderWithHeaderButton() {
        driver.findElement(orderHeaderButton).click();
    }
    // Сделать заказ через кнопку внизу страницы
    public void makeOrderWithHomeButton() {
        WebElement element = driver.findElement(orderHomeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
    // Скролл к вопросам
    public void moveToQuestions() {
        WebElement element = driver.findElement(questionsText);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
    // Проверка, что ответ на вопрос соответсвует ожидаемому результату
    public void checkQuestionAnswer(int questionNumber, String expectedText) {
        List<WebElement> questions = driver.findElements(questionsDropDown);
        questions.get(questionNumber-1).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.numberOfElementsToBe(answersDropDown, 8));
        List<WebElement> answers = driver.findElements(answersDropDown);
        String answerText = answers.get(questionNumber-1).getText();
        Assert.assertEquals("Текст ответа не совпадает с ожидаемым значением", expectedText, answerText);
    }
}

