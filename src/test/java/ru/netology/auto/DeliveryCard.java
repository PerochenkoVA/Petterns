package ru.netology.auto;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCard {

    LocalDate date = LocalDate.now().plusDays(5);
    LocalDate date1 = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Faker faker;

    String generateDate(int days, String datePattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(datePattern));
    }

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @Test
    void taskOne() {
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().subscriberNumber(10);
        String cardNumber = faker.finance().creditCard(CreditCardType.VISA);
        String city = faker.address().city();
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(city); //город
        $("[data-test-id=date] input").doubleClick(); //очитка поля дата
        $("[data-test-id=date] input").sendKeys(" "); //дата
        $("[data-test-id=date] input").sendKeys(formatter.format(date)); //дата
        $("[data-test-id=name] input").setValue(name); // FIO
        $("[data-test-id=phone] input").setValue("+7" + phone); // telefon number
        $("[data-test-id=agreement]").click(); //согласие
        $("[class='button__content']").click(); //Продолжить
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(50));

    }

    @Test
    void taskTwo() {

        String name = faker.name().fullName();
        String phone = faker.phoneNumber().subscriberNumber(10);
        String cardNumber = faker.finance().creditCard(CreditCardType.VISA);
        String city = faker.address().city();
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(city); //город
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE); //очитка поля дата
        $("[data-test-id=date] input").setValue(formatter.format(date1)); //дата
        $("[data-test-id=name] input").setValue(name); // ФИО
        $("[data-test-id=phone] input").setValue("+7" + phone); // telefon number
        $("[data-test-id=agreement]").click(); //согласие
        $("[class='button__content']").click(); //Продолжить
        $(withText("Заказ на выбранную дату невозможен")).shouldBe(visible);

    }


    @Test
    void shouldSendValidForm() {

        String name = faker.name().fullName();
        String phone = faker.phoneNumber().subscriberNumber(10);
        String cardNumber = faker.finance().creditCard(CreditCardType.VISA);
        String city = faker.address().city();
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue("+7" + phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldBe(visible)
                .shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }

}