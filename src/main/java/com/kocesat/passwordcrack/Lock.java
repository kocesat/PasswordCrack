package com.kocesat.passwordcrack;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Lock {
    private int firstCode;
    private int secondCode;
    private int thirdCode;
    private boolean eligible = true;

    public Lock(int first, int second, int third) {
        this.firstCode = first;
        this.secondCode = second;
        this.thirdCode = third;
        invalidateAllEquals();
    }

    public void invalidateAllEquals() {
        if (firstCode == secondCode || firstCode == thirdCode || secondCode == thirdCode) {
            eligible = false;
        }
    }

    public boolean oneInCorrectPlace(Hint hint) {
        int numOfCorrects = 0;
        if (firstCode == hint.getFirst()) {
            numOfCorrects++;
        }
        if (secondCode == hint.getSecond()) {
            numOfCorrects++;
        }
        if (thirdCode == hint.getThird()) {
            numOfCorrects++;
        }
        return numOfCorrects == 1;
    }

    public boolean OneInWrongPlace(Hint hint) {
        return inWrongPlace(hint, 1);
    }

    public boolean twoInWrongPlace(Hint hint) {
        return inWrongPlace(hint, 2);
    }

    private boolean inWrongPlace(Hint hint, int numberOfCorrects) {
        int numOfCorrects = 0;

        if (hint.contains(firstCode) && !hint.inCorrectPlace(firstCode, 0)) {
            numOfCorrects++;
        }

        if (hint.contains(secondCode) && !hint.inCorrectPlace(secondCode, 1)) {
            numOfCorrects++;
        }

        if (hint.contains(thirdCode) && !hint.inCorrectPlace(thirdCode, 2)) {
            numOfCorrects++;
        }
        return numOfCorrects == numberOfCorrects;
    }

    @Override
    public String toString() {
        return "" + firstCode + secondCode + thirdCode;
    }
}
