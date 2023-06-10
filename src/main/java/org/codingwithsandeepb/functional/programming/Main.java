package org.codingwithsandeepb.functional.programming;

import javax.naming.NameNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws NameNotFoundException {

        // print values of each character using a string value
        String greeting = "mynameissandeepbegudem";

        Map<String, Long> collect = Arrays.stream(greeting.split(""))
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        System.out.println(collect);


        // find list of a characters that are repeated in a String
        String message = "iamanastronaut";

        Map<String, Long> message1 = Arrays.stream(message.split(""))
                .collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));

        List<String> collect1 = message1.entrySet().stream().filter(m -> m.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(collect1);


        // find the list of elements that are unique or not repeated in a String
        String secretKey = "iamaskydiver";

        Map<String, Long> splitChars = Arrays.stream(secretKey.split("")).collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));

        List<String> uniqueKeys = splitChars.entrySet().stream().filter(s -> s.getValue() == 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());

        System.out.println(uniqueKeys);

        // find the first element that non repeat element
        String racer = "lewishamilton";

        LinkedHashMap<String, Long> racer1 = Arrays.stream(racer.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new
                , Collectors.counting()));

        List<String> firstRepKey = racer1.entrySet().stream().filter(r -> r.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toList());
        Optional<String> first = firstRepKey.stream().findFirst();

        System.out.println(first.get());

        // longest string in an array
        String [] myItems = {"backpack", "hiking-boots", "shades", "tent", "ready-to-cook-food", "water"};
        Optional<String> longestString = Arrays.stream(myItems)
                .reduce((item1, item2) -> item1.length() > item2.length() ? item1 : item2);
        System.out.println(longestString.get());


        // find the second highest integer in a array
        int [] numbers = {54, 1, 785, 76, 12, 9, 43, 34, 543, 879};
        Integer secondHighestNumber = Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();
        System.out.println(secondHighestNumber);


        // find the 3rd lowest number using a array
        int [] num = {12, 908, 1, 43, 23, 2, 98, 12543};
        Integer lowest3rdNum = Arrays.stream(num).boxed()
                .sorted()
                .skip(2)
                .findFirst().get();
        System.out.println(lowest3rdNum);
    }
}