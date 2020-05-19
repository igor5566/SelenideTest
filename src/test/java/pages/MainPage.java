package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static core.utils.ConfigUtils.timeWait;

@Slf4j
public class MainPage {

    private By pullRequestBtn = By.xpath("//a[contains(text(), 'Pull requests')]");
    private By createNewRepoBtn = By.xpath("//div[@id='dashboard-repos-container']/div/h2/a");
    private By searchRepoField = By.xpath("//div[@id='dashboard-repos-container']/div//input");


    public boolean isElementVisible() {
        try {
            $(pullRequestBtn).should().should(Condition.visible);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public CreateRepoPage clickCreateNewRepoBtn() {
        $(createNewRepoBtn).click();
        return page(CreateRepoPage.class);
    }

    public RepoPage typeAndClickRepoByName(String name) {
        $(searchRepoField).val(name);
        $(By.partialLinkText(name)).click();
        return page(RepoPage.class);
    }

    public boolean isRepoURLDisappear(String name) {
        $(searchRepoField).val(name);
        try {
            $(By.partialLinkText(name)).waitUntil(Condition.disappear, timeWait);
            return true;
        } catch (Exception ex) {
            log.error("Repo URL is still in the list");
            return false;
        }
    }
}
