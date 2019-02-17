package com.testtasks.taxiordersservice.data.room.entity;

import androidx.room.ColumnInfo;
import lombok.Data;

/**
 * Начальный адрес заказа
 */
@Data
public class StartAddress {
    /**
     * Город
     */
    @ColumnInfo(name = "start_city")
    private String city;

    /**
     * Адрес
     */
    @ColumnInfo(name = "start_address")
    private String address;
}
