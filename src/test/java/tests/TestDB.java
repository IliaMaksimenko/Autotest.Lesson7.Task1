package tests;

import com.codeborne.selenide.Selenide;
import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDataBase;

public class TestDB {

    @AfterAll
    static void tearDawn() {
        Selenide.clearBrowserCookies();
        cleanDataBase();
    }

    @Test
    void shouldPassVerification() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.shouldVisible();
    }

    @Test
    void shouldPassVerificationOtherUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidOtherAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.shouldVisible();
    }

    @Test
    void shouldTrowErrorMessageAfterInvalidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getInvalidAuthInfo();
        loginPage.makeLogin(authInfo);
        loginPage.findErrorMessage();
    }

    @Test
    void shouldBlockedPageAfterThreefoldInvalidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidThreefoldPassword(authInfo);
        loginPage.alertThreefoldPass();
    }

    @Test
    void shouldTrowErrorAfterInvalidCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.generateFakerCode();
        verificationPage.validVerify(verificationCode);
    }

}
