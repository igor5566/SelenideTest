package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static core.utils.ConfigUtils.*;

public class RepoPage {

    private By settingsBtn = By.xpath("//nav[contains(@class, 'js-repo-nav')]/ul/li[9]/a");

    public SettingsPage clickOnSettingsBtn() {
        $(settingsBtn).waitUntil(Condition.enabled, timeWait).click();
        return page(SettingsPage.class);
    }
}
