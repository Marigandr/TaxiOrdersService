package com.testtasks.taxiordersservice.service;

import java.util.HashMap;

import lombok.Data;

/**
 * Класс-обёртка для сериализации HashMap в String, и наоборот,
 * с помощью Gson для последующего хранения HashMap в SharePreferences
 */

@Data
public class MapWrapper {
    /**
     * Key = наименование изображения, Value = время загрузки с сервера
     */
    private HashMap<String, Long> photoLifetimeMap;
}
