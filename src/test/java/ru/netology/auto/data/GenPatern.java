package ru.netology.auto.data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class GenPatern {
    private GenPatern() {
    }

    public static class Registeration {
        private Registeration() {
        }

        public static RegistrationByCardInfo generate(String local) {
            Faker faker = new Faker(new Locale(local));
            return new RegistrationByCardInfo(
                    faker.name().fullName(),
                    faker.finance().creditCard(CreditCardType.VISA),
                    LocalDate.now().plusYears(1),
                    faker.address().city()
            );


        }

    }
}