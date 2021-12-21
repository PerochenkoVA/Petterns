package ru.netology.auto.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class GenPatern {
    private GenPatern() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationByCardInfo generate(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByCardInfo(
                    faker.address().city(),
                    faker.name().lastName() + " " + faker.name().firstName(),
                    faker.phoneNumber().phoneNumber());
        }

        public static String generateDate(int days){
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        public static String citiesValid(){
            Random random = new Random();
            int rand = random.nextInt(7);
            String cities[] = {"Москва","Санкт-Петербург","Омск","Красноярск","Новосибирск","Казань","Уфа",};
            return cities[rand];
        }
        public static String citiesNotValid(){
            Random random = new Random();
            int rand = random.nextInt(7);
            String citiesNotVal[] = {"Сочи","Енисейск","Выборг","Приморск","Канск","Геленджик","Пушкин"};
            return citiesNotVal[rand];
        }
    }
}
