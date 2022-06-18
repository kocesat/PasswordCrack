package com.kocesat.performant.enums;

public enum Policy {
    ONE_IN_CORRECT_PLACE("One number is correct and in the correct place"),
    ONE_IN_WRONG_PLACE( "One number is correct and in the wrong place"),
    TWO_IN_WRONG_PLACE("Two numbers are correct and in the wrong place"),
    NONE("Nothing is correct");

    private String explanation;

    Policy(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }
}
