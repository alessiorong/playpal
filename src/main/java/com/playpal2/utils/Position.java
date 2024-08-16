package com.playpal2.utils;

import lombok.Getter;

@Getter
public enum Position {
    PLAYMAKER("playmaker"),
    GUARDIA("guardia"),
    ALA_PICCOLA("ala piccola"),
    ALA_GRANDE("ala grande"),
    CENTRO("centro");

    private final String value;

    Position(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
