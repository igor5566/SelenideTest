package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static core.ConfigManager.timeWait;

public class CreateRepoPage {

    private By repoNameField = By.xpath("//input[contains(@class, 'form-control js-repo-name')]");
    private By createBtn = By.xpath("//button[@class='btn btn-primary first-in-line']");

    public NewCreatedRepoPage createNewRepoByUI(String repoName) {
            $(repoNameField).val(repoName);
            $(createBtn).waitUntil(Condition.enabled, timeWait).click();
            return page(NewCreatedRepoPage.class);
    }
}
