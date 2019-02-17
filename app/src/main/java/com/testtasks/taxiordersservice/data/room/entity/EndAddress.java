package com.testtasks.taxiordersservice.data.room.entity;

import androidx.room.ColumnInfo;
import lombok.Data;

/**
 * Конечный адрес заказа
 */
@Data
public class EndAddress {
    /**
     * Город
     */
    @ColumnInfo(name = "end_city")
    private String city;

    /**
     * Адрес
     */
    @ColumnInfo(name = "end_address")
    private String address;
}
