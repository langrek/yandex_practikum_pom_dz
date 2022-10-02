import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderFormPage;

@RunWith(Parameterized.class)
public class OrderFlowTests {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phone;
    private final String time;
    private final String comment;

    public OrderFlowTests (String name, String surname, String address, String subway, String phone,
                           String time, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phone = phone;
        this.time = time;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Садовая 55", "Сокольники", "+79811234565", "22.03.2022", "Hello"},
                {"Андрей", "Королев", "ул. Крайняя 22", "Лубянка", "+79605749823", "19.05.2022", "World"},
        };
    }

    @Test
    public void orderFlowTest_1() {
        // Инициализуем драйвер
        WebDriverManager.chromedriver().setup(); // Вынести в @Before не получилось, ошибка с установкой драйвера была
        WebDriver driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        // Нажимаем на кнопку "Заказать" из хедера
        mainPage.makeOrderWithHeaderButton();
        OrderFormPage orderFormPage = new OrderFormPage(driver);
        // Заполняем форму
        orderFormPage.fillOrderForm(name, surname, address, subway, phone);
        orderFormPage.clickNext();
        orderFormPage.fillAddInfoForm(time, comment);
        // Подтверждаем заказ
        orderFormPage.confirmOrder();
        // Проверяем, что заказ был создан
        orderFormPage.checkOrderSuccess();
        driver.quit(); //Вынести в @After не получилось, выползала ошибка nullPointer;
    }

    @Test
    public void orderFlowTest_2() {
        WebDriverManager.chromedriver().setup(); // Вынести в @Before не получилось, ошибка с установкой драйвера была
        WebDriver driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        // Нажимаем на кнопку "Заказать" внизу страницы
        mainPage.makeOrderWithHomeButton();
        // Заполняем форму
        OrderFormPage orderFormPage = new OrderFormPage(driver);
        orderFormPage.fillOrderForm(name, surname, address, subway, phone);
        orderFormPage.clickNext();
        orderFormPage.fillAddInfoForm(time, comment);
        // Подтверждаем заказ
        orderFormPage.confirmOrder();
        // Проверяем, что заказ был создан
        orderFormPage.checkOrderSuccess();
        driver.quit(); //Вынести в @After не получилось, выползала ошибка nullPointer;
    }
}
