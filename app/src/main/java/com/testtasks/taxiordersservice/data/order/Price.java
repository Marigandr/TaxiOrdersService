package com.testtasks.taxiordersservice.data.order;

import lombok.Data;

/**
 * Стоимость заказа
 */
@Data
public class Price {
    /**
     * Сумма
     */
    private Integer amount;

    /**
     * Код валюты
     */
    private String currency;
}
