package ru.netology.auto;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.auto.data.GenPatern;
import ru.netology.auto.data.RegistrationByCardInfo;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.auto.data.GenPatern.Registration.citiesValid;
import static ru.netology.auto.data.GenPatern.Registration.generateDate;

public class DeliveryCardTest {
    RegistrationByCardInfo info = GenPatern.Registration.generate("ru");



    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessTest() {
        $("[data-test-id='city'] input").setValue(citiesValid());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(4));
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhoneNumber());
        $("[class=checkbox__box]").click();
        $("[class=button__text]").click();
        $(withText("Успешно!")).shouldBe(Condition.appear);
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + generateDate(4)));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generateDate(6));
        $(withText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__title").shouldBe(Condition.appear);
        $(withText("Перепланировать")).click();
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + generateDate(6)));


    }
    @Test
    void emptyCityField() {
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generateDate(4));
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhoneNumber());
        $("[class=checkbox__box]").click();
        $("[class=button__text]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    @Test
    void emptyNameField() {
        $("[data-test-id='city'] input").setValue(citiesValid());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generateDate(4));
        $("[data-test-id='phone'] input").setValue(info.getPhoneNumber());
        $("[class=checkbox__box]").click();
        $("[class=button__text]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    @Test
    void emptyPhoneField() {
        $("[data-test-id='city'] input").setValue(citiesValid());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").sendKeys(generateDate(4));
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[class=checkbox__box]").click();
        $("[class=button__text]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    @Test
    void emptyDateField() {
        $("[data-test-id='city'] input").setValue(citiesValid());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhoneNumber());
        $("[class=checkbox__box]").click();
        $("[class=button__text]").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(Condition.text("Неверно введена дата"));
    }
    @Test
    void citiesNotValid(){
        $("[data-test-id='city'] input").setValue(GenPatern.Registration.citiesNotValid());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(4));
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhoneNumber());
        $("[class=checkbox__box]").click();
        $("[class=button__text]").click();
        $("[data-test-id='city'] .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));

    }
}
