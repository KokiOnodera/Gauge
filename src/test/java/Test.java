import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.Step;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Test {
    static String APPLICATION_URL = "https://google.com";
    static WebDriver webDriver;
    // テスト開始時にWEBドライバを初期化
    @BeforeSuite
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }
    // テスト終了時にWEBドライバを閉じる
    @AfterSuite
    public void closeDriver() {
        webDriver.close();
    }
    @Step("グーグルを開く")
    public void openApplication() {
        webDriver.get(APPLICATION_URL);
    }
    @Step("検索文字<searchText>で検索する")
    public void searchText(String searchText){
        // googleの検索ボックスはName要素にqが設定されている
        WebElement searchBox = webDriver.findElement(By.name("q"));
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.chord(Keys.ENTER));
    }
    @Step("タイトルに<title>が設定されていること")
    public void checkTitle(String title) {
        Assert.assertEquals(title, webDriver.getTitle());
    }
}