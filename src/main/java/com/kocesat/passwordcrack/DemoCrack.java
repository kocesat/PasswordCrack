package com.kocesat.passwordcrack;

import com.kocesat.passwordcrack.enums.Policy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemoCrack {
    public static void main(String[] args) {
        List<Hint> hints = new ArrayList<>();
        hints.add(new Hint(6, 9, 0, Policy.ONE_IN_CORRECT_PLACE));
        hints.add(new Hint(7, 4, 1, Policy.ONE_IN_WRONG_PLACE));
        hints.add(new Hint(5, 0, 4, Policy.TWO_IN_WRONG_PLACE));
        hints.add(new Hint(3, 8, 7, Policy.NONE));
        hints.add(new Hint(2, 1, 9, Policy.ONE_IN_WRONG_PLACE));

        Integer[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> firstCodeOptions = new ArrayList<>(Arrays.asList(digits));
        List<Integer> secondCodeOptions = new ArrayList<>(Arrays.asList(digits));
        List<Integer> thirdCodeOptions = new ArrayList<>(Arrays.asList(digits));

        reducePossibilities(hints, firstCodeOptions, secondCodeOptions, thirdCodeOptions);
        List<Lock> possibleLocks = generatePossibleLocks(firstCodeOptions, secondCodeOptions, thirdCodeOptions);
        List<Lock> eligibles = filterEligiblesUsing(hints, possibleLocks);
        eligibles.forEach(System.out::println);
    }

    private static List<Lock> filterEligiblesUsing(List<Hint> hints, List<Lock> possibleLocks) {
        possibleLocks.forEach(lock -> {
            hints.forEach(hint -> {
                switch (hint.getPolicy()) {
                    case ONE_IN_CORRECT_PLACE:
                        if (lock.isEligible() && !lock.oneInCorrectPlace(hint)) {
                            lock.setEligible(false);
                        }
                        break;
                    case ONE_IN_WRONG_PLACE:
                        if (lock.isEligible() && !lock.OneInWrongPlace(hint)) {
                            lock.setEligible(false);
                        }
                        break;
                    case TWO_IN_WRONG_PLACE:
                        if (lock.isEligible() && !lock.twoInWrongPlace(hint)) {
                            lock.setEligible(false);
                        }
                        break;
                    default:
                        break;
                }
            });
        });

        return possibleLocks.stream().filter(Lock::isEligible).collect(Collectors.toList());
    }

    private static List<Lock> generatePossibleLocks(List<Integer> firstCodeOptions,
                                                    List<Integer> secondCodeOptions,
                                                    List<Integer> thirdCodeOptions) {
        List<Lock> possibleLocks = new ArrayList<>();
        firstCodeOptions.forEach(first -> {
            secondCodeOptions.forEach(second -> {
                thirdCodeOptions.forEach(third -> {
                    possibleLocks.add(new Lock(first, second, third));
                });
            });
        });
        return possibleLocks;
    }

    public static void reducePossibilities(List<Hint> hints,
                                           List<Integer> firstCodeOptions,
                                           List<Integer> secondCodeOptions,
                                           List<Integer> thirdCodeOptions) {
        hints.forEach(hint -> {
            switch (hint.getPolicy()) {
                case ONE_IN_WRONG_PLACE:
                case TWO_IN_WRONG_PLACE:
                    firstCodeOptions.removeIf(d -> d.equals(hint.getFirst()));
                    secondCodeOptions.removeIf(d -> d.equals(hint.getSecond()));
                    thirdCodeOptions.removeIf(d -> d.equals(hint.getThird()));
                    break;
                case NONE:
                    firstCodeOptions.removeIf(d -> d.equals(hint.getFirst()));
                    firstCodeOptions.removeIf(d -> d.equals(hint.getSecond()));
                    firstCodeOptions.removeIf(d -> d.equals(hint.getThird()));
                    secondCodeOptions.removeIf(d -> d.equals(hint.getFirst()));
                    secondCodeOptions.removeIf(d -> d.equals(hint.getSecond()));
                    secondCodeOptions.removeIf(d -> d.equals(hint.getThird()));
                    thirdCodeOptions.removeIf(d -> d.equals(hint.getFirst()));
                    thirdCodeOptions.removeIf(d -> d.equals(hint.getSecond()));
                    thirdCodeOptions.removeIf(d -> d.equals(hint.getThird()));
                    break;
                default:
                    break;
            }
        });
    }
}
