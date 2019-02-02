package com.testtasks.taxiordersservice.data.order;

import java.util.Date;

import lombok.Data;

/**
 * Заказ
 */
@Data
public class Order {

    private Integer id;

    /**
     * Начальный адрес
     */
    private Address startAddress;

    /**
     * Конечный адрес
     */
    private Address endAddress;

    /**
     * Стоимость заказа
     */
    private Price price;

    /**
     * Время заказа
     */
    private Date orderTime;

    /**
     * Информация о машине и водителе
     */
    private Vehicle vehicle;
}
