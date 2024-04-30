package chapter5.ex2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<String> names = Stream.of(
                "John Lennon",
                "Paul McCartney",
                "George Harrison",
                "Ringo Starr",
                "Pere Best",
                "Stuart Sutcliffe");
//        System.out.println(longestName(names));
        System.out.println(longestNameV2(names));

        Stream<String> simpleNames = Stream.of("John", "Paul", "George", "John", "Paul", "John");
    }


    public static String longestName(Stream<String> names) {
        return names.reduce((accumulator, name) -> {
            return (accumulator.compareTo(name) >= 0 ? accumulator : name);
        }).orElse("");
    }

    public static String longestNameV2(Stream<String> names) {
        return names.max(String::compareTo).orElseGet(() -> "default");
    }

    public static Map<String, Long> wordsCounter(Stream<String> names) {
        return names.collect(Collectors.groupingBy(name -> name, Collectors.counting()));

    }




}
