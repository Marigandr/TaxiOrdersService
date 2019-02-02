package com.testtasks.taxiordersservice.data.order;

import lombok.Data;

/**
 * Информация о машине и водителе
 */
@Data
public class Vehicle {
    /**
     * Регистрационный номер машины
     */
    private String regNumber;

    /**
     * Марка машины
     */
    private String modelName;

    /**
     * Наименование фото машины на сервере
     */
    private String photo;

    /**
     * ФИО водителя
     */
    private String driverName;
}
