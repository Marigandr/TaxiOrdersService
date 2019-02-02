package com.testtasks.taxiordersservice.data.order;

import lombok.Data;

/**
 * Адрес заказа
 */
@Data
public class Address {
    /**
     * Город
     */
    private String city;

    /**
     * Адрес
     */
    private String address;
}
