package ru.netology.auto.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
public class RegistrationByCardInfo {
    private final String name;
    private final String card;
    private final LocalDate cardExpire;
    private final String city;
}
