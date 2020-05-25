package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;

import java.util.Date;

import static core.utils.ConfigUtils.driverName;
import static core.utils.ConfigUtils.timeWait;

@Slf4j
public class BaseTest {

    protected String uniqueId = "";

    @BeforeSuite
    public void initiate() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browser = driverName;
        Configuration.baseUrl = "https://github.com";
        Configuration.startMaximized = true;
        Configuration.headless = true; //for jenkins
        Configuration.timeout = timeWait;
        uniqueId = setUniqueId();
    }

    private String setUniqueId() {
        return new Date().getTime() + "";
    }
}
