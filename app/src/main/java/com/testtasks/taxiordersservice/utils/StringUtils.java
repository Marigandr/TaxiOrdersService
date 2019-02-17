package com.testtasks.taxiordersservice.utils;

import com.testtasks.taxiordersservice.data.room.entity.Price;
import com.testtasks.taxiordersservice.data.room.entity.Vehicle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class StringUtils {

    /**
     * Получение строки адреса вида "Санкт-Петербург, Пр. Ленина, д. 1"
     */
    public static String getFullAddressString(String city, String address) {
        return city + ", " + address;
    }

    /**
     * Получение строки даты вида "31 января 2019 г."
     */
    public static String getDateString(Date date) {
        SimpleDateFormat fmtOut = new SimpleDateFormat("d MMMM yyyy 'г.'", Locale.getDefault());
        return fmtOut.format(date);
    }

    /**
     * Получение строки даты вида "31 января 2019 г. в 15:00"
     */
    public static String getDateAndTimeString(Date date) {
        SimpleDateFormat fmtOut = new SimpleDateFormat("d MMMM yyyy 'г. в' HH:mm", Locale.getDefault());
        return fmtOut.format(date);
    }

    /**
     * Получение строки стоимости вида "230,10 RUB" (amount + currency)
     */
    public static String getPriceString(Price price) {
        double amount = price.getAmount() / 100.0;
        String amountString = amount - (int)amount != 0 ? String.valueOf(amount) : String.valueOf((int)amount);

        return amountString + " " + price.getCurrency();
    }

    /**
     * Получение строки информации о машине вида "Nissan Almera (Х555МТ98)" (model + regNumber)
     */
    public static String getCarModelAndNumberString(Vehicle vehicle) {
        return vehicle.getModelName() + " (" + vehicle.getRegNumber().toUpperCase()  + ")";
    }
}
