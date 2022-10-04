package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDataBase;

public class TestDB {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
//        Configuration.holdBrowserOpen = true;
    }

    @AfterAll
    static void tearDawn() {
        cleanDataBase();
    }

    @Test
    void shouldPassVerification1() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getValidAuthInfo());
        DashboardPage dashboardPage = verificationPage.validVerify(SQLHelper.getVerificationCode());
        dashboardPage.shouldVisible();
    }

    @Test
    void shouldPassVerification2() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getValidOtherAuthInfo());
        DashboardPage dashboardPage = verificationPage.validVerify(SQLHelper.getVerificationCode());
        dashboardPage.shouldVisible();
    }

//    @Test
//    void shouldTrowErrorMessageAfterInvalidPassword() {
//        LoginPage loginPage = new LoginPage();
//        loginPage.invalidAuthInfo(DataHelper.getInvalidAuthInfo());
//        loginPage.findErrorMessage("Ошибка! Неверно указан логин или пароль");
//    }
//
//    @Test
//    void shouldBlockedPageAfterThreefoldInvalidPassword() {
//        LoginPage loginPage = new LoginPage();
//        loginPage.invalidThreefoldPassword(DataHelper.getInvalidAuthInfo());
//        loginPage.findPopUp("Вы превысили лимит попыток ввода. Возможность ввода возобновится через 1 минуту");
//    }
//
//    @Test
//    void shouldTrowErrorAfterInvalidCode() {
//        LoginPage loginPage = new LoginPage();
//        VerificationPage verificationPage = loginPage.validAuthInfo(DataHelper.getValidOtherAuthInfo());
//        verificationPage.invalidVarify(DataHelper.generateFakerCode());
//        verificationPage.findErrorMessage("Ошибка! Неверно указан код! Попробуйте ещё раз.");
//    }

}
