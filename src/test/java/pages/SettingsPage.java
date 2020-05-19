package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static core.utils.ConfigUtils.*;

public class SettingsPage {

    private By deleteBtn = By.xpath("//div[@class='Box Box--danger']/ul/li[4]//summary");
    private By deleteConfirmField = By.xpath("//*[@aria-label='Delete repository']//input[@class='form-control input-block']");
    private By confirmBtn = By.xpath("//*[@aria-label='Delete repository']//form//button");
    private By fullRepoNameFromDeleteWindow = By.xpath("//*[@aria-label='Delete repository']//strong[2]");

    public void deleteRepoByUI(String name) {
        $(deleteBtn).scrollTo().waitUntil(Condition.enabled, timeWait).click();
        $(deleteConfirmField).val(name);
        $(confirmBtn).waitUntil(Condition.enabled, timeWait).click();
    }
}
