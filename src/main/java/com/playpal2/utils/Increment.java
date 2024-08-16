package com.playpal2.utils;

import lombok.Getter;

@Getter
public enum Increment {
    ONE(1),
    TWO(2),
    THREE(3);

    private final int value;

    Increment(int value) {
        this.value = value;
    }

}
