package com.playpal2.utils;

import lombok.Getter;

@Getter
public enum Category {
    GIOVANILI("Giovanili"),
    DIVISIONE_REGIONALE_TRE("Divisione Regionale 3"),
    DIVISIONE_REGIONALE_DUE("Divisione Regionale 2"),
    DIVISIONE_REGIONALE_UNO("Divisione Regionale 1"),
    SERIE_C("Serie C");

    private final String value;


    Category(String value) {
        this.value = value;
    }
}
