package com.testtasks.taxiordersservice.data.room.entity;

import androidx.room.ColumnInfo;
import lombok.Data;

/**
 * Стоимость заказа
 */
@Data
public class Price {
    /**
     * Сумма
     */
    @ColumnInfo(name = "amount")
    private Integer amount;

    /**
     * Код валюты
     */
    @ColumnInfo(name = "currency")
    private String currency;
}
