# Тестовое задание

Необходимо написать приложение, которое загружает с сервера JSON-файл по адресу [http://www.roxiemobile.ru/careers/test/orders.json], разбирает его и выводит на
 экран отсортированный по убыванию даты список с информацией об активных заказах в службе такси. Заказы должны также сохраняться в БД (Room) и отображаться в
 случае отсутствия интернет-соединения. В каждом элементе списка должны отображаться: начальный адрес, конечный адрес, дата поездки, стоимость заказа.

Сумма в стоимости заказа представлена в виде двух полей: целого числа копеек (или других минимальных единиц для других валют) и кода валюты по ISO 4217. Например, сумма 100 рублей 10 копеек будет иметь следующий вид:

{"amount": 10010, "currency": "RUB"}

По нажатию на элемент списка необходимо показать экран с детализацией, где, помимо перечисленных выше полей, должны отображаться время заказа, а также информация о машине и водителе.
Для запроса фотографии машины с сервера используется адрес [http://www.roxiemobile.ru/careers/test/images/{imageName}], где {imageName} – это содержимое поля [photo] из JSON-файла.

Приложение должно поддерживать Android 4.4+, поворот экрана и быть оформлено в соответствии с руководством по Material Design.

## Решено при помощи:

- MVP
- Retrofit 2
- RxJava 2
- Conductor
- Dagger 2
- Room
- Butterknife
- Lombok
- Gson
- Picasso
