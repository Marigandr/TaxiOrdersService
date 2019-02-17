package com.testtasks.taxiordersservice.data.room.entity;

import androidx.room.ColumnInfo;
import lombok.Data;

/**
 * Информация о машине и водителе
 */
@Data
public class Vehicle {
    /**
     * Регистрационный номер машины
     */
    @ColumnInfo(name = "regNumber")
    private String regNumber;

    /**
     * Марка машины
     */
    @ColumnInfo(name = "modelName")
    private String modelName;

    /**
     * Наименование фото машины на сервере
     */
    @ColumnInfo(name = "photo")
    private String photo;

    /**
     * ФИО водителя
     */
    @ColumnInfo(name = "driverName")
    private String driverName;
}
