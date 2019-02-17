package com.testtasks.taxiordersservice.data.room.entity;

import com.testtasks.taxiordersservice.data.room.converter.DateConverter;

import java.util.Date;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import lombok.Data;

/**
 * Заказ
 */
@Data
@Entity
public class Order {
    @PrimaryKey
    private Integer id;

    /**
     * Начальный адрес
     */
    @Embedded
    private StartAddress startAddress;

    /**
     * Конечный адрес
     */
    @Embedded
    private EndAddress endAddress;

    /**
     * Стоимость заказа
     */
    @Embedded
    private Price price;

    /**
     * Время заказа
     */
    @TypeConverters({DateConverter.class})
    private Date orderTime;

    /**
     * Информация о машине и водителе
     */
    @Embedded
    private Vehicle vehicle;
}
