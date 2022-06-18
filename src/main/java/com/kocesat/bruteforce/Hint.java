package com.kocesat.bruteforce;

import lombok.Data;

import java.util.List;

@Data
public class Hint {
    private List<Integer> numbers;
    private int correctCount;
    private boolean correctPlace; // true means in correct place

    public Hint(List<Integer> numbers, int correctCount, boolean correctPlace) {
        this.numbers = numbers;
        this.correctCount = correctCount;
        this.correctPlace = correctPlace;
    }
}
