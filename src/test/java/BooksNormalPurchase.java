import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.Step;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import util.TestUtils;

public class BooksNormalPurchase{
    static String BASKET_URL = "https://books.step.rakuten.co.jp/rms/mall/book/bs/Cart?shop_bid=213310&item_id=20971294&units=1";
    static String capturePath;
    static WebDriver webDriver;


    // テスト開始時にWEBドライバを初期化
    @BeforeSuite
    public void initDriver(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }
    // WEBドライバを閉じる
    @AfterSuite
    public void closeDriver(){
        webDriver.close();
    }
    @Step("任意の商品をかごに入れる")
    public void openApplication() {
        webDriver.get(BASKET_URL);
    }
    @Step("購入手続きを押下")
    public void clickPurchase(){
        // 買い物かごの購入手続きボタンのid要素からelementを取得
        WebElement element = webDriver.findElement(By.id("js-cartBtn"));
        element.click();
    }
    @Step("<userId><password>を入力し、「次へ」を押下し、Step4に遷移する")
    public void inputUserId(String userId, String pass) {
        //要素を見つけるまで待機
        webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement idForm = webDriver.findElement(By.name("username"));
        idForm.sendKeys(userId);
        idForm.sendKeys(Keys.chord(Keys.ENTER));
        webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement passForm = webDriver.findElement(By.name("password"));
        passForm.sendKeys(pass);
        passForm.sendKeys(Keys.chord(Keys.ENTER));
    }

    @Step("キャプチャを取得")
    public void capture() throws IOException, InterruptedException {
        // 画面表示まで１秒待つ
        // TODO　将来的には画面表示されるまで待つように修正する
        long millSec = 1000;
        Thread.sleep(millSec);

        // 画面をキャプチャする
        TestUtils.screenShot(webDriver);
    }
}