package chapter3;

import chapter1.common.Album;
import chapter1.common.Artist;
import chapter1.common.Track;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter1.common.SampleData.*;

public class Main {
    public static void main(String[] args) {
//        flarMap();
//        reduce();
//        ex1();
//        ex2();
//        ex4();
//        stringEx1();
        stringEx2();
    }

    private static void flarMap() {
        List<Integer> list1 = Stream.of(Arrays.asList(1, 2), Arrays.asList(1, 2)).flatMap(new Function<List<Integer>, Stream<Integer>>() {
            @Override
            public Stream<Integer> apply(List<Integer> integers) {
                return integers.stream();
            }
        }).collect(Collectors.toList());

        List<Integer> list2 = Stream.of(Arrays.asList(1, 2), Arrays.asList(1, 2)).flatMap(Collection::stream).toList();
    }

    private static void reduce() {
        Integer sum1 = Stream.of(1, 2, 3).reduce(10, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });

        Integer sum2 = Stream.of(1, 2, 3).reduce(10, Integer::sum);

    }

    public static void ex318() {
        Album loveSupreme = aLoveSupreme;
        Set<String> origin1 = loveSupreme.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .collect(Collectors.toSet());

        Set<String> origin2 = loveSupreme.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(Artist::getNationality)
                .collect(Collectors.toSet());
    }

    public static void findLongestTracks() {
        List<Album> albums = Arrays.asList(aLoveSupreme, aLoveSupreme);
        //old
        Set<String> trackNames = new HashSet<>();
        for (Album album : albums) {
            for (Track track : album.getTrackList()) {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        System.out.println(trackNames);

        //new
        Set<String> tracksName = albums.stream()
                .flatMap(album -> album.getTrackList().stream())
                .filter(track -> track.getLength() > 60)
                .map(Track::getName)
                .collect(Collectors.toSet());
    }

    public static void ex1() {
        System.out.println(addUp(Stream.of(1, 2, 3, 4)));
    }

    private static int addUp(Stream<Integer> numbers) {
//        return numbers.reduce(0, (integer, integer2) -> integer + integer2); //or
        return numbers.reduce(0, Integer::sum);
    }

    public static void ex2() {
        List<Artist> artists = List.of(johnLennon, paulMcCartney, georgeHarrison, ringoStarr);
        System.out.println(namesAndNationality(artists));

    }

    private static List<String> namesAndNationality(List<Artist> artists) {
        List<String> list = artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), "---", artist.getNationality()))
                .collect(Collectors.toList());
        return list;
    }

    public static void ex3() {
        List<Album> albums = Arrays.asList(aLoveSupreme, sampleShortAlbum, manyTrackAlbum);
        System.out.println(getAlbumWithLessThen3Tracks(albums));

    }

    private static List<Album> getAlbumWithLessThen3Tracks(List<Album> albums) {
        return albums.stream().filter(album -> album.getTrackList().size() < 3).collect(Collectors.toList());
    }

    public static void ex4() {
        List<Artist> artists = List.of(johnLennon, paulMcCartney, georgeHarrison, ringoStarr);
//        long count = artists.stream().flatMap(artist -> artist.getMembers()).count();
//        long count = artists.stream().flatMap(Artist::getMembers).count(); //or
        //or
        int count = artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, (x, y) -> x + y)
                .intValue();

        System.out.println(count);
    }

    public static void ex6() {
        String string = "test string";
        System.out.println(countLowerCase(string));
    }

    private static int countLowerCase(String string) {
//        long count = string.chars().filter(ch -> Character.isLowerCase(ch)).count();
        int count = (int) string.chars().filter(Character::isLowerCase).count();
        return count;
    }

    public static void stringEx1() {
        List<Album> albums = Arrays.asList(aLoveSupreme, aLoveSupreme);
//        Set<String> trackNames = albums.stream()
//                .flatMap(album -> album.getTrackList().stream())
//                .filter(track -> track.getLength() > 60)
//                .map(Track::getName)
//                .collect(Collectors.toSet());
//

        ArrayList<String> reduced = albums.stream()
                .flatMap(album -> album.getTrackList().stream())
                .filter(track -> track.getLength() > 60)
                .reduce(new ArrayList<String>(), new BiFunction<ArrayList<String>, Track, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, Track track) {
                        strings.add(track.getName());
                        return strings;
                    }
                }, new BinaryOperator<ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, ArrayList<String> strings2) {
                        strings.addAll(strings2);
                        return strings;
                    }
                });

        ArrayList<String> reduced2 = albums.stream()
                .flatMap(album -> album.getTrackList().stream())
                .filter(track -> track.getLength() > 60)
                .reduce(new ArrayList<>(), (strings, track) -> {
                    ArrayList<String> list = new ArrayList<>(strings);
                    list.add(track.getName());
                    return list;
                }, (strings, strings2) -> {
                    ArrayList<String> list = new ArrayList<>(strings);
                    list.addAll(strings2);
                    return list;
                });

        System.out.println(reduced);
        System.out.println(reduced2);



    }

    public static void stringEx2(){
        List<Album> albums = Arrays.asList(aLoveSupreme, aLoveSupreme);

        ArrayList<Track> trackArrayList = albums.stream()
                .flatMap(album -> album.getTrackList().stream())
                .reduce(new ArrayList<Track>(), (accumulator, track) -> {
                    ArrayList<Track> accumulatorList = new ArrayList<>(accumulator);
                    if (track.getLength() > 450) {
                        accumulatorList.add(track);
                    }
                    return accumulatorList;
                }, (leftTrack, rightTrack) -> {
                    ArrayList<Track> combineList = new ArrayList<>(leftTrack);
                    combineList.addAll(rightTrack);
                    return combineList;
                });

        System.out.println(trackArrayList);
    }

//    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
//        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
//             We are copying data from acc to new list instance. It is very inefficient,
//             but contract of Stream.reduce method requires that accumulator function does
//             not mutate its arguments.
    // Stream.collect method could be used to implement more efficient mutable reduction,
    // but this exercise asks to use reduce method.
//            List<O> newAcc = new ArrayList<>(acc);
//            newAcc.add(mapper.apply(x));
//            return newAcc;
//        }, (List<O> left, List<O> right) -> {
    // We are copying left to new list to avoid mutating it.
//            List<O> newLeft = new ArrayList<>(left);
//            newLeft.addAll(right);
//            return newLeft;
//        });
//    }


}
