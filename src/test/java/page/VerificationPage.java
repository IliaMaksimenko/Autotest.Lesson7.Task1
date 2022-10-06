package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=\"code\"] input");
    private final SelenideElement verifyButton = $("[data-test-id=\"action-verify\"]");
    private final SelenideElement alertInvalidCode = $("[data-test-id=\"error-notification\"] [class=\"notification__content\"]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
    }

    public void findErrorMessage() {
        alertInvalidCode.shouldHave(exactText("Ошибка! Неверно указан код! Попробуйте ещё раз.")).shouldBe(visible);
    }
}
