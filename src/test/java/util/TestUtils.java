package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtils {

    /**
     * 表示部分をスクリーンショット
     * Users/ダウンロードにキャプチャが保存されます
     * @param WebDriver driver
     * @throws IOException
     */
    public static void screenShot(WebDriver driver) throws IOException{
        String dowloadsPath = System.getProperty("user.home") + "\\Downloads" ;
        LocalDateTime nowDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String filename = "gauge-test-" + dtf.format(nowDate);
        driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        TakesScreenshot ts = (TakesScreenshot) new Augmenter().augment(driver);
        Path from = Paths.get(ts.getScreenshotAs(OutputType.FILE).toURI());
        Path to = Paths.get(dowloadsPath + "\\" + filename + ".png");
        Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * キャプチャ用のフォルダを指定の場所に作成
     *
     * @param dirpath
     * @param dirname
     * @return
     * @throws IOException
     */
    public static String mkdir(String dirpath, String dirname) throws IOException{
        String path = Paths.get(dirpath, dirname).toString();

        if(Files.notExists(Paths.get(dirpath,  dirname))) {
            Files.createDirectories(Paths.get(dirpath, dirname));
        }
        return path;
    }
}