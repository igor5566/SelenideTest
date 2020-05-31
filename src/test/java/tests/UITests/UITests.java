package tests.UITests;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static core.ConfigManager.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class UITests extends BaseTest {

    private String repoName = "Test";
    private LoginPage loginPage;
    private MainPage mainPage;
    private CreateRepoPage createRepoPage;
    private NewCreatedRepoPage newCreatedRepoPage;
    private RepoPage repoPage;
    private SettingsPage settingsPage;

    @AfterMethod
    public void clearUp() {
        clearBrowserCookies();
        clearBrowserCache();
    }

    @Test(description = "Verify Home page is opened.")
    public void loginTest() {
        loginPage = open("/login", LoginPage.class);
        mainPage = loginPage.logIn(email, pass);
        assertThat(mainPage.isElementVisible()).as("Cannot login into the account.").isTrue();
    }

    @Test(description = "Verify creating new repository.", priority = 1)
    public void creatingRepoTest() {
        loginPage = open("/login", LoginPage.class);
        mainPage = loginPage.logIn(email, pass);
        createRepoPage = mainPage.clickCreateNewRepoBtn();
        newCreatedRepoPage = createRepoPage.createNewRepoByUI(repoName + uniqueId);
        assertThat(newCreatedRepoPage.checkCreatedRepoName(repoName + uniqueId)).as("Repo name isn't the same").isTrue();
    }

    @Test(description = "Delete repository.", priority = 2)
    public void deleteRepoTest() {
        loginPage = open("/login", LoginPage.class);
        mainPage = loginPage.logIn(email, pass);
        repoPage = mainPage.typeAndClickRepoByName(uniqueId);
        settingsPage = repoPage.clickOnSettingsBtn();
        settingsPage.deleteRepoByUI(userID + "/" + repoName + uniqueId);
        assertThat(mainPage.isRepoURLDisappear(repoName + uniqueId)).as("Repo is still in the list.").isTrue();
    }
}
