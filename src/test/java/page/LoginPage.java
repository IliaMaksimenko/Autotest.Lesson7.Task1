package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement inputLogin = $("[data-test-id=\"login\"] input");
    private final SelenideElement inputPassword = $("[data-test-id=\"password\"] input");
    private final SelenideElement buttonProceed = $("[data-test-id=\"action-login\"]");
    private final SelenideElement alertInvalidPass = $("[data-test-id=\"error-notification\"] [class=\"notification__content\"]");
    private final SelenideElement alertThreefoldPass = $("[data-test-id=\"popup-notification\"] [class=\"notification__content\"]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        makeLogin(info);
        return new VerificationPage();
    }

    public void makeLogin(DataHelper.AuthInfo info){
        inputLogin.setValue(info.getLogin());
        inputPassword.setValue(info.getPassword());
        buttonProceed.click();
    }

    public void findErrorMessage() {
        alertInvalidPass.shouldHave(exactText("Ошибка! Неверно указан логин или пароль")).shouldBe(visible);
    }

    public void invalidThreefoldPassword(DataHelper.AuthInfo info) {
        inputLogin.setValue(info.getLogin());
        inputPassword.setValue(info.getPassword());
        buttonProceed.click();
        inputPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        inputPassword.setValue(info.getPassword());
        buttonProceed.click();
        inputPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        inputPassword.setValue(info.getPassword());
        buttonProceed.click();
    }

    public void alertThreefoldPass() {
        alertThreefoldPass.shouldHave(exactText("Вы превысили лимит попыток ввода. Возможность ввода возобновится через 1 минуту")).shouldBe(visible);
    }
}
