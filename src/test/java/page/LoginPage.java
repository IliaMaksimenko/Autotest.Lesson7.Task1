package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement inputLogin = $("[data-test-id=\"login\"] input");
    private final SelenideElement inputPassword = $("[data-test-id=\"password\"] input");
    private final SelenideElement buttonProceed = $("[data-test-id=\"action-login\"]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        makeLogin(info);
        return new VerificationPage();
    }

    public void makeLogin(DataHelper.AuthInfo info){
        inputLogin.setValue(info.getLogin());
        inputPassword.setValue(info.getPassword());
        buttonProceed.click();
    }
}
