package com.kocesat.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis(); // for performance measuring
        List<Hint> constraints = new ArrayList<>();
        constraints.add(new Hint(List.of(6, 9, 0), 1, true));
        constraints.add(new Hint(List.of(7, 4, 1), 1, false));
        constraints.add(new Hint(List.of(5, 0, 4), 2, false));
        constraints.add(new Hint(List.of(3, 8, 7), 0, true));
        constraints.add(new Hint(List.of(2, 1, 9), 1, false));

        int[] possibleSolutions = IntStream.range(100, 1000).toArray();
        int[] solutions = Arrays.stream(possibleSolutions)
                .filter(candidate -> satisfiesConstraints(candidate, constraints))
                .toArray();
        long end = System.currentTimeMillis();
        System.out.println("Elapsed at: " + (end - start) + " ms." );
        System.out.println(Arrays.toString(solutions));
    }


    public static boolean satisfiesConstraints(int num, List<Hint> hints) {
        List<Integer> digits = Arrays.stream(String.valueOf(num).split(""))
                .map(s -> Integer.parseInt(s, 10))
                .collect(Collectors.toList());
        for (Hint hint : hints) {
            if (!satisfiesConstraint(hint, digits)) {
                return false;
            }
        }
        return true;
    }

    private static boolean satisfiesConstraint(Hint hint, List<Integer> digits) {
        AtomicInteger counter = new AtomicInteger();
        boolean satisfiesPlaceCondition = true;
        for (int i = 0; i < digits.size(); i++) {
            int digit = digits.get(i);
            if (hint.getNumbers().contains(digit)) {
                counter.getAndIncrement();
                if (isCorrectPlace(digit, hint, i) != hint.isCorrectPlace()) {
                    satisfiesPlaceCondition = false;
                }
            }
        }
        return counter.get() == hint.getCorrectCount() && satisfiesPlaceCondition;
    }

    private static boolean isCorrectPlace(int digit, Hint hint, int correctIndex) {
        return hint.getNumbers().get(correctIndex) == digit;
    }
}
