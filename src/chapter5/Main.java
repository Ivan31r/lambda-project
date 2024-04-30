package chapter5;

import chapter1.common.Artist;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

public class Main {
    public static void main(String[] args) {

    }

    public static void ex1(){
        Stream<Artist> albumns = Stream.empty();
        Map<Boolean, List<Artist>> collect = albumns.collect(partitioningBy(artist -> artist.isSolo()));
    }
}
