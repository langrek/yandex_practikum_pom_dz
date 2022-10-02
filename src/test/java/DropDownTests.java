import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.MainPage;

@RunWith(Parameterized.class)
public class DropDownTests {

    private WebDriver driver;
    private int questionNumber;
    private String expectedText;

    public DropDownTests (int questionNumber, String expectedText) {
        this.questionNumber = questionNumber;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто " +
                        "сделать несколько заказов — один за другим."}
        };
    }
    @Test
    public void checkAnswersTextTest() {
        WebDriverManager.chromedriver().setup(); // Вынести в @Before не получилось, ошибка с установкой драйвера была
        WebDriver driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.moveToQuestions();
        mainPage.checkQuestionAnswer(questionNumber, expectedText);
        driver.quit(); //Вынести в @After не получилось, выползала ошибка nullPointer;
    }


}