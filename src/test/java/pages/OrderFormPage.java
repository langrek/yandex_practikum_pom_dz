package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderFormPage {

    private WebDriver driver;

    // Поле Имя
    private By nameField = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    // Поле Фамилия
    private By surnameField = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    // Поле Адрес
    private By addressField = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    // Поле Станция метро
    private By subwayField = By.xpath(".//input[contains(@placeholder, 'Станция метро')]");
    // Первый вариант в дроп дауне после поиска
    private By subwaySelectSearch = By.cssSelector("li[data-index='0']");
    // Поле Телефон
    private By phoneField = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    // Кнопка Далее
    private By nextButton = By.xpath(".//button[text() = 'Далее']");
    // Поле Когда привезти самокат
    private By timeField = By.xpath(".//input[contains(@placeholder, 'Когда привезти')]");
    // Дроп даун Срок аренды
    private By periodDropDown = By.xpath(".//div[contains(text(), 'Срок аренды')]");
    // Опция дроп дауна сутки
    private By dayPeriodOption = By.xpath(".//div[contains(text(), 'сутки')]");
    // Чек бокс цвет самоката - черный жемчуг
    private By blackColorCheckBox = By.xpath(".//label");
    // Поле Коммантарий
    private By commentField = By.xpath(".//input[contains(@placeholder, 'Комментарий')]");
    // Кнопка Заказать
    private By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text() = 'Заказать']");
    // Кнопка Да после оформления заказа
    private By confirmOrderButton = By.xpath(".//button[text() = 'Да']");
    // Элемент с текстом об успешном заказе
    private By successfulMessage = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
    }
    // Заполнение формы
    public void fillOrderForm(String name, String surname, String address, String subway, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(subwayField).sendKeys(subway);
        driver.findElement(subwaySelectSearch).click();
        driver.findElement(phoneField).sendKeys(phone);
    }
    // Нажатаие на кнопку Далее
    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    // Заполняем дополнительную инфомрацию о заказе
    public void fillAddInfoForm(String time, String comment) {
        driver.findElement(timeField).sendKeys(time);
        driver.findElement(timeField).sendKeys(Keys.ENTER);
        driver.findElement(periodDropDown).click();
        driver.findElement(dayPeriodOption).click();
        driver.findElement(blackColorCheckBox).click();
        driver.findElement(commentField).sendKeys(comment);
    }
    // Подтверждаем заказ
    public void confirmOrder(){
        driver.findElement(orderButton).click();
        driver.findElement(confirmOrderButton).click();
    }
    // Проверяем, что заказ создался
    public void checkOrderSuccess(){
        List<WebElement> message = driver.findElements(successfulMessage);
        Assert.assertEquals(1, message.size());
    }
}
