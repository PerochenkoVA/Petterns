# Patterns task one

[![Build status](https://ci.appveyor.com/api/projects/status/4brcfc7c6pt71il1?svg=true)](https://ci.appveyor.com/project/PerochenkoVA/petterns)



Вам необходимо автоматизировать тестирование новой функции формы заказа доставки карты:



Требования к содержимому полей, сообщения и другие элементы, по словам Заказчика и Разработчиков, "такие же, мы ничего не меняли"*.
Примечание*: личный совет - не забудьте это перепроверить, никому нельзя верить 😈.
Тестируемая функциональность: если заполнить форму повторно теми же данными за исключением "Даты встречи", то система предложит перепланировать время встречи:
После нажатия на кнопке "Перепланировать" произойдёт перепланирование встречи:
Важно: в этот раз вы не должны хардкодить данные прямо в тест! Используйте Faker, Lombok, Data-классы (для группировки нужных полей) и утилитный класс-генератор данных* - см. пример в презентации.

Утилитными называют классы, у которых приватный конструктор и статичные методы.
Обратите внимание, что Faker может генерировать не совсем в нужном для вас формате.

