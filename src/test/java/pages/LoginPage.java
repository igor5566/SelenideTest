package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private SelenideElement loginField = $(By.xpath("//input[@name='login']"));
    private SelenideElement passwordField = $(By.xpath("//input[@name='password']"));

    public MainPage logIn(String email, String pass) {
        loginField.val(email);
        passwordField.val(pass).pressEnter();
        return page(MainPage.class);
    }

}
