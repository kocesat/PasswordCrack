package com.kocesat.passwordcrack;

import com.kocesat.passwordcrack.enums.Policy;
import lombok.Data;

@Data
public class Hint {
    int[] numbers = new int[3];
    private Policy policy;

    public Hint(int first, int second, int third, Policy policy) {
        this.numbers[0] = first;
        this.numbers[1] = second;
        this.numbers[2] = third;
        this.policy = policy;
    }

    public boolean contains(int num) {
        for (int i = 0; i < 3; i++) {
            if (numbers[i] == num) {
                return true;
            }
        }
        return false;
    }

    public boolean inCorrectPlace(int number, int correctIndex) {
        return numbers[correctIndex] == number;
    }

    public int getFirst() {
        return numbers[0];
    }

    public int getSecond() {
        return numbers[1];
    }

    public int getThird() {
        return numbers[2];
    }
}
