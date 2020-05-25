package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NewCreatedRepoPage {

    private By createdRepoName = By.xpath("//div[@class='flex-auto min-width-0 width-fit mr-3']//strong/a");

    public boolean checkCreatedRepoName(String repoName) {
        String actualName = $(createdRepoName).getText();
        return actualName.equals(repoName);
    }
}
